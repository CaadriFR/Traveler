package fr.traveler.ecosystem.entities;

import java.util.HashSet;
import java.util.Set;

import fr.traveler.geography.entities.City;

/**
 * Représente un chercheur dans l'écosystème. Un chercheur est un titulaire qui
 * peut superviser un ensemble d'étudiants.
 * 
 * @author Adrien Riffaut
 */
public class Researcher extends Titular {

	/**
	 * Ensemble des étudiants supervisés par le chercheur.
	 */
	private Set<Student> supervisedStudents;

	/**
	 * Construit un nouveau chercheur avec les informations spécifiées.
	 * 
	 * @param firstName    le prénom du chercheur
	 * @param lastName     le nom de famille du chercheur
	 * @param age          l'âge du chercheur
	 * @param city         la ville de résidence du chercheur
	 * @param disciplines  les disciplines du chercheur
	 * @param officeNumber le numéro de bureau du chercheur
	 */
	public Researcher(String firstName, String lastName, int age, City city, Set<Discipline> disciplines,
			int officeNumber) {
		super(firstName, lastName, age, city, disciplines, officeNumber);
		this.supervisedStudents = new HashSet<>();
	}

	/**
	 * Retourne l'ensemble des étudiants supervisés par le chercheur.
	 * 
	 * @return un ensemble contenant les étudiants supervisés
	 */
	public Set<Student> getSupervisedStudents() {
		return supervisedStudents;
	}

	/**
	 * Ajoute un étudiant à l'ensemble des étudiants supervisés par le chercheur.
	 * 
	 * @param student l'étudiant à ajouter
	 */
	public void addSupervisedStudent(Student student) {
		if (!supervisedStudents.contains(student))
			this.supervisedStudents.add(student);
		else
			System.out.println("The student " + student.getFirstName() + " " + student.getLastName()
					+ " is already supervised by " + this.getFirstName() + " " + this.getLastName() + " !");
	}

	/**
	 * Retourne une représentation textuelle du chercheur.
	 * 
	 * @return une chaîne décrivant le chercheur
	 */
	@Override
	public String toString() {
		String supervisedStudentsList = "";

		for (Student supervisedStudent : supervisedStudents) {
			if (!supervisedStudentsList.equalsIgnoreCase(""))
				supervisedStudentsList += ", ";
			else
				supervisedStudentsList = ", Supervised Students=[";
			supervisedStudentsList += supervisedStudent.getFirstName() + " " + supervisedStudent.getLastName() + " (ID="
					+ supervisedStudent.getID() + ")";
		}

		supervisedStudentsList += "]";

		return "Researcher " + super.toString() + supervisedStudentsList + "]";
	}
}