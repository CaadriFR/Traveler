package fr.traveler.menu;

import java.util.Scanner;

import fr.traveler.ecosystem.entities.Discipline;

public class MenuUtils {

	public static Discipline addDisciplineInteractive(Scanner scanner) {
		System.out.println("Available disciplines:");
		for (Discipline d : Discipline.values()) {
			System.out.println("- " + d.name());
		}

		Discipline discipline = null;
		while (discipline == null) {
			try {
				System.out.print("Enter the discipline: ");
				String disciplineInput = scanner.nextLine().trim().toUpperCase();
				discipline = Discipline.valueOf(disciplineInput);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid discipline entered. Please try again.");
			}
		}
		return discipline;
	}
}
