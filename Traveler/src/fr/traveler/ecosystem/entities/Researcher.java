package fr.traveler.ecosystem.entities;

import java.util.HashSet;
import java.util.Set;

import fr.traveler.geography.entities.City;

public class Researcher extends Titular {
	private Set<Student> supervisedStudents;

	public Researcher(String firstName, String lastName, int age, City city, Set<Discipline> disciplines,
			int officeNumber) {
		super(firstName, lastName, age, city, disciplines, officeNumber);
		this.supervisedStudents = new HashSet<>();
	}

	public Set<Student> getSupervisedStudents() {
		return supervisedStudents;
	}

	public void addSupervisedStudent(Student student) {
		if (!supervisedStudents.contains(student))
			this.supervisedStudents.add(student);
		else
			System.out.println("The student " + student.getFirstName() + " " + student.getLastName()
					+ " is already supervised by " + this.getFirstName() + " " + this.getLastName() + " !");
	}

	@Override
	public String toString() {
		String supervisedStudentsList = "";

		for (Student supervisedStudent : supervisedStudents) {
			if (!supervisedStudentsList.equalsIgnoreCase(""))
				supervisedStudentsList += ", ";
			else
				supervisedStudentsList = ", SupervisedStudents=[";
			supervisedStudentsList += supervisedStudent.getFirstName() + " " + supervisedStudent.getLastName();
		}

		supervisedStudentsList += "]";

		return "Researcher " + super.toString() + supervisedStudentsList + "]";
	}
}