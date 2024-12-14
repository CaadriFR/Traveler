package fr.traveler;

import java.util.Scanner;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.geography.GeographyManager;
import fr.traveler.menu.EcosystemMenu;

public class Main {

	public static void main(String[] args) {

		GeographyManager geographyManager = new GeographyManager();
		EcosystemManager ecosystemManager = new EcosystemManager();

		Scanner scanner = new Scanner(System.in);

		EcosystemMenu.displayEcosystemMenu(ecosystemManager, geographyManager, scanner);

		scanner.close();

	}
}
