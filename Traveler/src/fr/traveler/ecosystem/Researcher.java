package fr.traveler.ecosystem;

import java.util.Set;

import fr.traveler.geography.City;

public class Researcher extends Titular {
    private Set<Student> supervisedStudents;

    public Researcher(String firstName, String lastName, int age, City city, Set<Discipline> disciplines, int officeNumber, Set<Student> supervisedStudents) {
        super(firstName, lastName, age, city, disciplines, officeNumber);
        this.supervisedStudents = supervisedStudents;
    }

    public Set<Student> getSupervisedStudents() {
        return supervisedStudents;
    }

    @Override
    public String toString() {
        return super.toString() + ", SupervisedStudents=" + supervisedStudents + "]";
    }
}