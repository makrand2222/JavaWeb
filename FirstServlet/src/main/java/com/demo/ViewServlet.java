package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet ("/ViewServlet")
public class ViewServlet extends HttpServlet
{	
	List<Employee> list;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		pw.println("<b>Total Visitors :"+FilterServlet.count+"</b></br>");
		pw.println("<a href='index.html'>Add new Employee</a>");
		pw.println("<h1>Employee List</h1>");
		
		
		try
		{
			list = EmployeeDAO.getAllEmployees();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
			pw.print("<table border='1' width='100%'");
			pw.print("<tr>"
						+"<th>Employee ID</th>" 
						+ "<th>Name</th>"
						+ "<th>Password</th>"
						+ "<th>Email</th>" 
						+ "<th>Country</th>" 
						+ "<th>Edit</th>" 
						+ "<th>Delete</th>"
						+ "</tr>");
			for (Employee emp :list) 
			{
				pw.print("<tr>"+
							"<td>"+emp.getId()+"</td>"+
							"<td>"+emp.getName()+"</td>"+
							"<td>"+emp.getPassword()+"</td>"+
							"<td>"+emp.getEmail()+"</td>"+
							"<td>"+emp.getCountry()+"</td>"+
							"<td><a href='EditServlet?id="+emp.getId()+"'>Edit</a></td>"+
							"<td><a href='DeleteServlet?id="+emp.getId()+"'>Delete</a></td></tr>"
						);
			}
			pw.println("</table>");
			pw.close();
	}
}



















