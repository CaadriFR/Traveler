package fr.traveler.geography.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.traveler.config.Config;

/**
 * Gère la connexion à la base de données.
 * 
 * @author Adrien Riffaut
 */
public class DatabaseConnection {
	
	/**
     * Établit une connexion à la base de données en utilisant les paramètres de configuration.
     * 
     * @return une connexion à la base de données, ou {@code null} si la connexion échoue
     */
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