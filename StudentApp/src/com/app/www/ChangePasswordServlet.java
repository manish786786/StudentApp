package com.app.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class ChangePasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String regNoVal = req.getParameter("regNo");
		String currentPasswordVal = req.getParameter("currentPassword");
		String newPasswordVal = req.getParameter("newPassword");
		String reTypeNewPassVal = req.getParameter("reTypeNewPass");
		
		//requestDispatcher
		RequestDispatcher dispatcher = null;
		
		
		// send the response to browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if (newPasswordVal.equals(reTypeNewPassVal)) {
			
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			
			try {
				// 1 load the driver
				Driver driveref = new Driver();
				DriverManager.registerDriver(driveref);

				// 2 get DB connection via driver
				String DBurl = "jdbc:mysql://localhost:3306/becm19_db?user=root&password=root";
				con = DriverManager.getConnection(DBurl);

				// 3 issue SQL query via connection
				String qry = "update student_otherinfo "
						+" set password=? "
						+" where regno=?"
						+" and password=?";
				
				pstmt=con.prepareStatement(qry);
				pstmt.setString(1,newPasswordVal);
				pstmt.setInt(2,Integer.parseInt(regNoVal));
				pstmt.setString(3,currentPasswordVal);
				int r=pstmt.executeUpdate();
				if(r==1) {
					
					dispatcher = req.getRequestDispatcher("Header.html");
					dispatcher.include(req, resp);
					
					out.print("<html>");
					        out.print("<head>");
					           out.print("<meta charset=\"UTF-8\">");
					           out.print("<title>Success</title>");
					           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
					           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
					           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");

					           out.print("<style>");
					                        out.print("body{");
					                            out.print("background-image: url(\"img/success.jpg\")");
					                        out.print("}");
					                            out.print("</style>");

					           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
					            out.print("<script>");
					           out.print("$(document).ready(function() {");
					           out.print("$(\"h1\").addClass(\"animated zoomInDown\");");
					         out.print("});");
					         out.print("</script>");
					       out.print("</head>");
				out.print("<body>");
				out.print("<br><br><br><br><br>");
				out.print("<h1 align=\"center\"><font color=\"green\"><i> Password Successfully Changed<i></font></h1>");
				out.print("</body>");
				
				out.print("</html>");
				out.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
				dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
				}else {
					dispatcher = req.getRequestDispatcher("Header.html");
					dispatcher.include(req, resp);
					out.print("<html>");
					        out.print("<head>");
					           out.print("<meta charset=\"UTF-8\">");
					           out.print("<title>Success</title>");
					           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
					           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
					           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");

					           out.print("<style>");
					                        out.print("body{");
					                            out.print("background-image: url(\"img/no_reg_present.jpg\")");
					                        out.print("}");
					                            out.print("</style>");

					           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
					            out.print("<script>");
					           out.print("$(document).ready(function() {");
					           out.print("$(\"h1\").addClass(\"animated zoomInDown\");");
					         out.print("});");
					         out.print("</script>");
					       out.print("</head>");
					
					out.print("<body>");
					out.print("<h1 align=\"center\"><font color=\"red\"><i>Invalid Regno or Password</i></font></h1>");
					out.print("</body>");
					
					out.print("</html>");
					
					out.print("<br><br><br><br><br><br><br><br><br><br><br><br><br>");
					dispatcher = req.getRequestDispatcher("Footer.html");
					dispatcher.include(req, resp);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			finally {
				// 5. close all the "JDBC object"

				try {
					if (con != null) {
						con.close();
					}

					if (pstmt != null) {
						pstmt.close();
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

			} // end of finally
				
		}//end of if
		else {
			dispatcher = req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
			out.print("<html>");
			        out.print("<head>");
			           out.print("<meta charset=\"UTF-8\">");
			           out.print("<title>Success</title>");
			           out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
			           out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
			           out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");

			           out.print("<style>");
			                        out.print("body{");
			                            out.print("background-image: url(\"img/no_reg_present.jpg\")");
			                        out.print("}");
			                            out.print("</style>");

			           out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
			            out.print("<script>");
			           out.print("$(document).ready(function() {");
			           out.print("$(\"h3\").addClass(\"animated zoomInDown\");");
			         out.print("});");
			         out.print("</script>");
			       out.print("</head>");
			
			out.print("<html>");
			out.print("<body>");
			out.print("<h3 align=\"center\"><font color=\"red\">New Password and retype new Password in not matching</h3></font>");
			out.print("</body>");
			out.print("</html>");
			
			out.print("<br><br><br><br><br><br><br><br><br><br><br><br><br>");
			dispatcher = req.getRequestDispatcher("Footer.html");
			dispatcher.include(req, resp);
		}
	}//end of dopost
	
}//end of class
