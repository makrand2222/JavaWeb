package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO 
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection con=null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:/mysql", "root", "m@k1");
		
		
		return con;
	}

public static int save(Employee emp) throws ClassNotFoundException, SQLException 
{
	Connection con=getConnection();
	
	PreparedStatement ps= con.prepareStatement("insert into empservlet values(?, ?, ?, ?, ?)");
	String uniqueId = String.valueOf(System.currentTimeMillis() / 1000);
	ps.setString(1, "emp/"+uniqueId);
	ps.setString(2, emp.getName());
	ps.setString(3, emp.getPassword());
	ps.setString(4, emp.getEmail());
	ps.setString(5, emp.getCountry());
	
	
	int status=ps.executeUpdate();
	
	return status;
}

public static List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException 
{
	List<Employee> list=new ArrayList<Employee>();
	
	try(Connection con=EmployeeDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from empservlet");
			ResultSet rs=ps.executeQuery();) 
	{
		
		
		while (rs.next())
		{
			Employee e=new Employee();
			e.setId(rs.getString(1));
			e.setName(rs.getString(2));
			e.setPassword(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setCountry(rs.getString(5));
			
			list.add(e);
			
			
		}
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	return list;
}

	public static Employee getEmployeeById(String id) throws ClassNotFoundException, SQLException
	{
		Employee emp=new Employee();
		
		Connection con=EmployeeDAO.getConnection();
		PreparedStatement ps= con.prepareStatement("select * from empservlet where id=?");
		ps.setString(1, id);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) 
		{
			emp.setId(rs.getString(1));
			emp.setName(rs.getString(2));
			emp.setPassword(rs.getString(3));
			emp.setEmail(rs.getString(4));
			emp.setCountry(rs.getString(5));
		}
		con.close();
 		return emp;
		
	}
	public static int update(Employee e) throws ClassNotFoundException, SQLException
	{
		int status=0;
		Connection con=EmployeeDAO.getConnection();
		PreparedStatement ps=con.prepareStatement("update empservlet set name=?, pwd=?, email=?, country=? where id=?");
		ps.setString(1, e.getId());
		ps.setString(2, e.getName());
		ps.setString(3, e.getPassword());
		ps.setString(4, e.getEmail());
		ps.setString(5, e.getCountry());
		
		status=ps.executeUpdate();
		
		con.close();
		return status;
	}
	
	public static int delete(String id) throws ClassNotFoundException, SQLException {
		int status=0;
		
		Connection con=EmployeeDAO.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from empservlet where id=?");
		ps.setString(1, id);
		
		status=ps.executeUpdate();
		con.close();
		return status;
	}
}




























