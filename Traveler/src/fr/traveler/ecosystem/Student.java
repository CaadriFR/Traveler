package fr.traveler.ecosystem;

import fr.traveler.geography.City;

public class Student extends Person {
    private String thesisSubject;
    private Discipline discipline;
    private int thesisYear;
    private Titular titular;

    public Student(String firstName, String lastName, int age, City city, String thesisSubject, Discipline discipline, int thesisYear, Titular titular) {
        super(firstName, lastName, age, city);
        this.thesisSubject = thesisSubject;
        this.discipline = discipline;
        this.thesisYear = thesisYear;
        this.titular = titular;
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
    
    @Override
    public String toString() {
        return super.toString() + ", ThesisSubject=" + thesisSubject + ", Discipline=" + discipline + ", Year=" + thesisYear + ", Titular= " + titular.getFirstName() + " " + titular.getLastName() + "]";
    }
}