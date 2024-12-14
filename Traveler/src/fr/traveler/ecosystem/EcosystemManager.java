package fr.traveler.ecosystem;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.geography.entities.City;

/**
 * Gestionnaire principal de l'écosystème. Cette classe centralise la gestion
 * des entités.
 * 
 * @author Adrien Riffaut
 */
public class EcosystemManager {

	/**
	 * Liste des étudiants dans l'écosystème.
	 */
	private List<Student> students;

	/**
	 * Liste des titulaires dans l'écosystème.
	 */
	private List<Titular> titulars;

	/**
	 * Construit un gestionnaire d'écosystème avec des listes vides de titulaires et
	 * d'étudiants.
	 * 
	 */
	public EcosystemManager() {
		this.students = new ArrayList<>();
		this.titulars = new ArrayList<>();
	}

	/**
	 * Ajoute un étudiant à l'écosystème.
	 * 
	 * @param student l'étudiant à ajouter
	 */
	public void addStudent(Student student) {
		students.add(student);
		System.out.println("Student added successfully: " + student);
	}

	/**
	 * Ajoute un étudiant en créant une instance de Student.
	 * 
	 * @param firstName     prénom de l'étudiant
	 * @param lastName      nom de famille de l'étudiant
	 * @param age           âge de l'étudiant
	 * @param city          ville de résidence
	 * @param thesisSubject sujet de thèse
	 * @param discipline    discipline de recherche
	 * @param thesisYear    année de la thèse (1, 2 ou 3)
	 * @param titular       encadrant de l'étudiant
	 */
	public void addStudent(String firstName, String lastName, int age, City city, String thesisSubject,
			Discipline discipline, int thesisYear, Titular titular) {
		Student student = new Student(firstName, lastName, age, city, thesisSubject, discipline, thesisYear, titular);
		if (assignTitular(student, titular)) {
			students.add(student);
			System.out.println("Student added successfully: " + student);
		}
	}

	/**
	 * Ajoute un maître de conférences (MCF) à l'écosystème.
	 * 
	 * @param mcf le maître de conférences à ajouter
	 */
	public void addMCF(MCF mcf) {
		titulars.add(mcf);
		System.out.println("MCF added successfully: " + mcf);
	}

	/**
	 * Ajoute un chercheur à l'écosystème.
	 * 
	 * @param researcher le chercheur à ajouter
	 */
	public void addResearcher(Researcher researcher) {
		titulars.add(researcher);
		System.out.println("Researcher added successfully: " + researcher);
	}

	/**
	 * Affiche tous les étudiants et titulaires dans l'écosystème.
	 */
	public void showAllPersons() {
		System.out.println("==== Students ====");
		if (students.isEmpty())
			System.out.println("There are no students in the ecosystem.");
		for (Person person : students) {
			System.out.println(person);
		}
		System.out.println("\n==== Titulars ====");
		if (titulars.isEmpty())
			System.out.println("There are no titulars in the ecosystem.");
		for (Person person : titulars) {
			System.out.println(person);
		}
	}

	/**
	 * Retourne la liste de toutes les personnes dans l'écosystème (étudiants et
	 * titulaires).
	 * 
	 * @return une liste de toutes les personnes
	 */
	public List<Person> getAllPersons() {
		List<Person> allPersons = new ArrayList<>();
		allPersons.addAll(students);
		allPersons.addAll(titulars);
		return allPersons;
	}

	/**
	 * Recherche les titulaires de l'écosystem par nom et prénom.
	 * 
	 * @param firstName prénom du titulaire
	 * @param lastName  nom de famille du titulaire
	 * @return une liste de titulaires ayant le nom et prénom précisé
	 */
	public List<Titular> getTitularsByName(String firstName, String lastName) {
		List<Titular> foundTitulars = new ArrayList<>();
		for (Titular titular : titulars) {
			if (titular.getFirstName().equalsIgnoreCase(firstName) && titular.getLastName().equalsIgnoreCase(lastName))
				foundTitulars.add(titular);
		}
		return foundTitulars;
	}

	/**
	 * Vérifie si un titulaire est disponible pour encadrer un étudiant.
	 * 
	 * @return true si un titulaire peut être assigné, false sinon
	 */
	public boolean canAssignTitular() {
		if (titulars.isEmpty())
			return false;

		for (Titular titular : titulars) {
			if ((titular instanceof Researcher)
					|| (titular instanceof MCF && ((MCF) titular).getSupervisedStudent() == null)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Assigne un titulaire à un étudiant.
	 * 
	 * @param student l'étudiant à encadrer
	 * @param titular le titulaire qui sera l'encadrant
	 * @return true si l'encadrement est réussi, false sinon
	 */
	public static boolean assignTitular(Student student, Titular titular) {
		if (titular instanceof MCF) {
			if (((MCF) titular).getSupervisedStudent() != null) {
				System.out.println("The MCF " + titular.getFirstName() + " " + titular.getLastName() + " (ID="
						+ titular.getID() + ") already supervises a student !");
				return false;
			}

			((MCF) titular).setSupervisedStudent(student);

		} else if (titular instanceof Researcher) {
			((Researcher) titular).addSupervisedStudent(student);
		}

		System.out.println("The student " + student.getFirstName() + " " + student.getLastName() + " (ID="
				+ student.getID() + ") is now supervised by " + titular.getFirstName() + " " + titular.getLastName()
				+ " (ID=" + titular.getID() + ").");
		return true;
	}
}
