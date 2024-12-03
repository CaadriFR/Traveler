package fr.traveler.ecosystem;

import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;

public class EcosystemManager {

	public static void addTitular(Student student, Titular titular) {
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
