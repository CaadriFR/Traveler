package fr.traveler.ecosystem.entities;

import fr.traveler.geography.entities.City;

public class Student extends Person {
	private String thesisSubject;
	private Discipline discipline;
	private int thesisYear;
	private Titular titular;

	public Student(String firstName, String lastName, int age, City city, String thesisSubject, Discipline discipline,
			int thesisYear) {
		super(firstName, lastName, age, city);
		this.thesisSubject = thesisSubject;
		this.discipline = discipline;
		this.thesisYear = thesisYear;
	}

	public String getThesisSubject() {
		return thesisSubject;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public int getThesisYear() {
		return thesisYear;
	}

	public Titular getTitular() {
		return titular;
	}
	
	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	@Override
	public String toString() {
		String titularName = "";
		if (titular != null)
			titularName += ", Titular= " + titular.getFirstName() + " " + titular.getLastName();
		return "Student " + super.toString() + ", Thesis Subject=" + thesisSubject + ", Discipline=" + discipline + ", Year="
				+ thesisYear + titularName + "]";
	}
}