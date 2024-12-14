package fr.traveler.geography.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.traveler.geography.entities.City;

/**
 * Gère les interactions avec la base de données.
 * 
 * @author Adrien Riffaut
 */
public class DatabaseManager {

	 /**
     * Récupère toutes les villes de la base de données.
     * Les informations sont extraites de la table {@code villes_france_free}.
     * 
     * @return une liste des villes récupérées, ou une liste vide si aucune ville n'est trouvée ou en cas d'erreur
     */
	public static List<City> getCities() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();

				String query = "SELECT * FROM villes_france_free";
				ResultSet res = statement.executeQuery(query);
				List<City> cities = new ArrayList<>();
				int count = 0;
				while (res.next()) {
					String name = res.getString("ville_nom_reel");
					String postalCode = res.getString("ville_code_postal");
					int population = res.getInt("ville_population_2012");
					double area = res.getDouble("ville_surface");
					String department = res.getString("ville_departement");
					double latitude = res.getDouble("ville_latitude_deg");
					double longitude = res.getDouble("ville_longitude_deg");
					City city = new City(name, postalCode, population, area, department, latitude, longitude);
					cities.add(city);
					count++;
				}
				System.out.println("Number of cities=" + count);
				try {
					connection.close();
					System.out.println("Connection to the database closed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return cities;

			} catch (SQLException e) {
				e.printStackTrace();
				return Collections.emptyList();
			}
		} else {
			return Collections.emptyList();
		}
	}

}
