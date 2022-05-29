package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet 
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		PrintWriter pw= res.getWriter();
//		Getting info from request
		String name= req.getParameter("name");
		String pwd= req.getParameter("pwd");
		String email= req.getParameter("email");
		String country=req.getParameter("country");
//		Preparing DTO
		Employee emp=new Employee();
		emp.setName(name);
		emp.setPassword(pwd);
		emp.setEmail(email);
		emp.setCountry(country);
		
		try
		{
			int status=EmployeeDAO.save(emp);
			if (status>0)
			{
				pw.print("<p>Record saved successfully</p>");
				req.getRequestDispatcher("Login.html").include(req, res);
			}
			else
			{
				pw.print("Unable to save employee deatails");
			}
		}
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}
}
