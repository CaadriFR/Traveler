package fr.traveler.geography;

import java.util.List;

public class CityManager {

	public City getCity(String name, List<City> cities) {

		for (City city : cities) {
			if (city.getName() == name)
				return city;
		}
		return null;
	}

}
