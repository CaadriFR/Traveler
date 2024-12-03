package fr.traveler.ecosystem;

import java.util.Set;

import fr.traveler.geography.City;

public class MCF extends Titular {
    private Student supervisedStudent;

    public MCF(String firstName, String lastName, int age, City city, Set<Discipline> disciplines, int officeNumber, Student supervisedStudent) {
        super(firstName, lastName, age, city, disciplines, officeNumber);
        this.supervisedStudent = supervisedStudent;
    }

    public Student getSupervisedStudent() {
        return supervisedStudent;
    }

    @Override
    public String toString() {
        return super.toString() + ", SupervisedStudent=" + supervisedStudent + "]";
    }
}