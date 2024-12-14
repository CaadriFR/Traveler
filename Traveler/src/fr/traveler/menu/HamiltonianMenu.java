package fr.traveler.menu;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.traveler.config.Config;
import fr.traveler.display.FitnessGraph;
import fr.traveler.display.FranceMap;
import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.genetic.GeneticManager;
import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.GeographyManager;
import fr.traveler.geography.entities.City;
import fr.traveler.geography.entities.Region;

public class HamiltonianMenu {

	public static void displayHamiltonianMenu(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		boolean back = false;
		while (!back) {
			System.out.println("\n--- Hamiltonian Cycle Menu ---");
			System.out.println("1. Cycle to visit students in a given discipline");
			System.out.println("2. Cycle to visit researchers over 55 years old");
			System.out.println("3. Cycle to visit everyone");
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
				List<Person> studentByDiscipline = filterPersons(ecosystemManager, 1, -1, disciplineFilter, null, -1,
						false);
				prepareGeneticAlgorithm(ecosystemManager, geographyManager, studentByDiscipline);
				break;
			case 2:
				List<Person> olderResearcher = filterPersons(ecosystemManager, 3, 55, null, null, -1, false);
				prepareGeneticAlgorithm(ecosystemManager, geographyManager, olderResearcher);
				break;
			case 3:
				List<Person> everyone = ecosystemManager.getAllPersons();
				prepareGeneticAlgorithm(ecosystemManager, geographyManager, everyone);
				break;
			case 4:
				performCustomCycle(ecosystemManager, geographyManager, scanner);
				continue;
			case 5:
				back = true;
				break;
			default:
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				continue;
			}
		}
	}

	private static Region addRegionInteractive(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Enter a region: ");
				String regionInput = scanner.nextLine().trim().toUpperCase();
				return Region.valueOf(regionInput);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid region entered. Please try again.");
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

		Set<Region> regionsFilter = new HashSet<>();
		while (true) {
			System.out.print("Filter by region? (yes/no): ");
			String filterByRegion = scanner.nextLine().trim();
			if (filterByRegion.equalsIgnoreCase("yes")) {
				System.out.println("Available regions:");
				for (Region region : Region.values()) {
					if (region != Region.UNKNOWN)
						System.out.println("- " + region.name());
				}

				regionsFilter.add(addRegionInteractive(scanner));

				while (true) {
					System.out.print("Would you like to add another region? (yes/no): ");
					String addAnother = scanner.nextLine().trim();
					if (addAnother.equalsIgnoreCase("yes")) {
						regionsFilter.add(addRegionInteractive(scanner));
					} else if (addAnother.equalsIgnoreCase("no")) {
						break;
					}
				}
				break;
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

		boolean supervisingStudent = false;

		if (typeChoice == 2 || typeChoice == 3 || typeChoice == 4) {
			while (true) {
				System.out.print("Filter titulars supervising students (yes/no): ");
				String filterSupervisingStudent = scanner.nextLine().trim();
				if (filterSupervisingStudent.equalsIgnoreCase("yes")) {
					supervisingStudent = true;
					break;
				} else if (filterSupervisingStudent.equalsIgnoreCase("no")) {
					break;
				}
			}
		}

		List<Person> filteredPersons = filterPersons(ecosystemManager, typeChoice, minAge, disciplineFilter,
				regionsFilter, thesisYear, supervisingStudent);

		prepareGeneticAlgorithm(ecosystemManager, geographyManager, filteredPersons);

	}

	private static List<Person> filterPersons(EcosystemManager ecosystemManager, int typeChoice, int minAge,
			Discipline disciplineFilter, Set<Region> regionsFilter, int thesisYear, boolean supervisingStudent) {
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
					Student student = (Student) person;
					if (!student.getDiscipline().equals(disciplineFilter))
						continue;
				} else if (person instanceof Titular) {
					Titular titular = (Titular) person;
					if (!titular.getDisciplines().contains(disciplineFilter))
						continue;
				}
			}

			if (regionsFilter != null && !regionsFilter.isEmpty()) {
				Region personRegion = Region.findRegionByCity(person.getCity());
				if (personRegion == Region.UNKNOWN || !regionsFilter.contains(personRegion))
					continue;
			}

			if (person instanceof Student && thesisYear != -1) {
				Student student = (Student) person;
				if (student.getThesisYear() != thesisYear)
					continue;
			}

			if (person instanceof Titular && supervisingStudent) {
				if (person instanceof Researcher) {
					Researcher researcher = (Researcher) person;
					if (researcher.getSupervisedStudents().isEmpty()) {
						continue;
					}
				} else if (person instanceof MCF) {
					MCF mcf = (MCF) person;
					if (mcf.getSupervisedStudent() == null) {
						continue;
					}
				}
			}

			filteredPersons.add(person);
		}

		return filteredPersons;
	}

	private static void prepareGeneticAlgorithm(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			List<Person> persons) {
		if (persons.isEmpty()) {
			System.out.println("No persons match the given criteria.");
			return;
		} else if (persons.size() == 1) {
			System.out.println(
					"There aren't enough persons in different cities who match your criteria in the ecosystem.");
			return;
		} else {
			System.out.println("Filtered persons:");
			for (Person person : persons) {
				System.out.println(person);
			}
		}

		List<City> citiesFromPersons = geographyManager.getCitiesFromPersons(persons);

		GeneticManager geneticManager = new GeneticManager();
		geneticManager.startGeneticAlgorithm(citiesFromPersons, Config.SIZE_POPULATION);

		System.out.println("Solution : ");
		Individu solution = geneticManager.getSolution();
		solution.displayCities();
		System.out.println();
		System.out.println("Distance : ");
		System.out.println(solution.getDistance());
		System.out.println("Fitness : ");
		System.out.println(solution.getFitness());

		showMapWithGraph(solution.getCycle(), geneticManager.getFitnessEvolution());
	}

	private static void showMapWithGraph(List<City> cities, List<Double> fitnessValues) {
		cities.add(cities.getFirst());

		JFrame frame = new JFrame("Map and Fitness Graph");
		frame.setLayout(new GridLayout(1, 2));

		FranceMap mapPanel = new FranceMap(cities);

		JPanel graphPanel = new FitnessGraph(fitnessValues);

		frame.add(mapPanel);
		frame.add(graphPanel);

		frame.setSize(1300, 667);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

}
