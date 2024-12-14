package fr.traveler.geography.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.traveler.config.Config;

public class DatabaseConnection {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
			System.out.println("Connected to the database.");
		} catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
		return connection;
	}
}