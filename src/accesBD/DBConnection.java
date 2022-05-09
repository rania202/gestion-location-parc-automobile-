package accesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection=null ;
	

	private DBConnection() {	}
	
	public static Connection getInstance()
	{     
		try {
			if (connection==null || connection.isClosed())
			{
				
			  try{
				  Class.forName("com.mysql.cj.jdbc.Driver");
			  }
			  catch(Exception e ){
			  e.printStackTrace();}
			 try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				
				 
					}
			 catch (SQLException e) {
				e.printStackTrace();
			}}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return connection;
	}
	
	


}
