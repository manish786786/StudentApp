package com.app.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class AllStudentsViewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection con=null;
		  Statement stmt=null;
		  ResultSet rs=null;
		  
		  resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
		 
		try {
			
			//load the driver
			Driver driveref = new Driver();
			DriverManager.registerDriver(driveref);
			
			//get DB connection via driver
			String DBurl= "jdbc:mysql://localhost:3306/becm19_db?user=root&password=root";
			con = DriverManager.getConnection(DBurl);
			
			//issue SQL query via connection
			String qry= "select * from student_info si,guardian_info gi "+
			" where si.regno=gi.regno";
			stmt=con.createStatement();
			rs=stmt.executeQuery(qry);
			
			String htmlresp="";
			String s="<html>"
			        +"<head>"
			           +"<meta charset=\"UTF-8\">"
			           +"<title>AllStudent View</title>"
			           +"<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">"
			           +"<link rel=\"stylesheet\" href=\"css/animate.css\">"
			           +"<link rel=\"stylesheet\" href=\"css/style.css\">"
			           +"<script src=\"js/jquery-3.3.1.js\"></script>"
			            +"<script>"
			           +"$(document).ready(function() {"
			           +"$(\"tr\").addClass(\"animated rubberBand\");"
			         +"});"
			         +"</script>"
			       +"</head>"
			+"<body>"
			+"<table align=\"center\">"
			+"<tr bgcolor=\"green\">"
			+"<th>reg.no</th>"
			+"<th>First Name</th>"
			+"<th>Middle Name</th>"
			+"<th>Last Name</th>"
			+"<th>G first name</th>"
			+"<th>G middle name</th>"
			+"<th>G last name</th>"
			+"</tr>";
			
			out.println(s);
			//process the result returned by SQL qry
			while(rs.next()) {
				int regno = rs.getInt("si.regno");
				String firstNM= rs.getString("si.firstname");
				String middleNM= rs.getString("si.middlename");
				String lastNM = rs.getString("si.lastname");
				String gfNM = rs.getString("gi.gfirstname");
				String gmNM = rs.getString("gi.gmiddlename");
				String glNM = rs.getString("gi.glastname");	
			
				htmlresp=
						"<tr>"
						+"<td>"+"<a href=\"http://localhost:8080/StudentApp/studentsearch?regno="+regno+"\">"+regno+"</a>"+"</td>"
						+"<td>"+firstNM+"</td>"
						+"<td>"+middleNM+"</td>"
						+"<td>"+lastNM+"</td>"
						+"<td>"+gfNM+"</td>"
						+"<td>"+gmNM+"</td>"
						+"<td>"+glNM+"</td>"
						+"</tr>";
						

			out.print(htmlresp);
			
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
				
				if(stmt!=null)
				{
					stmt.close();
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
