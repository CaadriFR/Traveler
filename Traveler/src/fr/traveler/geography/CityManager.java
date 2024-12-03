package fr.traveler.geography;

import java.util.List;

public class CityManager {

	public static City getCity(String name, List<City> cities) {

		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(name))
				return city;
		}
		return null;
	}
	
}
