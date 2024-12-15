package fr.traveler;

import java.util.Scanner;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.geography.GeographyManager;
import fr.traveler.menu.EcosystemMenu;

/**
 * Classe principale du programme.
 * 
 * @author Adrien Riffaut
 * @author Néo Moret
 */
public class Main {
	
	/**
     * Méthode principale du programme.
     * Initialise les gestionnaires nécessaires et affiche le menu de gestion de l'écosystème.
     * 
     * @param args les arguments de ligne de commande (non utilisés)
     */
	public static void main(String[] args) {

		GeographyManager geographyManager = new GeographyManager();
		EcosystemManager ecosystemManager = new EcosystemManager();

		Scanner scanner = new Scanner(System.in);

		EcosystemMenu.displayEcosystemMenu(ecosystemManager, geographyManager, scanner);

		scanner.close();

	}
}
