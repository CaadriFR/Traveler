package fr.traveler.ecosystem.entities;

import java.util.Set;

import fr.traveler.geography.entities.City;

/**
 * Représente un titulaire dans l'écosystème. Un titulaire est une personne
 * travaillant sur une ou plusieurs disciplines et disposant d'un numéro de
 * bureau.
 * 
 * @author Adrien Riffaut
 */
public abstract class Titular extends Person {

	/**
	 * Ensemble des disciplines dans lesquelles le titulaire travaille.
	 */
	private Set<Discipline> disciplines;

	/**
	 * Numéro de bureau du titulaire.
	 */
	private int officeNumber;

	/**
	 * Construit un titulaire avec les informations spécifiées.
	 * 
	 * @param firstName    le prénom du titulaire
	 * @param lastName     le nom de famille du titulaire
	 * @param age          l'âge du titulaire
	 * @param city         la ville de résidence du titulaire
	 * @param disciplines  les disciplines dans lesquelles le titulaire travaille
	 * @param officeNumber le numéro de bureau du titulaire
	 */
	public Titular(String firstName, String lastName, int age, City city, Set<Discipline> disciplines,
			int officeNumber) {
		super(firstName, lastName, age, city);
		this.disciplines = disciplines;
		this.officeNumber = officeNumber;
	}

	/**
	 * Retourne l'ensemble des disciplines dans lesquelles le titulaire travaille.
	 * 
	 * @return un ensemble contenant les disciplines
	 */
	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	/**
	 * Retourne le numéro de bureau du titulaire.
	 * 
	 * @return le numéro de bureau
	 */
	public int getOfficeNumber() {
		return officeNumber;
	}

	/**
	 * Retourne une représentation textuelle du titulaire.
	 * 
	 * @return une chaîne décrivant le titulaire
	 */
	@Override
	public String toString() {
		return super.toString() + ", Disciplines=" + disciplines + ", Office Number=" + officeNumber;
	}
}