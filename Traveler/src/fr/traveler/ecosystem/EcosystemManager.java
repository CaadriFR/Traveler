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

	public Student getStudentById(int id) {
		for (Student student : students) {
			if (student.getID() == id)
				return student;
		}
		return null;

	}

	public Titular getTitularById(int id) {
		for (Titular titular : titulars) {
			if (titular.getID() == id)
				return titular;
		}
		return null;

	}

	public static void assignTitular(Student student, Titular titular) {
		if (student.getTitular() != null) {
			System.out.println(
					"The student " + student.getFirstName() + " " + student.getLastName() + " already has a titular !");
			return;
		}
		if (titular instanceof MCF) {
			if (((MCF) titular).getSupervisedStudent() != null) {
				System.out.println("The MCF " + titular.getFirstName() + " " + titular.getLastName()
						+ " already supervises a student !");
				return;
			}
			((MCF) titular).setSupervisedStudent(student);
		}
		if (titular instanceof Researcher) {
			((Researcher) titular).addSupervisedStudent(student);
		}
		student.setTitular(titular);
	}

}
