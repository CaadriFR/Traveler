package fr.traveler.geography;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.geography.database.DatabaseManager;
import fr.traveler.geography.entities.City;

public class GeographyManager {
	
	private List<City> cities;
	
	public GeographyManager() {
		this.cities = DatabaseManager.getCities();
		if (cities.isEmpty()) {
			System.out.println("No cities found or there was an error.");
		}
	}

	public List<City> getCitiesByName(String name) {
		List<City> foundCities = new ArrayList<>();
		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(name))
				foundCities.add(city);
		}
		return foundCities;
	}
	
	public City getCity(String name, String department) {

		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(name) && city.getDepartment().equalsIgnoreCase(department))
				return city;
		}
		return null;
	}
	
}