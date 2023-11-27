package br.com.biblioteca.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	// Database path, port, database name
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/biblioteca";
	
	public static Connection createConnectionToMySQL() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
}
