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

public class CreateProfileServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		//requestDispatcher
		RequestDispatcher dispatcher = null;
		
		String regNoVal = req.getParameter("regNo");
		String fNameVal = req.getParameter("firstName");
		String mNameVal = req.getParameter("middleName");
		String lNameVal = req.getParameter("lastName");
		String gFNameVal = req.getParameter("gFirstName");
		String gMNameVal = req.getParameter("gMiddleName");
		String gLNameVal = req.getParameter("gLastName");
		String adminVal = req.getParameter("admin");
		String passwordVal = req.getParameter("password");
		
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
			String qry1= "insert into student_info values(?,?,?,?) ";
			pstmt=con.prepareStatement(qry1);
			pstmt.setInt(1, Integer.parseInt(regNoVal));
			pstmt.setString(2,fNameVal);
			pstmt.setString(3,mNameVal);
			pstmt.setString(4,lNameVal);
			int count=pstmt.executeUpdate();
			pstmt.close();
			
			String qry2= "insert into guardian_info values(?,?,?,?) ";
			pstmt=con.prepareStatement(qry2);
			pstmt.setInt(1, Integer.parseInt(regNoVal));
			pstmt.setString(2,gFNameVal);
			pstmt.setString(3,gMNameVal);
			pstmt.setString(4,gLNameVal);
			int count1=pstmt.executeUpdate();
			pstmt.close();
			
			String qry3= "insert into student_otherinfo values(?,?,?) ";
			pstmt=con.prepareStatement(qry3);
			pstmt.setInt(1, Integer.parseInt(regNoVal));
			pstmt.setString(2,adminVal);
			pstmt.setString(3,passwordVal);
			int count2=pstmt.executeUpdate();
			
			dispatcher = req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
			//process the result returned by SQL qry	
							out.print("<html>");
				        out.print("<head>");
				           out.print("<meta charset=\"UTF-8\">");
				           out.print("<title>Success</title>");
				           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
				           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
				           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
				           out.print("<style>");
				           out.print("body{");
				           out.print("background-image: url(\"img/profile_created.jpg\");");
				           out.print("}");
				           out.print("</style>");
				           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
				            out.print("<script>");
				           out.print("$(document).ready(function() {");
				           out.print("$(\"h1\").addClass(\"animated bounceInUp\");");
				         out.print("});");
				         out.print("</script>");
				       out.print("</head>");
				       		out.print("<body>");
				       		out.print("</br>");
				       		
				               out.print("<h1 align=\"center\">");
				               out.print("<font color=\"green\">");
				               out.print("Profile Created Succesfully");
				               out.print("</font>");
				               out.print("</h1>");
				       out.print("</body>");
				       out.print("</html>");
				       out.print("</br></br></br></br></br></br></br></br></br></br>");
				       dispatcher = req.getRequestDispatcher("Footer.html");
						dispatcher.include(req, resp);
			
		} catch (SQLException e) {
			
			dispatcher = req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
			out.print("<html>");
	        out.print("<head>");
	           out.print("<meta charset=\"UTF-8\">");
	           out.print("<title>Success</title>");
	           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
	           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
	           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
	           
	           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
	            out.print("<script>");
	           out.print("$(document).ready(function() {");
	           out.print("$(\"h1\").addClass(\"animated bounceInUp\");");
	         out.print("});");
	         out.print("</script>");
	       out.print("</head>");
	       		out.print("<body>");
	       		out.print("</br>");
	       		out.print("</br>");
	       		out.print("</br>");
	               out.print("<h1 align=\"center\">");
	               out.print("<font color=\"green\">");
	               out.print("Reg No already present, try with other regno");
	               out.print("</font>");
	               out.print("</h1>");
	       out.print("</body>");
	       out.print("</html>");
	       out.print("</br></br></br></br></br></br></br></br></br></br>");
	       dispatcher = req.getRequestDispatcher("Footer.html");
			dispatcher.include(req, resp);
			
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
	
}