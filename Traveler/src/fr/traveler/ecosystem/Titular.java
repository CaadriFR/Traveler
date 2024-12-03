package fr.traveler.ecosystem;

import fr.traveler.geography.City;
import java.util.Set;

public abstract class Titular extends Person {
	
    private Set<Discipline> disciplines;
    private int officeNumber;

    public Titular(String firstName, String lastName, int age, City city, Set<Discipline> disciplines, int officeNumber) {
        super(firstName, lastName, age, city);
        this.disciplines = disciplines;
        this.officeNumber = officeNumber;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", Disciplines=" + disciplines + ", OfficeNumber=" + officeNumber;
    }
}