package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		PrintWriter pw =res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("comman.html").include(req, res);
		
		String name=req.getParameter("name");
		String password=req.getParameter("pwd");
		
		  try{
			Connection con=EmployeeDAO.getConnection();
		    PreparedStatement ps=con.prepareStatement("select * from empservlet where name=? and pwd=?");
		    ps.setString(1, name);
		    ps.setString(2, password);
		    ResultSet rs =ps.executeQuery();
		   
//		    while (rs.next()) 
//		    {	
//		    	uname=rs.getString("name");
//			    pwd=rs.getString("pwd");
//			}
		    
		    if (rs.next()) 
		    {
//		    	1) Using Cookie Method
//		    	Cookie ck=new Cookie("username", name);
//		    	Cookie ck1=new Cookie("password", password);
//		    	res.addCookie(ck);
//		    	res.addCookie(ck1);
		    	
//		    	2) Using HttpSession method:
		    	HttpSession session=req.getSession();
		    	session.setAttribute("name", name);
		    	session.setAttribute(password, session); 
		    	pw.println("Welcome :"+name);
//		    	req.getRequestDispatcher("/ViewServlet").include(req, res);
		    	pw.println("<p><a href='ChangePwd.html'>Change Password</a></p>");
			}
		    else
		    {	
		    	pw.println("Invalid credentials, Please try again!!!");
		    	req.getRequestDispatcher("Login.html").include(req, res);
		    }
		    	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
