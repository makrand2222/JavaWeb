package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ValidateServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw= res.getWriter();
		
		String wel= (String) req.getAttribute("wel");
		pw.println(wel);
		
		Employee emp=(Employee) req.getAttribute("emp");
		pw.println("Employee Details:");
//		pw.println("Id:"+emp.getId());
		pw.println("Name:"+emp.getName());
		
	}
}
