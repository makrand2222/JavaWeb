package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

public class FilterServlet implements Filter
{
	static int count;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException 
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String name=req.getParameter("name");
		String password=req.getParameter("pwd");
		String email=req.getParameter("email");
		String country=req.getParameter("country");
		
		if (name==null | name=="" ) 
		{
			pw.print("Please enter valid name.");
		} else if(password==null | password=="") 
		{
			pw.print("Please enter valid password.");
		}
		 else if(email==null | email=="") 
			{
				pw.print("Please enter valid email.");
			}
		 else if(country==null | country=="Select Country") 
			{
				pw.print("Please enter Country.");
			}
		else
		{
			chain.doFilter(req, res);	
			count=++count;
		}
	}
}
                   































