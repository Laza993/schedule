package Schedule.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static final String DATABASE  = "localhost:3306/schedulee";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Laza993";
	
	private static Connection connection;
	
	public static void open() throws Exception {
//		loading mySql driver
		Class.forName("com.mysql.jdbc.Driver");
//		connecting
		connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
	}
	
	public static Connection getConnection() throws Exception{
		if(connection == null || connection.isClosed()) {
			throw new Exception("Connection does not exist");
		}else {
			return connection;
		}
	}
	
	public static void close() throws Exception {
		connection.close();
	}
}
