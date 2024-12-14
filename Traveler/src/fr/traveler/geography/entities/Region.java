package fr.traveler.geography.entities;

import java.util.Collections;
import java.util.List;

/**
 * Représente une région géographique de France. Chaque région possède un
 * chef-lieu, un code départemental, une superficie, une population estimée, et
 * une liste de départements qu'elle contient.
 * 
 * @author Adrien Riffaut
 */
public enum Region {

	/**
	 * Région Auvergne-Rhône-Alpes.
	 */
	AUVERGNE_RHONE_ALPES("Lyon", "69", 69711.0, 8197296,
			List.of("01", "03", "07", "15", "26", "38", "42", "43", "63", "69", "73", "74")),

	/**
	 * Région Bourgogne-Franche-Comté.
	 */
	BOURGOGNE_FRANCHE_COMTE("Dijon", "21", 47784.0, 2810480, List.of("21", "25", "39", "58", "70", "71", "89", "90")),

	/**
	 * Région Bretagne.
	 */
	BRETAGNE("Rennes", "35", 27208.0, 3379224, List.of("22", "29", "35", "56")),

	/**
	 * Région Centre-Val de Loire.
	 */
	CENTRE_VAL_DE_LOIRE("Orléans", "45", 39151.0, 2559078, List.of("18", "28", "36", "37", "41", "45")),

	/**
	 * Région Corse.
	 */
	CORSE("Ajaccio", "2A", 8680.0, 351255, List.of("2A", "2B")),

	/**
	 * Région Grand-Est.
	 */
	GRAND_EST("Strasbourg", "67", 57433.0, 5555262,
			List.of("08", "10", "51", "52", "54", "55", "57", "67", "68", "88")),

	/**
	 * Région Hauts-De-France.
	 */
	HAUTS_DE_FRANCE("Lille", "59", 31813.0, 5980697, List.of("02", "59", "60", "62", "80")),

	/**
	 * Région Ile-De-France.
	 */
	ILE_DE_FRANCE("Paris", "75", 12011.0, 12358932, List.of("75", "77", "78", "91", "92", "93", "94", "95")),

	/**
	 * Région Normandie.
	 */
	NORMANDIE("Rouen", "76", 29906.0, 3317023, List.of("14", "27", "50", "61", "76")),

	/**
	 * Région Nouvelle-Aquitaine.
	 */
	NOUVELLE_AQUITAINE("Bordeaux", "33", 84061.0, 6110365,
			List.of("16", "17", "19", "23", "24", "33", "40", "47", "64", "79", "86", "87")),

	/**
	 * Région Occitanie.
	 */
	OCCITANIE("Toulouse", "31", 72724.0, 6101005,
			List.of("09", "11", "12", "30", "31", "32", "34", "46", "48", "65", "66", "81", "82")),

	/**
	 * Région Pays-De-La-Loire.
	 */
	PAYS_DE_LA_LOIRE("Nantes", "44", 32082.0, 3907426, List.of("44", "49", "53", "72", "85")),

	/**
	 * Région Provence-Alpes-Cote-D'Azur.
	 */
	PROVENCE_ALPES_COTE_D_AZUR("Marseille", "13", 31400.0, 5160091, List.of("04", "05", "06", "13", "83", "84")),

	/**
	 * Région Inconnue.
	 */
	UNKNOWN("Unknown", "", 0, 0, Collections.emptyList());

	/**
	 * Chef-lieu de la région.
	 */
	private final String capitalCity;

	/**
	 * Code du département du chef-lieu.
	 */
	private final String capitalDepartment;

	/**
	 * Superficie de la région en kilomètres carrés.
	 */
	private final double area;

	/**
	 * Population de la région.
	 */
	private final int population;

	/**
	 * Liste des départements composant la région.
	 */
	private final List<String> departments;

	/**
	 * Construit une région avec les informations spécifiées.
	 * 
	 * @param capitalCity               chef-lieu de la région
	 * @param capitalDepartment code du département du chef-lieu
	 * @param area                  superficie de la région en kilomètres carrés
	 * @param population            population de la région
	 * @param departments           liste des départements composant la région
	 */
	Region(String capitalCity, String capitalDepartment, double area, int population, List<String> departments) {
		this.capitalCity = capitalCity;
		this.capitalDepartment = capitalDepartment;
		this.area = area;
		this.population = population;
		this.departments = departments;
	}

	/**
	 * Retourne le chef-lieu de la région.
	 * 
	 * @return le chef-lieu
	 */
	public String getCapitalCity() {
		return capitalCity;
	}

	/**
	 * Retourne le code du département du chef-lieu.
	 * 
	 * @return le code du département
	 */
	public String getCapitalDepartment() {
		return capitalDepartment;
	}

	/**
	 * Trouve la région correspondant à une ville donnée.
	 * 
	 * @param city la ville pour laquelle rechercher la région
	 * @return la région contenant la ville, ou {@code Region.UNKNOWN} si aucune
	 *         région ne correspond
	 */
	public static Region findRegionByCity(City city) {
		for (Region region : values()) {
			if (region.departments.contains(city.getDepartment())) {
				return region;
			}
		}
		return Region.UNKNOWN;
	}

	/**
	 * Retourne une représentation textuelle de la région.
	 * 
	 * @return une chaîne décrivant la région
	 */
	@Override
	public String toString() {
		return "Region [Name=" + name() + ", Capital City=" + capitalCity + ", Capital Department=" + capitalDepartment
				+ ", Area=" + area + " km², Population=" + population + ", Departments=" + departments + "]";
	}
}