package fr.traveler.geography.entities;

public class City {
	private String name;
	private String postalCode;
	private int population;
	private double area;
	private String department;
	
	private double latitude;
	private double longitude;
	private static final double EARTH_RADIUS = 6371.0;

	public City(String name, String postalCode, int population, double area, String department, double latitude, double longitude) {
		this.name = name;
		this.postalCode = postalCode;
		this.population = population;
		this.area = area;
		this.department = department;
		this.latitude = Math.toRadians(latitude);
		this.longitude = Math.toRadians(longitude);
	}

	public double calculateDistance(City otherCity) {
	    double deltaLatitude = this.latitude - otherCity.latitude;
	    double deltaLongitude = this.longitude - otherCity.longitude;

	    double temp = Math.pow(Math.sin(deltaLatitude / 2), 2) +
	               Math.cos(this.latitude) * Math.cos(otherCity.latitude) *
	               Math.pow(Math.sin(deltaLongitude / 2), 2);
	    double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(temp));

	    return distance;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	@Override
	public String toString() {
		return "City [Name=" + name + ", Postal Code=" + postalCode + ", Population=" + population + ", Area=" + area
				+ " km2, Department=" + department + ", Latitude=" + latitude + ", Longitude=" + longitude + "]";
	}
}
