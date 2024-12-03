package fr.traveler;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.geography.GeographyManager;

public class Main {

	public static void main(String[] args) {

		GeographyManager geographyManager = new GeographyManager();
		EcosystemManager ecosystemManager = new EcosystemManager();

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		boolean defaultEcosystem = false;

		while (!exit) {
			System.out.println("\n--- Ecosystem Management Menu ---");
			System.out.println("1. Generate the default ecosystem");
			System.out.println("2. View the entire ecosystem");
			System.out.println("3. Add a student");
			System.out.println("4. Add an MCF");
			System.out.println("5. Add a Researcher");
			System.out.println("6. Add a relationship between a student and a titular");
			System.out.println("7. Go to Hamiltonian Cycle Menu");
			System.out.println("8. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			System.out.println();
			
			switch (choice) {
			case 1:
				if (defaultEcosystem) {
					System.out.println("The default ecosystem has already been generated !");
				} else {
					setupDefaultEcosystem(ecosystemManager, geographyManager);
					defaultEcosystem = true;
				}
				continue;
			case 2:
				ecosystemManager.showAllPersons();
				continue;
			case 7:
				hamiltonianCycleMenu(ecosystemManager, geographyManager, scanner);
				continue;
			case 8:
				exit = true;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				continue;
			}
			scanner.close();
		}
	}

	private static void setupDefaultEcosystem(EcosystemManager ecosystemManager, GeographyManager geographyManager) {
		Discipline mathematics = Discipline.MATHEMATICS;
		Discipline computerScience = Discipline.COMPUTER_SCIENCE;

		Set<Discipline> disciplinesForMCF = new HashSet<>(List.of(mathematics));
		Set<Discipline> disciplinesForResearcher = new HashSet<>(List.of(mathematics, computerScience));

		ecosystemManager.addMCF("Alice", "Dupont", 40, geographyManager.getCity("Lille", "59"), disciplinesForMCF, 101);

		ecosystemManager.addResearcher("Bob", "Martin", 55, geographyManager.getCity("Paris", "75"),
				disciplinesForResearcher, 202);

		ecosystemManager.addStudent("Charles", "Leclerc", 25, geographyManager.getCity("Lyon", "69"), "Algebra",
				mathematics, 2);

		ecosystemManager.addStudent("Diane", "Moreau", 28, geographyManager.getCity("Rouen", "76"), "AI",
				computerScience, 3);

		EcosystemManager.assignTitular(ecosystemManager.getStudentById(3), ecosystemManager.getTitularById(2));
		EcosystemManager.assignTitular(ecosystemManager.getStudentById(4), ecosystemManager.getTitularById(2));

		System.out.println("Default ecosystem successfully generated !");
	}
	
	private static void hamiltonianCycleMenu(EcosystemManager manager, GeographyManager geographyManager, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Hamiltonian Cycle Menu ---");
            System.out.println("1. Cycle to visit students in a given field");
            System.out.println("2. Cycle to visit researchers over 55 years old");
            System.out.println("3. Cycle to visit all titulars");
            System.out.println("4. Custom cycle (enter your criteria)");
            System.out.println("5. Go to Ecosystem Management Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
            case 5:
            	back = true;
                default:
                	System.out.println("Invalid option. Please try again.");
                	continue;
            }
        }
    }

}
