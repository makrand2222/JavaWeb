package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		
		Employee emp=new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setPassword(password);
		emp.setEmail(email);
		emp.setCountry(country);
		
		try {
			int status=EmployeeDAO.update(emp);
			if (status>0)
			{
				resp.sendRedirect("ViewServlet");
			}
			else
			{
				pw.print("Sorry! Unable to update record :(");
				resp.sendRedirect("ViewServlet");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		pw.close();
	}
}


	

























