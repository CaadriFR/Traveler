package fr.traveler.ecosystem.entities;

import fr.traveler.geography.entities.City;

/**
 * Représente un étudiant dans l'écosystème. Chaque étudiant est associé à un
 * sujet de thèse, une discipline, une année de thèse et un titulaire qui est
 * son encadrant.
 * 
 * @author Adrien Riffaut
 */
public class Student extends Person {

	/**
	 * Sujet de la thèse de l'étudiant.
	 */
	private String thesisSubject;

	/**
	 * Discipline de recherche de l'étudiant.
	 */
	private Discipline discipline;

	/**
	 * Année actuelle de la thèse de l'étudiant (1ère, 2ème ou 3ème).
	 */
	private int thesisYear;

	/**
	 * Titulaire encadrant l'étudiant.
	 */
	private Titular titular;

	/**
	 * Construit un étudiant avec les informations spécifiées.
	 * 
	 * @param firstName     le prénom de l'étudiant
	 * @param lastName      le nom de famille de l'étudiant
	 * @param age           l'âge de l'étudiant
	 * @param city          la ville de résidence de l'étudiant
	 * @param thesisSubject le sujet de thèse de l'étudiant
	 * @param discipline    la discipline de recherche de l'étudiant
	 * @param thesisYear    l'année actuelle de la thèse
	 * @param titular       le titulaire encadrant l'étudiant
	 * 
	 * @throws IllegalArgumentException si le titulaire est null
	 * 
	 */
	public Student(String firstName, String lastName, int age, City city, String thesisSubject, Discipline discipline,
			int thesisYear, Titular titular) {
		super(firstName, lastName, age, city);
		this.thesisSubject = thesisSubject;
		this.discipline = discipline;
		this.thesisYear = thesisYear;
		if (titular == null) {
			throw new IllegalArgumentException("A student must have a titular.");
		}
		this.titular = titular;
	}

	/**
	 * Retourne le sujet de thèse de l'étudiant.
	 * 
	 * @return le sujet de thèse
	 */
	public String getThesisSubject() {
		return thesisSubject;
	}

	/**
	 * Retourne la discipline de recherche de l'étudiant.
	 * 
	 * @return la discipline de recherche
	 */
	public Discipline getDiscipline() {
		return discipline;
	}

	/**
	 * Retourne l'année actuelle de la thèse de l'étudiant.
	 * 
	 * @return l'année de thèse
	 */
	public int getThesisYear() {
		return thesisYear;
	}

	/**
	 * Retourne le titulaire encadrant l'étudiant.
	 * 
	 * @return le titulaire encadrant
	 */
	public Titular getTitular() {
		return titular;
	}

	/**
	 * Retourne une représentation textuelle de l'étudiant.
	 * 
	 * @return une chaîne décrivant l'étudiant
	 */
	@Override
	public String toString() {
		String titularName = "";
		if (titular != null)
			titularName += ", Titular=" + titular.getFirstName() + " " + titular.getLastName() + " (ID="
					+ titular.getID() + ")";
		return "Student " + super.toString() + ", Thesis Subject=" + thesisSubject + ", Discipline=" + discipline
				+ ", Year=" + thesisYear + titularName + "]";
	}
}