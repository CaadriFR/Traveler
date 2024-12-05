package fr.traveler.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.geography.GeographyManager;
import fr.traveler.geography.entities.Region;

public class HamiltonianMenu {

	public static void displayHamiltonianMenu(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		boolean back = false;
		while (!back) {
			System.out.println("\n--- Hamiltonian Cycle Menu ---");
			System.out.println("1. Cycle to visit students in a given discipline");
			System.out.println("2. Cycle to visit researchers over 55 years old");
			System.out.println("3. Cycle to visit all titulars");
			System.out.println("4. Custom cycle");
			System.out.println("5. Go to Ecosystem Management Menu");
			System.out.print("Choose an option: ");

			int choice = -1;
			try {
				choice = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				continue;
			}

			switch (choice) {
			case 1:
				Discipline disciplineFilter = MenuUtils.addDisciplineInteractive(scanner);
				List<Person> filter1 = filterPersons(ecosystemManager, 1, -1, disciplineFilter, null, -1);
				if (filter1.isEmpty()) {
					System.out.println("No persons match the given criteria.");
				} else {
					System.out.println("Filtered persons:");
					for (Person person : filter1) {
						System.out.println(person);
					}
				}
				break;
			case 2:
				List<Person> filter2 = filterPersons(ecosystemManager, 3, 55, null, null, -1);
				if (filter2.isEmpty()) {
					System.out.println("No persons match the given criteria.");
				} else {
					System.out.println("Filtered persons:");
					for (Person person : filter2) {
						System.out.println(person);
					}
				}
				break;
			case 3:
				List<Person> filter3 = filterPersons(ecosystemManager, 2, -1, null, null, -1);
				if (filter3.isEmpty()) {
					System.out.println("No persons match the given criteria.");
				} else {
					System.out.println("Filtered persons:");
					for (Person person : filter3) {
						System.out.println(person);
					}
				}
				break;
			case 4:
				performCustomCycle(ecosystemManager, geographyManager, scanner);
				continue;
			case 5:
				back = true;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				continue;
			}
		}
	}

	private static void performCustomCycle(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		System.out.println("--- Custom Hamiltonian Cycle ---");

		int typeChoice = -1;
		while (typeChoice < 1 || typeChoice > 5) {
			try {
				System.out.println("Choose the type of person to filter:");
				System.out.println("1. Student");
				System.out.println("2. Titular");
				System.out.println("3. Researcher");
				System.out.println("4. MCF");
				System.out.println("5. Everyone");
				System.out.print("Enter your choice: ");
				typeChoice = Integer.parseInt(scanner.nextLine().trim());
				if (typeChoice < 1 || typeChoice > 5) {
					System.out.println("Invalid choice. Please select a number between 1 and 5.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		int minAge = -1;
		while (true) {
			System.out.print("Filter by age? (yes/no): ");
			String filterByAge = scanner.nextLine().trim();
			if (filterByAge.equalsIgnoreCase("yes")) {
				while (minAge < 0) {
					try {
						System.out.print("Enter minimum age: ");
						minAge = Integer.parseInt(scanner.nextLine().trim());
						if (minAge < 0) {
							System.out.println("Age must be a positive number. Please try again.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a valid number.");
					}
				}
				break;
			} else if (filterByAge.equalsIgnoreCase("no")) {
				break;
			}
		}

		Discipline disciplineFilter = null;
		while (true) {
			System.out.print("Filter by discipline? (yes/no): ");
			String filterByDiscipline = scanner.nextLine().trim();
			if (filterByDiscipline.equalsIgnoreCase("yes")) {
				disciplineFilter = MenuUtils.addDisciplineInteractive(scanner);
				break;
			} else if (filterByDiscipline.equalsIgnoreCase("no")) {
				break;
			}
		}

		Region regionFilter = null;
		while (true) {
			System.out.print("Filter by region? (yes/no): ");
			String filterByRegion = scanner.nextLine().trim();
			if (filterByRegion.equalsIgnoreCase("yes")) {
				System.out.println("Available regions:");
				for (Region region : Region.values()) {
					System.out.println("- " + region.name());
				}
				try {
					System.out.print("Enter the region: ");
					String regionInput = scanner.nextLine().trim().toUpperCase();
					regionFilter = Region.valueOf(regionInput);
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid region entered. Please try again.");
				}
			} else if (filterByRegion.equalsIgnoreCase("no")) {
				break;
			}
		}

		int thesisYear = -1;
		if (typeChoice == 1) {
			while (true) {
				System.out.print("Filter by thesis year? (yes/no): ");
				String filterByThesisYear = scanner.nextLine().trim();
				if (filterByThesisYear.equalsIgnoreCase("yes")) {
					while (thesisYear < 1 || thesisYear > 3) {
						try {
							System.out.print("Enter thesis year (1, 2, or 3): ");
							thesisYear = Integer.parseInt(scanner.nextLine().trim());
							if (thesisYear < 1 || thesisYear > 3) {
								System.out.println("Thesis year must be 1, 2, or 3. Please try again.");
							}
						} catch (NumberFormatException e) {
							System.out.println("Invalid input. Please enter a valid number.");
						}
					}
					break;
				} else if (filterByThesisYear.equalsIgnoreCase("no")) {
					break;
				}
			}
		}

		List<Person> filteredPersons = filterPersons(ecosystemManager, typeChoice, minAge, disciplineFilter,
				regionFilter, thesisYear);

		if (filteredPersons.isEmpty()) {
			System.out.println("No persons match the given criteria.");
		} else {
			System.out.println("Filtered persons:");
			for (Person person : filteredPersons) {
				System.out.println(person);
			}
		}
	}

	private static List<Person> filterPersons(EcosystemManager ecosystemManager, int typeChoice, int minAge,
			Discipline disciplineFilter, Region regionFilter, int thesisYear) {
		List<Person> filteredPersons = new ArrayList<>();

		for (Person person : ecosystemManager.getAllPersons()) {
			if (typeChoice == 1 && !(person instanceof Student))
				continue;
			if (typeChoice == 2 && !(person instanceof Titular))
				continue;
			if (typeChoice == 3 && !(person instanceof Researcher))
				continue;
			if (typeChoice == 4 && !(person instanceof MCF))
				continue;

			if (minAge > 0 && person.getAge() < minAge)
				continue;

			if (disciplineFilter != null) {
				if (person instanceof Student) {
					if (!((Student) person).getDiscipline().equals(disciplineFilter))
						continue;
				} else if (person instanceof Titular) {
					if (!((Titular) person).getDisciplines().contains(disciplineFilter))
						continue;
				}
			}

			if (regionFilter != null) {
				if (Region.findRegionByCity(person.getCity()) != regionFilter)
					continue;
			}

			if (person instanceof Student) {
				if (thesisYear != -1 && ((Student) person).getThesisYear() != thesisYear)
					continue;
			}

			filteredPersons.add(person);
		}

		return filteredPersons;
	}

}
