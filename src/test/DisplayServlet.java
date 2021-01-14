package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class DisplayServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		String uname=req.getParameter("username");
		PrintWriter out = res.getWriter();
		out.println("Welcome "+uname);
		Connection conn = DBUtil.getConnection();
		try
		{
             PreparedStatement ps = conn.prepareStatement("select * from DASH1.user_info where username=?");
             ps.setString(1,uname);
             out.print("<center><table width=50% border=1 ></center>");  
             out.print("<caption>Result:</caption>");  
             ResultSet rs =ps.executeQuery();
             ResultSetMetaData rsmd=rs.getMetaData();  
             int total=rsmd.getColumnCount();  
             out.print("<tr>");  
             for(int i=1;i<=total;i++)  
             {  
            	 out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
             }  
             out.print("</tr>");  
             
             while(rs.next())  
             {  
            	 out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td></tr>");  
                               
             }  
             out.print("</table>");  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
