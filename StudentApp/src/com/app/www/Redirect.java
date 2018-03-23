package com.app.www;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String val = req.getParameter("redirect");
		String url=null;
		
		RequestDispatcher dispatcher=null;
		
		if(val.equals("home")) {
			
			url="loggedin";
		}
		else if(val.equals("search")) {
			
			url="./Search.html";
			
		}
		else if(val.equals("changePassword")){
			url="./ChangePassword.html";
		}
		else if(val.equals("logout")) {	
			url="#";
		}
		
		else if(val.equals("create")) {	
			url="./CreateProfile.html";
		}
		

		dispatcher = req.getRequestDispatcher("Header.html");
		dispatcher.include(req, resp);
		
		dispatcher = req.getRequestDispatcher(url);
		dispatcher.include(req, resp);
		
		
		dispatcher = req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
	}
	
	
}
