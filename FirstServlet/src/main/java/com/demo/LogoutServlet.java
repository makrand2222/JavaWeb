package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet
{
	public LogoutServlet()
	{
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		pw.print("<p>Logged out successfully...</p>");
		req.getRequestDispatcher("comman.html").include(req, res);
		req.getRequestDispatcher("Login.html").include(req, res);
		
		Cookie ck=new Cookie("username", "name");
		ck.setMaxAge(0);
		res.addCookie(ck);
		
		Cookie ck1=new Cookie("password", "password");
		ck1.setMaxAge(0);
		res.addCookie(ck1);
		
	}
}
