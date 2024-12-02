package fr.traveler.geography;

public class City {
	private String name;
	private String postalCode;
	private int population;
	private double area;
	private String department;

	public City(String name, String postalCode, int population, double area, String department) {
		this.name = name;
		this.postalCode = postalCode;
		this.population = population;
		this.area = area;
		this.department = department;
	}

	@Override
	public String toString() {
		return "City [Name=" + name + ", Postal Code=" + postalCode + ", Population=" + population + ", Area=" + area
				+ " km2, Department=" + department + "]";
	}
}
