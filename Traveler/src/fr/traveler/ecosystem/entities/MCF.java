package fr.traveler.ecosystem.entities;

import java.util.Set;

import fr.traveler.geography.entities.City;

public class MCF extends Titular {

	private Student supervisedStudent;

	public MCF(String firstName, String lastName, int age, City city, Set<Discipline> disciplines, int officeNumber) {
		super(firstName, lastName, age, city, disciplines, officeNumber);
	}

	public Student getSupervisedStudent() {
		return supervisedStudent;
	}

	public void setSupervisedStudent(Student student) {
		this.supervisedStudent = student;
	}

	@Override
	public String toString() {
		String supervisedStudentName = "";
		if (supervisedStudent != null)
			supervisedStudentName += ", SupervisedStudent=" + supervisedStudent.getFirstName() + " "
					+ supervisedStudent.getLastName();
		return "MCF " + super.toString() + supervisedStudentName + "]";
	}

}