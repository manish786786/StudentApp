package com.app.www;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String regNoVal= req.getParameter("regno");
		String passwordVal = req.getParameter("password");
		
		// send the response to browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//requestDispatcher
		RequestDispatcher dispatcher = null;
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			// 1 load the driver
			Driver driveref = new Driver();
			DriverManager.registerDriver(driveref);

			// 2 get DB connection via driver
			String DBurl = "jdbc:mysql://localhost:3306/becm19_db?user=root&password=root";
			con = DriverManager.getConnection(DBurl);
			
			/*FileReader f1 = new FileReader("/WEB-INF/db.properties");
			Properties prop = new Properties();
			prop.load(f1);
			
			// 1 load the driver
			String driverClass = prop.getProperty("driver_class");
			Driver driver = (Driver)Class.forName(driverClass).newInstance();
			
			// 2 get DB connection via driver
			String url = prop.getProperty("url");
			con=DriverManager.getConnection(url,prop);*/
			
			//3 issue SQL query via connection
			String qry="select * from student_info si ,"
						+" guardian_info gi,"
						+" student_otherinfo soi"
						+" where si.regno = gi.regno"
						+" and si.regno=soi.regno"
						+" and soi.regno=?"
						+" and soi.password=?";
			
			pstmt=con.prepareStatement(qry);
			pstmt.setInt(1, Integer.parseInt(regNoVal));
			pstmt.setString(2, passwordVal);
			rs=pstmt.executeQuery();
			
			dispatcher = req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
			//4 process the result issue by sql query
			if(rs.next()) {
				int regno = rs.getInt("si.regno");
				String fName = rs.getString("si.firstname");
				String mName = rs.getString("si.middlename");
				String lName = rs.getString("si.lastname");
				String gFName = rs.getString("gi.gfirstname");
				String gMName = rs.getString("gi.gmiddlename");
				String gLName = rs.getString("gi.glastname");
				String isadmin = rs.getString("soi.isadmin");
				String password = rs.getString("soi.password");
				

				out.print("<html>");
		        out.print("<head>");
		           out.print("<meta charset=\"UTF-8\">");
		           out.print("<title>AllStudent View</title>");
		           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
		           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
		           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
		           out.print("<style>");
		           out.print("body{background-image: url(\"img/home1.jpg\")");
		           out.print("</style>");
		           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
		            out.print("<script>");
		           out.print("$(document).ready(function() {");
		           out.print("$(\".loginbg\").addClass(\"animated zoomInDown\");");
		           
		         out.print("});");
		         out.print("</script>");
		         out.print("</head>");
		         out.print("<body id=\"body\">");
				
				out.print("<br><br><br><br><br><br><br><br><br>");
				out.print("<h2 align=\"center\" class=\"loginbg\"><font color=\"black\">Students App</font></h2>");
				out.print("<br><br><br>");
				
				
				if(isadmin.equals("y")) {
					out.print("<br><br>");
					out.print("<h1 align=\"center\" class=\"loginbg\"><a href=\"./allstudentview\"><i><font color=\"black\"> View All Student Data </font></i></a></h1>");
					out.print("<h1 align=\"center\" class=\"loginbg\"><a href=\"./redirect?redirect=create\"><i><font color=\"black\"> Create Profile </font></i></a></h1>");
				}
				
				out.print("</body>");
				out.print("</html>");
				dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
		}//end of if
			else {
				dispatcher = req.getRequestDispatcher("Header.html");
				dispatcher.include(req, resp);
				
				out.print("<br><br><br><br><br>");
				out.print("<html>");
		        out.print("<head>");
		           out.print("<meta charset=\"UTF-8\">");
		           out.print("<title>AllStudent View</title>");
		           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
		           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
		           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
		           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
		            out.print("<script>");
		           out.print("$(document).ready(function() {");
		           out.print("$(\"#invalid\").addClass(\"animated zoomInUp\");");
		           
		         out.print("});");
		         out.print("</script>");
		out.print("</head>");
		out.print("<body>");
		out.print("</br></br></br>");
		out.print("</br></br></br>");
		out.print("</br>");
		out.print("</br>");
		out.print("<h1 align=\"center\" id=\"invalid\"> <font color=\"red\"> INVALID USER NAME OR PASSWORD </h1>");
		out.print("</body>");
		out.print("</html>");
		
		dispatcher = req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
			}
		}
		
		catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 5. close all the "JDBC object"

			try {
				if (con != null) {
					con.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		} // end of finally

	}// end of dopost

}// end of class
