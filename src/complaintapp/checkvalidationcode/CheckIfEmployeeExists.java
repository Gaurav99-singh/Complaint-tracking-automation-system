package complaintapp.checkvalidationcode;
import java.sql.*;

import complaintapp.database.DbConnection1;

public class CheckIfEmployeeExists 
{
	String id,pass,phone,email;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public CheckIfEmployeeExists()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int checkEmployeeid()
	{
		String str1="select EmployeeId from employee where EmployeeId=?";
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
	
	public int checkEmployeepass()
	{
		String str1="select Password from employee where Password=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,pass);
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
	
	public int checkEmployeephone()
	{
		String str1="select PhoneNo from employee where PhoneNo=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,phone);
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
	
	public int checkEmployeeEmail()
	{
		String str1="select Email from employee where Email=?";
		try
		{
			ps=con.prepareStatement(str1);
			ps.setString(1,email);
			rs=ps.executeQuery();
			
			if(rs.next())
				return 4;
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

	public CheckIfEmployeeExists(String pass, String phone, String email) {
		super();
		con=DbConnection1.createConnection();
		this.pass = pass;
		this.phone = phone;
		this.email = email;
	}

	public CheckIfEmployeeExists(String id, String pass, String phone, String email) 
	{
		super();
		con=DbConnection1.createConnection();
		this.id = id;
		this.pass = pass;
		this.phone = phone;
		this.email = email;
	}

}
