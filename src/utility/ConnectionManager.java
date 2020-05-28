package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	 
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException 
	{
		
		Properties properties=null;
		
		properties = loadPropertiesFile();
		final String driver=properties.getProperty("driver");
		final String url=properties.getProperty("url");
		final String username=properties.getProperty("username");
		final String password=properties.getProperty("password");
		
		Class.forName(driver);
		Connection con = null;
		con = DriverManager.getConnection(url, username, password);
		
		if(con != null)
			System.out.println("Connection Established");
		
		return con;
		
	}
	
	public static Properties loadPropertiesFile() throws IOException
	{
		
		Properties prop=new Properties();
		
		InputStream in=ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
		
	}
}
