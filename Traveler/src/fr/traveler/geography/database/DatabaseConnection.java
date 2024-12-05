package fr.traveler.geography.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/projet_bdd";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Salut";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to the database.");
		} catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
		return connection;
	}
}