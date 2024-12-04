package fr.traveler.ecosystem.entities;

import fr.traveler.geography.entities.City;

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
	
	public static int getPersonCount() {
		return personCount;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getID() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public City getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "[ID=" + id + ", Name=" + firstName + " " + lastName + ", Age=" + age + ", City=" + city.getName();
	}
}