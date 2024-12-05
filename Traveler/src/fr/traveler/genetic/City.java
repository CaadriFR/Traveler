package fr.traveler.genetic;

import java.util.ArrayList;

/*
 * Cette classe vise à représenter une ville.
 * Une ville possède un nom, une latitude et une longitude.
 * On suppose que la latitude et longitude sont en degrés,
 * mais elles sont converties en radians dans le constructeur.
 */

public class City {

	private String city_name;
	private double latitude; // En degrés
	private double longitude; // En degrés
	final double EARTH_RADIUS = 6371.0;

	// ---------------------------------------------------------------------------------------------
	// Constructor :

	public City(String city_name, double latitude, double longitude) {
		this.city_name = city_name; // nom de la ville
		this.latitude = latitude; // latitude de la ville transformée en radians
		this.longitude = longitude; // longitude de la ville transformée en radians
	}

	// ----------------------------------------------------------------------------------------------

	// Getters :

	public String getName() {
		return this.city_name;
	}

	public double getLatitude() {
		return this.latitude; // En radians
	}

	public double getLongitude() {
		return this.longitude; // En radians
	}

	// ----------------------------------------------------------------------------------------------

	
	// Display Cities of an ArrayList of City
	public static void displayCities(ArrayList<City> individual ) {

		String mystr="";
		for (City city : individual ) {
			mystr += city.getName() + "->";
		}
		mystr +=  individual.get(0).getName();
		System.out.println(mystr);
	}
	
	
	
	// Distance enter this.city and city2 by the Haversine formula

	public double distanceTo(City otherCity) {
		double thisLatitudeRad = Math.toRadians(this.latitude);
        double thisLongitudeRad = Math.toRadians(this.longitude);
        double otherLatitudeRad = Math.toRadians(otherCity.latitude);
        double otherLongitudeRad = Math.toRadians(otherCity.longitude);
        
        double deltaLatitude = otherLatitudeRad - thisLatitudeRad;
        double deltaLongitude = otherLongitudeRad - thisLongitudeRad;

	    double temp = Math.pow(Math.sin(deltaLatitude / 2), 2) +
	               Math.cos(thisLatitudeRad) * Math.cos(otherLatitudeRad) *
	               Math.pow(Math.sin(deltaLongitude / 2), 2);
	    double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(temp));

	    return distance;
	}

	@Override
	public String toString() {
		return (this.getName() + " ");
	}

}
