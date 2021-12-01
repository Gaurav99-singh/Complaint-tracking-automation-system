package complaintapp.database;
import java.sql.*;

public class DbConnection1 
{
	private static Connection con;//Connection interface
	
	public static Connection createConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//to load driver(Class belong lang package)
			
			//valid till jdk 1.6 only above line if remove then no error and also remove class not found exception
			//(forName->factory method->to create driver class object)
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/complaint_tracking_db","root","root1");
			//Driver manager communicate with type 4 driver
		}
		catch(ClassNotFoundException|SQLException cse)
		{
			cse.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection()
	{
		if(con!=null)
		{
			try
			{
				con.close();
			}
			catch(SQLException sed)
			{
				sed.printStackTrace();
			}
		}
	}
}


/*steps

1-C drive->program x86->my sql->connector->copy and paste mysql-connector-java jar file in a self made package 
complaintapp.jarfiles and then right click on file and set build path->add to path.

2-import java.sql.*;

3-
*/
