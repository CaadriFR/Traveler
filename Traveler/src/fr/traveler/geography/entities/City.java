package fr.traveler.geography.entities;

/**
 * Représente une ville avec ses informations géographiques et démographiques.
 * 
 * @author Adrien Riffaut
 */
public class City {

	/**
	 * Nom de la ville.
	 */
	private String name;

	/**
	 * Code postal de la ville.
	 */
	private String postalCode;

	/**
	 * Population de la ville.
	 */
	private int population;

	/**
	 * Superficie de la ville en kilomètres carrés.
	 */
	private double area;

	/**
	 * Département auquel appartient la ville.
	 */
	private String department;

	/**
	 * Latitude de la ville.
	 */
	private double latitude;

	/**
	 * Longitude de la ville.
	 */
	private double longitude;

	/**
	 * Rayon de la Terre en kilomètres.
	 */
	private static final double EARTH_RADIUS = 6371.0;

	/**
	 * Construit une nouvelle instance de City avec les informations spécifiées.
	 * 
	 * @param name       nom de la ville
	 * @param postalCode code postal de la ville
	 * @param population population de la ville
	 * @param area       superficie de la ville en kilomètres carrés
	 * @param department département de la ville
	 * @param latitude   latitude de la ville
	 * @param longitude  longitude de la ville
	 */
	public City(String name, String postalCode, int population, double area, String department, double latitude,
			double longitude) {
		this.name = name;
		this.postalCode = postalCode;
		this.population = population;
		this.area = area;
		this.department = department;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Retourne la latitude de la ville.
	 * 
	 * @return la latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Retourne la longitude de la ville.
	 * 
	 * @return la longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Retourne le nom de la ville.
	 * 
	 * @return le nom de la ville
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourne le département de la ville.
	 * 
	 * @return le département
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Calcule la distance entre cette ville et une autre en utilisant la formule
	 * haversine du projet bdd.
	 * 
	 * @param other la ville cible
	 * @return la distance en kilomètres entre les deux villes
	 */
	public double distanceTo(City otherCity) {
		double thisLatitudeRad = Math.toRadians(this.latitude);
		double thisLongitudeRad = Math.toRadians(this.longitude);
		double otherLatitudeRad = Math.toRadians(otherCity.latitude);
		double otherLongitudeRad = Math.toRadians(otherCity.longitude);

		double deltaLatitude = otherLatitudeRad - thisLatitudeRad;
		double deltaLongitude = otherLongitudeRad - thisLongitudeRad;

		double temp = Math.pow(Math.sin(deltaLatitude / 2), 2)
				+ Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad) * Math.pow(Math.sin(deltaLongitude / 2), 2);
		double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(temp));

		return distance;
	}

	/**
	 * Retourne une représentation textuelle de la ville.
	 * 
	 * @return une chaîne contenant les informations de la ville
	 */
	@Override
	public String toString() {
		return "City [Name=" + name + ", Postal Code=" + postalCode + ", Population=" + population + ", Area=" + area
				+ " km2, Department=" + department + ", Latitude=" + latitude + ", Longitude=" + longitude + "]";
	}
}
