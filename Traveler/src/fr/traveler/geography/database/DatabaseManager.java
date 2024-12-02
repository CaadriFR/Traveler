package fr.traveler.geography.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.traveler.geography.City;

public class DatabaseManager {

	public static List<City> getCities() {
		Connection connection = DatabaseConnection.getConnection();
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();

				String query = "SELECT * FROM villes_france_free";
				ResultSet res = statement.executeQuery(query);
				List<City> cities = new ArrayList<>();
				while (res.next()) {
					String name = res.getString("ville_nom");
					String postalCode = res.getString("ville_code_postal");
					int population = res.getInt("ville_population_2012");
					double area = res.getDouble("ville_surface");
					String department = res.getString("ville_departement");
					City city = new City(name, postalCode, population, area, department);
					cities.add(city);
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