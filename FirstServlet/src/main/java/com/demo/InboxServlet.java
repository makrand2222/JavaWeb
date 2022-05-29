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

@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet 
{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("comman.html").include(req, res);
		
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		
		try {
			Connection con=EmployeeDAO.getConnection();
		    PreparedStatement ps=con.prepareStatement("select * from empservlet where name='"+name+"'and pwd='"+password+"'");
		    ResultSet rs =ps.executeQuery();
		    String uname=rs.getString(1);
		    String pwd=rs.getString(2);
		    
		    Cookie[] ar=req.getCookies();
	    	String name1=ar[0].getValue();
	    	String pwd1=ar[1].getValue();
	    	
		    if (name1.equals(uname) && pwd1.equals(pwd) ) 
		    {
		    	pw.println("Hello, "+name);
		    	pw.print("Welcome to your inbox");
			}
		    else
		    {
		    	pw.println("you need to login first");
		    	req.getRequestDispatcher("Login.html").include(req, res);
		    }
		    	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
