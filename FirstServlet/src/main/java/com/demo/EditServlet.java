package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	Employee emp ;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<h1>Update Employee Details</h1>");
		String id = req.getParameter("id");
		
		try {
			emp = EmployeeDAO.getEmployeeById(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		pw.print("<form action='EditServlet2' method='post'>");
		pw.print("<table>");
		pw.print("<tr><td><input type='hidden' name='id' value='" + emp.getId() + "'/></td></tr>");
		pw.print("<tr><td>Name:<input type='text' name=id value='" + emp.getName() + "'/></td></tr>");
		pw.print("<tr><td>Password:<input type='password' name='password' value='" + emp.getPassword()
				+ "'/></td></tr>");
		pw.print("<tr><td>Email:<input type='email' name='email' value='" + emp.getEmail() + "'/></td></tr>");
		pw.print("<tr><td>Country:</td><td>");
		pw.print("<select name='country' style='width:150px'>");
		pw.print("<option>Select Country</option>");
		pw.print("<option>India</option>");
		pw.print("<option>Norway</option>");
		pw.print("<option>US</option>");
		pw.print("<option>Germany</option>");
		pw.print("<option>Other</option>");
		pw.print("</select>");
		pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'><input type='submit' value='Apply changes'></td></tr>");
		pw.print("</table>");
		pw.print("</form>");

		pw.close();
	}
}
