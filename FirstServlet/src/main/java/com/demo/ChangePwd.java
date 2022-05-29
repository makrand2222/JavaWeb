package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet
{
	String dbOldPwd;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		
		Employee emp=new Employee();
		
		String OldPwd=req.getParameter("pwd");
		String NewPwd=req.getParameter("npwd");
		String ConNewPwd=req.getParameter("cnpwd");
		
		
		Connection con=null;
		try {
			con=EmployeeDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from empservlet where email='"+emp.getEmail()+"'");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				dbOldPwd=rs.getString("pwd");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
        {
            try {
                con.close();
            } catch (SQLException ex) {
//                Logger.getLogger(Admin.ChangePwd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		if (!OldPwd.equals(dbOldPwd)) 
		{
			pw.print("Old password didnt matched!!! Please try again");
			req.getRequestDispatcher("ChangePwd.html").include(req, resp);
		}
		if (!NewPwd.equals(ConNewPwd)) 
		{
			pw.print("Confirm password didnt matched!!! Please try again");
			req.getRequestDispatcher("ChangePwd.html").include(req, resp);
		}
		else 
		{
			try {
				Connection conn=EmployeeDAO.getConnection();
				
				PreparedStatement ps=conn.prepareStatement("update empservlet set pwd='"+NewPwd+"' where email='"+emp.getEmail()+"'");
				int i=ps.executeUpdate();
				if (i>0) 
				{
					pw.print("Password updated successfully");
					req.getRequestDispatcher("ChangePwd.html").include(req, resp);
				}
				else
				{
					pw.print("Password didn't updated successfully!!! Please try gain.");
					conn.close();
				}
			} catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}





























