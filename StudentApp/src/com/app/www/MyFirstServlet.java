package com.app.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		
		//java code to generate current date and time
		Date dateref = new Date();
		String currentDate = dateref.toString();
		
		//generate html reponse
		//send the html response to browser via Web server
				out.print("<html>");
		        out.print("<head>");
				out.print("<meta charset=\"UTF-8\">");
				out.print("<title>Current Date</title>");
				out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
				out.print("<link rel=\"stylesheet\" href=\"css/animate.css\">");
				out.print("<link rel=\"stylesheet\" href=\"css/style.css\">");
				out.print("<script src=\"js/jquery-3.3.1.js\"></script>");
				out.print("<script>");
				out.print("$(document).ready(function() {");
				out.print("$(\"h1\").addClass(\"animated zoomIn\");");
				out.print("});");
				out.print("</script>");
				out.print("</head>");
				out.print("<html>");
				out.print("<body>");
				out.print("<h1>");
				out.print("Current Date & Time is:");
				out.print("<font color=\"red\">");
				out.print(currentDate);
				out.print("</font>");
				out.print("</h1>");
				out.print("</body>");
				out.print("</html>");
		
		
	}// end of doget
}// end of class
