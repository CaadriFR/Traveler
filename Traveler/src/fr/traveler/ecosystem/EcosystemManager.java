package fr.traveler.ecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.geography.entities.City;

public class EcosystemManager {

	private List<Student> students;
	private List<Titular> titulars;

	public EcosystemManager() {
		this.students = new ArrayList<>();
		this.titulars = new ArrayList<>();
	}

	public void addStudent(String firstName, String lastName, int age, City city, String thesisSubject,
			Discipline discipline, int thesisYear) {
		Student student = new Student(firstName, lastName, age, city, thesisSubject, discipline, thesisYear);
		students.add(student);
		System.out.println("Student added successfully: " + student);
	}

	public void addMCF(String firstName, String lastName, int age, City city, Set<Discipline> disciplines,
			int officeNumber) {
		MCF mcf = new MCF(firstName, lastName, age, city, disciplines, officeNumber);
		titulars.add(mcf);
		System.out.println("MCF added successfully: " + mcf);
	}

	public void addResearcher(String firstName, String lastName, int age, City city, Set<Discipline> disciplines,
			int officeNumber) {
		Researcher researcher = new Researcher(firstName, lastName, age, city, disciplines, officeNumber);
		titulars.add(researcher);
		System.out.println("Researcher added successfully: " + researcher);
	}

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

	public List<Student> getStudentsByName(String firstName, String lastName) {
		List<Student> foundStudents = new ArrayList<>();
		for (Student student : students) {
			if (student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName))
				foundStudents.add(student);
		}
		return foundStudents;
	}

	public List<Titular> getTitularsByName(String firstName, String lastName) {
		List<Titular> foundTitulars = new ArrayList<>();
		for (Titular titular : titulars) {
			if (titular.getFirstName().equalsIgnoreCase(firstName) && titular.getLastName().equalsIgnoreCase(lastName))
				foundTitulars.add(titular);
		}
		return foundTitulars;
	}

	public boolean canAssignTitular() {
		if (students.isEmpty() || titulars.isEmpty())
			return false;
		boolean everyStudentsHasTitular = true;
		for (Student student : students) {
			if (student.getTitular() == null) {
				everyStudentsHasTitular = false;
				break;
			}
		}
		boolean titularAvailable = false;
		for (Titular titular : titulars) {
			if (titular instanceof Researcher) {
				titularAvailable = true;
				break;
			} else if (titular instanceof MCF && ((MCF) titular).getSupervisedStudent() == null) {
				titularAvailable = true;
				break;
			}
		}
		return true && !everyStudentsHasTitular && titularAvailable;
	}

	public static void assignTitular(Student student, Titular titular) {
		if (student.getTitular() != null) {
			System.out.println("The student " + student.getFirstName() + " " + student.getLastName() + " (ID="
					+ student.getID() + ") already has a titular !");
			return;
		}
		if (titular instanceof MCF) {
			if (((MCF) titular).getSupervisedStudent() != null) {
				System.out.println("The MCF " + titular.getFirstName() + " " + titular.getLastName()
						+ " (ID="
								+ titular.getID() + ") already supervises a student !");
				return;
			}
			((MCF) titular).setSupervisedStudent(student);
		}
		if (titular instanceof Researcher) {
			((Researcher) titular).addSupervisedStudent(student);
		}
		student.setTitular(titular);
		System.out.println("The student " + student.getFirstName() + " " + student.getLastName() + " (ID="
				+ student.getID() + ") is now supervised by " + titular.getFirstName() + " " + titular.getLastName()
				+ " (ID=" + titular.getID() + ").");
	}

}
