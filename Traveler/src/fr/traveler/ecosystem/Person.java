package fr.traveler.ecosystem;

import fr.traveler.geography.City;

public abstract class Person {

	private static int personCount = 0;

	private final int id;
	private String firstName;
	private String lastName;
	private int age;
	private City city;

	public Person(String firstName, String lastName, int age, City city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
		this.id = ++personCount;
	}
	
	public City getCity() {
		return this.city;
	}
	
	@Override
	public String toString() {
		return "Person [ID=" + this.id + ", Name=" + this.firstName + " " + this.lastName + ", Age=" + this.age
				+ ", City=" + this.city + "]";
	}
}