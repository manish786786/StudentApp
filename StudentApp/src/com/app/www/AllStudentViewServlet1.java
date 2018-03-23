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

public class AllStudentViewServlet1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// send the response to browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		// get the Query String Info

		//requestDispatcher
		RequestDispatcher dispatcher = null;
		
		
		String nextRegNoVal = req.getParameter("nextRegNo");
		

		int fromRegNo = 0;
		int toRegNo = 0;
		int row = 4;

		if (nextRegNoVal == null) {
			fromRegNo = 1;
			toRegNo = 5;
		} else {

			fromRegNo = Integer.parseInt(nextRegNoVal);
			toRegNo = fromRegNo +row;
		}

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
		
				// 3 issue SQL query via connection
				String qry="select * from "
						+" student_info si, "
						+" guardian_info gi "
						+" where si.regno=gi.regno "
						+" and si.regno between ? and ? ";
				
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, fromRegNo);
			pstmt.setInt(2, toRegNo);
			rs = pstmt.executeQuery();
			
			dispatcher = req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
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
				           out.print("$(\"tr\").addClass(\"animated rubberBand\");");
				         out.print("});");
				         out.print("</script>");
				out.print("</head>");
				out.print("<body>");
				out.print("</br>");
				out.print("</br>");
				out.print("</br>");
				out.print("</br>");
				
				out.print("<table  align=\"center\">");
				out.print("<tr bgcolor=\"green\">");
				out.print("<th>reg.no</th>");
				out.print("<th>First Name</th>");
				out.print("<th>Middle Name</th>");
				out.print("<th>Last Name</th>");
				out.print("<th>G first name</th>");
				out.print("<th>G middle name</th>");
				out.print("<th>G last name</th>");
				out.print("<th>Delete</th>");
				
				out.print("</tr>");
				
				//process the result returned by SQL qry
				while(rs.next()) {
					int regno = rs.getInt("si.regno");
					String firstNM= rs.getString("si.firstname");
					String middleNM= rs.getString("si.middlename");
					String lastNM = rs.getString("si.lastname");
					String gfNM = rs.getString("gi.gfirstname");
					String gmNM = rs.getString("gi.gmiddlename");
					String glNM = rs.getString("gi.glastname");	
				
					
							out.print("<tr>");
							out.print("<td>"+"<a href=\"./studentsearch?regno="+regno+"\">"+regno+"</a>"+"</td>");
							out.print("<td>"+firstNM+"</td>");
							out.print("<td>"+middleNM+"</td>");
							out.print("<td>"+lastNM+"</td>");
							out.print("<td>"+gfNM+"</td>");
							out.print("<td>"+gmNM+"</td>");
							out.print("<td>"+glNM+"</td>");
							out.print("<td><a href='./deleted?regno="+rs.getInt("si.regno")+"'>Delete</a></td>");
							out.print("</tr>");
				
				}
		}
		
				catch (SQLException e) {
			
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
							}catch (Exception e) {
								
								e.printStackTrace();
							}

				}//end of finally
			
			
			//pagination: next link
		String nextUrl="./allstudentview?nextRegNo="+(toRegNo+1);
		String backUrl="./allstudentview?nextRegNo="+(fromRegNo-5);
		
				out.print("</table>");
				out.print("</br>");
				out.print("<div>");
				out.print("&nbsp&nbsp&nbsp");
				if(fromRegNo!=1) {
				out.print("<h4 align=\"center\">");
				out.print("<a href=\""+backUrl+"\"> < Previous </a>");
				}
				out.print("&nbsp&nbsp");
				out.print("<h4 align=\"center\">");
				out.print("<a href=\""+nextUrl+"\"> Next > </a>");
				out.print("</h4>");
				out.print("</div>");
				out.print("</body>");
				out.print("</html>");
				
				dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
			
		}
}

	
	

