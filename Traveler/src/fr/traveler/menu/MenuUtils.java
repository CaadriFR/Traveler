package fr.traveler.menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

	 /**
     * Écrit une solution dans un fichier texte.
     * 
     * @param filePath Le chemin du fichier où enregistrer la solution.
     * @param solution La solution à écrire dans le fichier.
     */
    public static void writeSolutionToFile(String filePath, String solution) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "solution.txt"))) {
            writer.write(solution);
            System.out.println("Solution saved in : " + filePath + "solution.txt");
        } catch (IOException e) {
            System.err.println("Error while saving solution : " + e.getMessage());
        }
    }
}
