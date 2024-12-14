package fr.traveler.ecosystem.entities;

import java.util.Set;

import fr.traveler.geography.entities.City;

/**
 * Représente un Maître de Conférences (MCF) dans l'écosystème. Un MCF est un
 * titulaire qui peut superviser un seul étudiant.
 * 
 * @author Adrien Riffaut
 */
public class MCF extends Titular {

	/**
	 * Étudiant supervisé par le MCF (peut être nul si aucun étudiant n'est
	 * supervisé).
	 */
	private Student supervisedStudent;

	/**
	 * Construit un MCF avec les informations spécifiées.
	 * 
	 * @param firstName    le prénom du MCF
	 * @param lastName     le nom de famille du MCF
	 * @param age          l'âge du MCF
	 * @param city         la ville de résidence du MCF
	 * @param disciplines  les disciplines dans lesquelles le MCF travaille
	 * @param officeNumber le numéro de bureau du MCF
	 */
	public MCF(String firstName, String lastName, int age, City city, Set<Discipline> disciplines, int officeNumber) {
		super(firstName, lastName, age, city, disciplines, officeNumber);
	}

	/**
	 * Retourne l'étudiant supervisé par le MCF.
	 * 
	 * @return l'étudiant supervisé, ou null si aucun étudiant n'est supervisé
	 */
	public Student getSupervisedStudent() {
		return supervisedStudent;
	}

	/**
	 * Définit l'étudiant à superviser par le MCF.
	 * 
	 * @param student l'étudiant à superviser
	 */
	public void setSupervisedStudent(Student student) {
		this.supervisedStudent = student;
	}

	/**
	 * Retourne une représentation textuelle du MCF.
	 * 
	 * @return une chaîne décrivant le MCF
	 */
	@Override
	public String toString() {
		String supervisedStudentName = "";
		if (supervisedStudent != null)
			supervisedStudentName += ", Supervised Student=" + supervisedStudent.getFirstName() + " "
					+ supervisedStudent.getLastName() + " (ID=" + supervisedStudent.getID() + ")";
		return "MCF " + super.toString() + supervisedStudentName + "]";
	}

}