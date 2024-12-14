package fr.traveler.menu;

import java.util.Scanner;

import fr.traveler.ecosystem.entities.Discipline;

/**
 * Utilitaires pour les menus interactifs.
 * 
 * @author Adrien Riffaut
 */
public class MenuUtils {

	/**
	 * Permet à l'utilisateur de sélectionner une discipline parmi celles
	 * disponibles, de manière interactive. Affiche les disciplines disponibles et
	 * invite l'utilisateur à saisir une discipline.
	 * 
	 * @param scanner l'objet {@link Scanner} utilisé pour la saisie utilisateur
	 * @return la discipline sélectionnée par l'utilisateur
	 */
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
