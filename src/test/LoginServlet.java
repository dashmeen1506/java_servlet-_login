package test;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/pqr")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		String uname=req.getParameter("username");
		String pwd=req.getParameter("password");
		Connection conn= DBUtil.getConnection();
		PrintWriter out = res.getWriter();
		try
		{
             PreparedStatement ps = conn.prepareStatement("select * from DASH1.login_info");
             ResultSet rs = ps.executeQuery();
             while(rs.next())
             {
            	 String username=rs.getString(1);
            	 String password=rs.getString(2);
            	 if(username.equals(uname) && pwd.equals(password))
            	 {
            		 RequestDispatcher rd = req.getRequestDispatcher("welcome2");
         			 rd.forward(req, res);
            	 }
            	 else
            	 {
         			 out.println("Incorrect username or password");
         			 RequestDispatcher rd = req.getRequestDispatcher("Login.html");
         			 rd.include(req, res);
            	 }
             }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
