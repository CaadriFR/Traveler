package fr.traveler;

import java.util.List;

import fr.traveler.geography.City;
import fr.traveler.geography.CityManager;
import fr.traveler.geography.Region;
import fr.traveler.geography.database.DatabaseManager;

public class Main {

	public static void main(String[] args) {
		List<City> cities = DatabaseManager.getCities();
		if (cities.isEmpty()) {
			System.out.println("No cities found or there was an error.");
		} else {
			/*
			 * System.out.println("List of cities:"); for (City city : cities) {
			 * System.out.println(city.toString() + "\nRegion=" +
			 * Region.findRegionByCity(city).name()); }
			 */

			for (Region rg : Region.values()) {
				City capitalCity = CityManager.getCity(rg.getCapitalCity(), cities);
				if (capitalCity != null)
					System.out.println("Capital City of " + rg.name() + " is " + capitalCity.toString());
				else
					System.out.println("Capital City of " + rg.name() + " not found.");
			}
		}
	}

}
