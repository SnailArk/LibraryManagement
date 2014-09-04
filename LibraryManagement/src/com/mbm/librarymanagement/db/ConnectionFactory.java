package com.mbm.librarymanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mbm.librarymanagement.exception.ExceptionCategory;
import com.mbm.librarymanagement.exception.LibraryManagementException;


public class ConnectionFactory {

	
	public Connection getConnection() throws LibraryManagementException {
		Connection connection =null;
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagement?user=root&password=root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			} 
		return connection;
	}
	
	
	public void closeConnection(Connection connection) throws LibraryManagementException{
		if(connection != null) { 
			try {
				connection.close();
			} catch (SQLException e) {
				throw new LibraryManagementException(ExceptionCategory.SYSTEM);
			}	
		}
	}
}
