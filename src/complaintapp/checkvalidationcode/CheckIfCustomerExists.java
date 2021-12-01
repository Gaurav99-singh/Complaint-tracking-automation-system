package complaintapp.checkvalidationcode;
import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import complaintapp.database.DbConnection1;

public class CheckIfCustomerExists 
{
	private String id,name,email,address,phone;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CheckIfCustomerExists() {
		super();
	}
	
	public int checkCustomerid()
	{
		String str1="select Customerid from customer where Customerid=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,id);
			rs=ps.executeQuery();
			
			if(rs.next())
				return 1;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
		}
		return 0;
	}
	
	
	public int checkCustomerPhone()
	{
		String str1="select Phoneno from customer where Phoneno=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,phone);
			rs=ps.executeQuery();
			
			if(rs.next())
				return 2;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
		}
		return 0;
	}
	
	public int checkCustomerEmail()
	{
		String str1="select Email from customer where Email=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,email);
			rs=ps.executeQuery();
			
			if(rs.next())
				return 3;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException ee)
			{
				ee.printStackTrace();
			}
		}
		return 0;
	}

	public CheckIfCustomerExists(String id, String name, String email, String address, String phone) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public CheckIfCustomerExists(String id, String email, String phone) 
	{
		super();
		con=DbConnection1.createConnection();
		this.id = id;
		this.email = email;
		this.phone = phone;
	}

}
