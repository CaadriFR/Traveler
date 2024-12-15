package fr.traveler.geography;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.ecosystem.entities.Person;
import fr.traveler.geography.database.DatabaseManager;
import fr.traveler.geography.entities.City;

/**
 * Gestionnaire des données géographiques.
 * 
 * @author Adrien Riffaut
 */
public class GeographyManager {

	/**
	 * Liste des villes gérées par le gestionnaire.
	 */
	private List<City> cities;

	/**
	 * Construit un gestionnaire géographique et charge les villes depuis la base de
	 * données. Si aucune ville n'est trouvée, le programme se termine avec une
	 * erreur.
	 */
	public GeographyManager() {
		this.cities = DatabaseManager.getCities();
		if (cities == null || cities.isEmpty()) {
			System.err.println("Error: No cities found or there was an error loading cities from the database.");
			System.exit(1);
		}
	}

	/**
	 * Recherche des villes par leur nom.
	 * 
	 * @param name le nom de la ville à rechercher
	 * @return une liste des villes correspondant au nom donné
	 */
	public List<City> getCitiesByName(String name) {
		List<City> foundCities = new ArrayList<>();
		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(name))
				foundCities.add(city);
		}
		return foundCities;
	}

	/**
	 * Recherche une ville par son nom et son département.
	 * 
	 * @param name       le nom de la ville
	 * @param department le département de la ville
	 * @return la ville correspondant au nom et département donnés, ou {@code null}
	 *         si aucune ville ne correspond
	 */
	public City getCity(String name, String department) {

		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(name) && city.getDepartment().equalsIgnoreCase(department))
				return city;
		}
		return null;
	}

	/**
	 * Extrait une liste des villes associées aux personnes données.
	 * 
	 * @param persons une liste de personnes
	 * @return une liste des villes où résident ces personnes
	 */
	public List<City> getCitiesFromPersons(List<Person> persons) {
		List<City> citiesFromPersons = new ArrayList<>();
		for (Person person : persons) {
			City personCity = person.getCity();
			if (personCity != null && !citiesFromPersons.contains(personCity))
				citiesFromPersons.add(personCity);
		}
		return citiesFromPersons;
	}

}
