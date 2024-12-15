package fr.traveler.ecosystem.entities;

import fr.traveler.geography.entities.City;

/**
 * Représente une personne générique dans l'écosystème. Chaque personne a un
 * identifiant unique, un nom, un âge et une ville de résidence. Cette classe
 * est abstraite et destinée à être étendue par des types spécifiques de
 * personnes.
 * 
 * @author Adrien Riffaut
 */
public abstract class Person {

	/**
	 * Le nombre total de personnes créées.
	 */
	private static int personCount = 0;

	/**
	 * L'identifiant unique de la personne.
	 */
	private final int id;

	/**
	 * Le prénom de la personne.
	 */
	private String firstName;

	/**
	 * Le nom de famille de la personne.
	 */
	private String lastName;

	/**
	 * L'âge de la personne.
	 */
	private int age;

	/**
	 * La ville de résidence de la personne.
	 */
	private City city;

	/**
	 * Construit une nouvelle personne avec les informations spécifiées.
	 * 
	 * @param firstName le prénom de la personne
	 * @param lastName  le nom de famille de la personne
	 * @param age       l'âge de la personne
	 * @param city      la ville de résidence de la personne
	 * 
	 * @throws IllegalArgumentException si la ville est null
	 */
	public Person(String firstName, String lastName, int age, City city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		if (city == null) {
			throw new IllegalArgumentException("A person must have a valid city.");
		}
		this.city = city;
		this.id = ++personCount;
	}

	/**
	 * Retourne le nombre total de personnes créées.
	 * 
	 * @return le nombre total de personnes
	 */
	public static int getPersonCount() {
		return personCount;
	}

	/**
	 * Retourne le prénom de la personne.
	 * 
	 * @return le prénom de la personne
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Retourne le nom de famille de la personne.
	 * 
	 * @return le nom de famille de la personne
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Retourne l'identifiant unique de la personne.
	 * 
	 * @return l'identifiant de la personne
	 */
	public int getID() {
		return id;
	}

	/**
	 * Retourne l'âge de la personne.
	 * 
	 * @return l'âge de la personne
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Retourne la ville de résidence de la personne.
	 * 
	 * @return la ville de résidence
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Retourne une représentation textuelle de la personne.
	 * 
	 * @return une chaîne contenant le prénom, le nom de famille et l'identifiant de
	 *         la personne
	 */
	@Override
	public String toString() {
		return "[ID=" + id + ", Name=" + firstName + " " + lastName + ", Age=" + age + ", City=" + city.getName();
	}
}