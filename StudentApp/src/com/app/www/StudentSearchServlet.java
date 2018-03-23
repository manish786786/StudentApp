package com.app.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;


public class StudentSearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		
		String regnoVal = req.getParameter("regno");
		
		//requestDispatcher
		RequestDispatcher dispatcher = null;
		
		 Connection con=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		try {
			
			//load the driver
			Driver driveref = new Driver();
			DriverManager.registerDriver(driveref);
			
			//get DB connection via driver
			String DBurl= "jdbc:mysql://localhost:3306/becm19_db?user=root&password=root";
			con = DriverManager.getConnection(DBurl);
			
			//issue SQL query via connection
			String qry= "select * from student_info si,guardian_info gi "+
			" where si.regno=gi.regno and si.regno=?";
			pstmt=con.prepareStatement(qry);
			pstmt.setInt(1, Integer.parseInt(regnoVal));
			rs=pstmt.executeQuery();
			
			
			
			//process the result returned by SQL qry
			if (rs.next()) {
				int regno = rs.getInt("si.regno");
				String firstNM= rs.getString("si.firstname");
				String middleNM= rs.getString("si.middlename");
				String lastNM = rs.getString("si.lastname");
				String gfNM = rs.getString("gi.gfirstname");
				String gmNM = rs.getString("gi.gmiddlename");
				String glNM = rs.getString("gi.glastname");	
				
				out.print("<html>");
				out.print("<head>");
				out.print("<meta charset=\"UTF-8\">");
				out.print("<title>Student search</title>");
				out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
				out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
				out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
				out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
				out.print("<style>");
				out.print("body{");
				out.print("background-image: url(\"img/search_sucess.jpg\");");
				out.print("}");
				out.print("</style>");
				out.print("<script>");
				out.print("$(document).ready(function() {");
				out.print("$(\"tr\").addClass(\"animated fadeInUp\");");
				out.print("});");
				out.print("</script>");
				out.print("</head>");
				out.print("<body>");
				out.print("</br>");
				out.print("</br>");
				out.print("</br>");
				       
				out.print("<table align=\"center\">");
				out.print("<tr bgcolor=\"green\">");
				out.print("<th>reg.no</th>");
				out.print("<th>First Name</th>");
				out.print("<th>Middle Name</th>");
				out.print("<th>Last Name</th>");
				out.print("<th>G first name</th>");
				out.print("<th>G middle name</th>");
				out.print("<th>G last name</th>");
				
				out.print("</tr>");
				out.print("<tr>");
				
				
				out.print("<td>"+regno+"</td>");
										out.print("<td>"+firstNM+"</td>");
										out.print("<td>"+middleNM+"</td>");
										out.print("<td>"+lastNM+"</td>");
										out.print("<td>"+gfNM+"</td>");
										out.print("<td>"+gmNM+"</td>");
										out.print("<td>"+glNM+"</td>");
										
										out.print("</tr>");
										out.print("</table>");
										out.print("</body>");
										out.print("</html>");
										out.print("<br><br><br><br><br><br><br><br><br>");		
								
			}
			else {
				
				out.print("<html>");
				        out.print("<head>");
				           out.print("<meta charset=\"UTF-8\">");
				           out.print("<title>Success</title>");
				           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
				           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
				           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
				           out.print("<style>");
				           out.print("body{");
				           out.print("background-image: url(\"img/no_reg_present.jpg\");");
				           out.print("}");
				           out.print("</style>");
				           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
				            out.print("<script>");
				           out.print("$(document).ready(function() {");
				           out.print("$(\"h2\").addClass(\"animated flipInX\");");
				         out.print("});");
				         out.print("</script>");
				       out.print("</head>");
				       out.print("<html>");
		        out.print("<body>");
		        out.print("<h2 align=\"center\">");
		        out.print("<font color=\"red\">");
		        out.print("reg.no. NOT PRESENT!!!");
		        out.print("</font>");
		        out.print("</h2>");
		        out.print("</body>");
		        out.print("</html>");
		        out.print("<br><br><br><br><br><br><br><br><br>");
		        
				
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			// 5. close all the "JDBC object"
			
			try {
				if(con!=null)
				{
					con.close();
				}
				
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
				if(rs!=null)
				{
					rs.close();
				}
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		
		}
		
		
		
	}//end of doget
	
}//end of class
