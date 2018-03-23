package com.app.www;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class Delete extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String regnoVal = req.getParameter("regno");
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		String qry1="delete from student_info where regno=?";
		String qry2="delete from Student_otherinfo where regno=?";
		String qry3="delete from guardian_info where regno=?";
		
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		
		RequestDispatcher dispatcher = null;
		// 1. load the driver
		try {
			//load the driver
			Driver driveref = new Driver();
			DriverManager.registerDriver(driveref);
			
			//get DB connection via driver
			String DBurl= "jdbc:mysql://localhost:3306/becm19_db?user=root&password=root";
			con = DriverManager.getConnection(DBurl);
			
			
			int regno = Integer.parseInt(regnoVal);
			
			pstmt1 = con.prepareStatement(qry1);
			pstmt1.setInt(1,regno);
			int count1 = pstmt1.executeUpdate();
			
			pstmt2 = con.prepareStatement(qry2);
			pstmt2.setInt(1,regno);
			int count2 = pstmt2.executeUpdate();
			
			pstmt3 = con.prepareStatement(qry3);
			pstmt3.setInt(1,regno);
			int count3 = pstmt3.executeUpdate();
			
			
			if (count1>0 && count2>0 && count3>0) {
				
				dispatcher = req.getRequestDispatcher("Header.html");
				dispatcher.include(req, resp);
				
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
				out.print("$(\"h1\").addClass(\"animated zoomInUp\");");
				out.print("});");
				out.print("</script>");
				out.print("</head>");
				out.print("<body>");
				out.print("</br>");
				out.print("</br>");
				out.print("</br>");
				out.print("<h1 align=\"center\"><font color=\"green\"> Details Successfully deleted </font></h1>");
				out.print("</body>");
			    out.print("</html>");
			    dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
			}
			else {
				dispatcher = req.getRequestDispatcher("Header.html");
				dispatcher.include(req, resp);
				
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
				out.print("background-image: url(\"img/no_reg_present.jpg\");");
				out.print("}");
				out.print("</style>");
				out.print("<script>");
				out.print("$(document).ready(function() {");
				out.print("$(\"h1\").addClass(\"animated zoomInUp\");");
				out.print("});");
				out.print("</script>");
				out.print("</head>");
				out.print("<body>");
				out.print("</br>");
				out.print("</br>");
				out.print("</br>");
				out.print("<h1 align=\"center\"><font color=\"red\"> SomeThing Went Wrong </font></h1>");
				out.print("</body>");
			    out.print("</html>");
			    dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
			}
			
	} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			try {
				if(con!=null) {
					con.close();
				}
				
				if(pstmt1!=null) {
					pstmt1.close();
				}
				
				if(pstmt2!=null) {
					pstmt2.close();
				}
				
				if(pstmt3!=null) {
					pstmt3.close();
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}
