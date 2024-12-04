package fr.traveler;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.geography.GeographyManager;
import fr.traveler.geography.entities.City;

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
			case 3:
				addStudentInteractive(ecosystemManager, geographyManager, scanner);
				continue;
			case 4:
				addMCFInteractive(ecosystemManager, geographyManager, scanner);
				continue;
			case 5:
				addResearcherInteractive(ecosystemManager, geographyManager, scanner);
				continue;
			case 6:
				addRelationshipInteractive(ecosystemManager, scanner);
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

		System.out.println("Default ecosystem successfully generated !");
	}

	private static City addCityInteractive(GeographyManager geographyManager, Scanner scanner) {
		while (true) {
			System.out.print("Enter the city name where the student resides: ");
			String cityName = scanner.nextLine();
			List<City> foundCities = geographyManager.getCitiesByName(cityName);
			if (foundCities.isEmpty()) {
				System.out.println("City not found in the database. Please verify the name.");
				continue;
			} else if (foundCities.size() > 1) {
				System.out.println("Several " + cityName + " cities have been found:");
				for (City currentCity : foundCities) {
					System.out.println("- " + currentCity.getName() + " " + currentCity.getDepartment());
				}
				System.out.print("Enter the department code of the desired city: ");
				String departmentCode = scanner.nextLine();
				for (City currentCity : foundCities) {
					if (currentCity.getDepartment().equalsIgnoreCase(departmentCode)) {
						return currentCity;
					}
				}
				System.out.println("City not found for the specified department. Please try again.");
			} else {
				return foundCities.getFirst();
			}
		}
	}

	private static Discipline addDisciplineInteractive(Scanner scanner) {
		System.out.println("Available disciplines:");
		for (Discipline d : Discipline.values()) {
			System.out.println("- " + d.name());
		}

		Discipline discipline = null;
		while (discipline == null) {
			try {
				System.out.print("Enter the discipline: ");
				String disciplineInput = scanner.nextLine().toUpperCase();
				discipline = Discipline.valueOf(disciplineInput);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid discipline entered. Please try again.");
			}
		}
		return discipline;
	}

	private static void addStudentInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {

		System.out.println("--- Add a Student ---");
		System.out.print("Enter student's first name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter student's last name: ");
		String lastName = scanner.nextLine();

		int age = -1;
		while (age < 10 || age > 90) {
			try {
				System.out.print("Enter student's age: ");
				age = Integer.parseInt(scanner.nextLine());
				if (age < 10 || age > 90) {
					System.out.println("Age must be between 10 and 90.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		City city = addCityInteractive(geographyManager, scanner);

		if (city == null)
			return;

		System.out.print("Enter the thesis subject: ");
		String thesisSubject = scanner.nextLine();

		Discipline discipline = addDisciplineInteractive(scanner);

		int thesisYear = -1;
		while (thesisYear < 1 || thesisYear > 3) {
			try {
				System.out.print("Enter the thesis year (1, 2, or 3): ");
				thesisYear = Integer.parseInt(scanner.nextLine());
				if (thesisYear < 1 || thesisYear > 3) {
					System.out.println("The thesis year must be 1, 2 or 3.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		ecosystemManager.addStudent(firstName, lastName, age, city, thesisSubject, discipline, thesisYear);
	}

	private static void addTitularInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner, String titularType) {
		System.out.println("--- Add a " + titularType + " ---");
		System.out.print("Enter " + titularType + " first name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter " + titularType + " last name: ");
		String lastName = scanner.nextLine();

		int age = -1;
		while (age < 10 || age > 90) {
			try {
				System.out.print("Enter " + titularType + " age: ");
				age = Integer.parseInt(scanner.nextLine());
				if (age < 10 || age > 90) {
					System.out.println("Age must be between 10 and 90.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		City city = addCityInteractive(geographyManager, scanner);

		if (city == null)
			return;

		Set<Discipline> disciplines = new HashSet<>();
		disciplines.add(addDisciplineInteractive(scanner));
		while (true) {
			System.out.print("Would you like to add a second discipline? (yes/no): ");
			String addSecond = scanner.nextLine();

			if (addSecond.equalsIgnoreCase("yes")) {
				disciplines.add(addDisciplineInteractive(scanner));
				break;
			} else if (addSecond.equalsIgnoreCase("no")) {
				break;
			}
		}

		int officeNumber = -1;
		while (officeNumber < 0) {
			try {
				System.out.print("Enter " + titularType + " office number: ");
				officeNumber = Integer.parseInt(scanner.nextLine());
				if (officeNumber < 0) {
					System.out.println("Office number must be a positive integer.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		if (titularType.equalsIgnoreCase("MCF")) {
			ecosystemManager.addMCF(firstName, lastName, age, city, disciplines, officeNumber);
		} else if (titularType.equalsIgnoreCase("Researcher")) {
			ecosystemManager.addResearcher(firstName, lastName, age, city, disciplines, officeNumber);
		}

	}

	private static void addMCFInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		addTitularInteractive(ecosystemManager, geographyManager, scanner, "MCF");
	}

	private static void addResearcherInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		addTitularInteractive(ecosystemManager, geographyManager, scanner, "Researcher");
	}

	private static void addRelationshipInteractive(EcosystemManager ecosystemManager, Scanner scanner) {

		if (!ecosystemManager.canAssignTitular()) {
			System.out.println("The current ecosystem does not allow new relationships to be added.");
			return;
		}
		Student student = null;
		while (student == null) {
			System.out.print("Enter student's first name: ");
			String firstName = scanner.nextLine();

			System.out.print("Enter student's last name: ");
			String lastName = scanner.nextLine();

			List<Student> foundStudents = ecosystemManager.getStudentsByName(firstName, lastName);
			if (foundStudents.isEmpty()) {
				System.out.println("Student not found in the ecosystem. Please verify the name.");
				continue;
			} else if (foundStudents.size() > 1) {
				System.out.println("Several \"" + firstName + "\" have been found:");
				for (Student currentStudent : foundStudents) {
					System.out.println("- " + currentStudent);
				}
				int studentID = -1;
				while (studentID < 1 || studentID > Person.getPersonCount()) {
					try {
						System.out.print("Enter the id of the desired student: ");
						studentID = Integer.parseInt(scanner.nextLine());
						if (studentID < 1 || studentID > Person.getPersonCount()) {
							System.out.println("The ID number must be in line with existing ids.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a valid id.");
					}
				}

				for (Student currentStudent : foundStudents) {
					if (currentStudent.getID() == studentID)
						student = currentStudent;
				}
				if (student == null)
					System.out.println("ID not found for the specified name. Please try again.");
				else 
					System.out.println("Student \"" + firstName + " " + lastName + "\" (ID="+studentID +") have been found.");
			} else {
				student = foundStudents.getFirst();
			}
		}

		Titular titular = null;
		while (titular == null) {
			System.out.print("Enter titular's first name: ");
			String firstName = scanner.nextLine();

			System.out.print("Enter titular's last name: ");
			String lastName = scanner.nextLine();

			List<Titular> foundTitulars = ecosystemManager.getTitularsByName(firstName, lastName);
			if (foundTitulars.isEmpty()) {
				System.out.println("Titular not found in the ecosystem. Please verify the name.");
				continue;
			} else if (foundTitulars.size() > 1) {
				System.out.println("Several \"" + firstName + " " + lastName + "\" have been found:");
				for (Titular currentTitular : foundTitulars) {
					System.out.println("- " + currentTitular);
				}
				int titularID = -1;
				while (titularID < 1 || titularID > Person.getPersonCount()) {
					try {
						System.out.print("Enter the id of the desired student: ");
						titularID = Integer.parseInt(scanner.nextLine());
						if (titularID < 1 || titularID > Person.getPersonCount()) {
							System.out.println("The ID number must be in line with existing ids.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a valid id.");
					}
				}

				for (Titular currentTitular : foundTitulars) {
					if (currentTitular.getID() == titularID)
						titular = currentTitular;
				}
				if (titular == null)
					System.out.println("ID not found for the specified name. Please try again.");
				else 
					System.out.println("Titular \"" + firstName + " " + lastName + "\" (ID="+titularID +") have been found.");
			} else {
				titular = foundTitulars.getFirst();
			}
		}

		EcosystemManager.assignTitular(student, titular);

	}

	private static void hamiltonianCycleMenu(EcosystemManager manager, GeographyManager geographyManager,
			Scanner scanner) {
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
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				continue;
			}
		}
	}

}
