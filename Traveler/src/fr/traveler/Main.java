package fr.traveler;

import java.util.List;

import fr.traveler.geography.City;
import fr.traveler.geography.database.DatabaseManager;

public class Main {

	public static void main(String[] args) {
		List<City> cities = DatabaseManager.getCities();
		int i = 0;
		if (cities.isEmpty()) {
			System.out.println("No cities found or there was an error.");
		} else {
			System.out.println("List of cities:");
			for (City city : cities) {
				i++;
				System.out.println(city.toString());
			}
		}
		System.out.println("Nombre de villes=" + i);
	}

}
