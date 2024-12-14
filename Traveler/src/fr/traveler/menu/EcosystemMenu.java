package fr.traveler.menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Person;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.ecosystem.entities.Titular;
import fr.traveler.geography.GeographyManager;
import fr.traveler.geography.entities.City;

/**
 * Gère les menus interactifs pour la gestion de l'écosystème.
 * Permet de modifier l'écosystème.
 * 
 * @author Adrien Riffaut
 */
public class EcosystemMenu {

	/**
     * Affiche le menu principal pour la gestion de l'écosystème.
     * 
     * @param ecosystemManager le gestionnaire de l'écosystème
     * @param geographyManager le gestionnaire géographique
     * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
     */
	public static void displayEcosystemMenu(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		boolean exit = false;
		boolean defaultEcosystem = false;

		while (!exit) {
			System.out.println("\n--- Ecosystem Management Menu ---");
			System.out.println("1. Generate the default ecosystem");
			System.out.println("2. View the entire ecosystem");
			System.out.println("3. Add a student");
			System.out.println("4. Add an MCF");
			System.out.println("5. Add a Researcher");
			System.out.println("6. Go to Hamiltonian Cycle Menu");
			System.out.println("7. Exit");
			System.out.print("Choose an option: ");

			int choice = -1;
			try {
				choice = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 7.");
				continue;
			}

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
				HamiltonianMenu.displayHamiltonianMenu(ecosystemManager, geographyManager, scanner);
				continue;
			case 7:
				exit = true;
				break;
			default:
				System.out.println("Invalid input. Please enter a number between 1 and 7.");
				continue;
			}
		}
	}

	/**
     * Configure l'écosystème par défaut en ajoutant des chercheurs et des étudiants.
     * 
     * @param ecosystemManager le gestionnaire de l'écosystème
     * @param geographyManager le gestionnaire géographique
     */
	private static void setupDefaultEcosystem(EcosystemManager ecosystemManager, GeographyManager geographyManager) {
		Discipline mathematics = Discipline.MATHEMATICS;
		Discipline computerScience = Discipline.COMPUTER_SCIENCE;
		
		Set<Discipline> researcherDiscipline = new HashSet<>(Arrays.asList(mathematics, computerScience));
		 
		Researcher researcher = new Researcher("Laurent", "Martin", 43, geographyManager.getCity("Lisieux", "14"), researcherDiscipline, 30);
		ecosystemManager.addResearcher(researcher);

		ecosystemManager.addStudent("Camille", "Richard", 23, geographyManager.getCity("Metz", "57"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Diane", "Lemoine", 22, geographyManager.getCity("Reims", "51"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Etienne", "Baron", 20, geographyManager.getCity("Lille", "59"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Alice", "Durand", 21, geographyManager.getCity("Calais", "62"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Benjamin", "Lemoine", 23, geographyManager.getCity("Dieppe", "76"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Charlotte", "Bertrand", 22, geographyManager.getCity("Le Havre", "76"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("David", "Rousseau", 24, geographyManager.getCity("Cherbourg-Octeville", "50"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Louis", "Gonzalez", 22, geographyManager.getCity("Biarritz", "64"), "Machine Learning", computerScience, 2, researcher);
		ecosystemManager.addStudent("Elisa", "Martin", 21, geographyManager.getCity("Strasbourg", "67"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Paul", "Dupont", 23, geographyManager.getCity("Paris", "75"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Julien", "Perrin", 21, geographyManager.getCity("Orléans", "45"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Clara", "Petit", 22, geographyManager.getCity("Lyon", "69"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Mathieu", "Girard", 23, geographyManager.getCity("Toulouse", "31"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Emma", "Simon", 20, geographyManager.getCity("Clermont-Ferrand", "63"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Hugo", "Lefevre", 24, geographyManager.getCity("Marseille", "13"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Lucas", "Morel", 22, geographyManager.getCity("Nice", "06"), "Machine Learning", computerScience, 2, researcher);
		ecosystemManager.addStudent("Juliette", "Fournier", 23, geographyManager.getCity("Bordeaux", "33"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Victor", "Mercier", 21, geographyManager.getCity("Grenoble", "38"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Manon", "Lambert", 22, geographyManager.getCity("Limoges", "87"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Nicolas", "Faure", 23, geographyManager.getCity("Dijon", "21"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Amandine", "Blanc", 24, geographyManager.getCity("Angers", "49"), "Artificial Intelligence", computerScience, 3, researcher);
		ecosystemManager.addStudent("Sarah", "Chevalier", 22, geographyManager.getCity("Poitiers", "86"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Thomas", "Lemoine", 21, geographyManager.getCity("Perpignan", "66"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Hélène", "Moreau", 20, geographyManager.getCity("Rennes", "35"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Florian", "Bertrand", 23, geographyManager.getCity("Tours", "37"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Camille", "Delacroix", 24, geographyManager.getCity("Annecy", "74"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Amélie", "Bonnet", 22, geographyManager.getCity("Valence", "26"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Guillaume", "Faure", 23, geographyManager.getCity("Nancy", "54"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Claire", "Renard", 24, geographyManager.getCity("Bayonne", "64"), "Machine Learning", computerScience, 2, researcher);
		ecosystemManager.addStudent("Mathilde", "Lopez", 20, geographyManager.getCity("Troyes", "10"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Xavier", "Dumont", 21, geographyManager.getCity("Besançon", "25"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Sophie", "Roux", 23, geographyManager.getCity("Le Mans", "72"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Adrien", "Arnaud", 22, geographyManager.getCity("Nîmes", "30"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Louis", "Martinez", 24, geographyManager.getCity("La Rochelle", "17"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Eva", "Blanchard", 20, geographyManager.getCity("Pau", "64"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Yann", "Perez", 21, geographyManager.getCity("Aix-en-Provence", "13"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Elodie", "Vidal", 22, geographyManager.getCity("Béziers", "34"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Chloé", "Pons", 21, geographyManager.getCity("Bastia", "2B"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Gaëtan", "Moulin", 24, geographyManager.getCity("Avignon", "84"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Justine", "Navarro", 23, geographyManager.getCity("Albi", "81"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Kevin", "Dupuis", 20, geographyManager.getCity("Tarbes", "65"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Alice", "Lemoine", 21, geographyManager.getCity("Chambéry", "73"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Pierre", "Dubois", 22, geographyManager.getCity("Blois", "41"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Sarah", "Baron", 23, geographyManager.getCity("Chartres", "28"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Léa", "Fabre", 24, geographyManager.getCity("Mulhouse", "68"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Antoine", "Charpentier", 22, geographyManager.getCity("Agen", "47"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Sophie", "Collin", 21, geographyManager.getCity("Ajaccio", "2A"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Léo", "Dubois", 23, geographyManager.getCity("Angoulême", "16"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Elena", "Germain", 24, geographyManager.getCity("Aurillac", "15"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Bastien", "Perrot", 20, geographyManager.getCity("Belfort", "90"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Chloé", "Raymond", 22, geographyManager.getCity("Brive-la-Gaillarde", "19"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Romain", "Vincent", 23, geographyManager.getCity("Châteauroux", "36"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Alice", "Martinez", 21, geographyManager.getCity("Cholet", "49"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Nathan", "Rodriguez", 20, geographyManager.getCity("Dunkerque", "59"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Pauline", "Gauthier", 24, geographyManager.getCity("Évreux", "27"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Lucas", "Loiseau", 22, geographyManager.getCity("Foix", "09"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Camille", "Bernard", 23, geographyManager.getCity("Gap", "05"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Julien", "Masson", 21, geographyManager.getCity("Guéret", "23"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Emma", "Barbier", 22, geographyManager.getCity("Issoudun", "36"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Noah", "Rousseau", 20, geographyManager.getCity("La Roche-sur-Yon", "85"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Sophie", "Benoit", 24, geographyManager.getCity("Laval", "53"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Florian", "Laporte", 23, geographyManager.getCity("Lons-le-Saunier", "39"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Manon", "Chapel", 21, geographyManager.getCity("Mende", "48"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Victor", "Roy", 22, geographyManager.getCity("Mont-de-Marsan", "40"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Clara", "Chevalier", 20, geographyManager.getCity("Montluçon", "03"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Élise", "Lucas", 23, geographyManager.getCity("Nevers", "58"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Théo", "Renard", 24, geographyManager.getCity("Niort", "79"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Hugo", "Pelletier", 20, geographyManager.getCity("Périgueux", "24"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Julie", "Leclerc", 22, geographyManager.getCity("Privas", "07"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Maxime", "Dufour", 23, geographyManager.getCity("Quimper", "29"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Sarah", "Meyer", 21, geographyManager.getCity("Rodez", "12"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Léa", "Carpentier", 22, geographyManager.getCity("Saint-Brieuc", "22"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Nathan", "Blanc", 20, geographyManager.getCity("Saint-Lô", "50"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Émilie", "Poirier", 24, geographyManager.getCity("Sarrebourg", "57"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Antoine", "Georges", 23, geographyManager.getCity("Sens", "89"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Paul", "Garcia", 21, geographyManager.getCity("Soissons", "02"), "Distributed Systems", computerScience, 3, researcher);
		ecosystemManager.addStudent("Claire", "Roux", 22, geographyManager.getCity("Thionville", "57"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Hector", "Lemoine", 20, geographyManager.getCity("Tulle", "19"), "Combinatorics", mathematics, 1, researcher);
		ecosystemManager.addStudent("Eva", "Fontaine", 24, geographyManager.getCity("Valenciennes", "59"), "Artificial Intelligence", computerScience, 2, researcher);
		ecosystemManager.addStudent("Lucas", "Girard", 23, geographyManager.getCity("Vannes", "56"), "Cryptography", computerScience, 2, researcher);
		ecosystemManager.addStudent("Camille", "Chauvin", 21, geographyManager.getCity("Verdun", "55"), "Topology", mathematics, 1, researcher);
		ecosystemManager.addStudent("Amélie", "Duval", 22, geographyManager.getCity("Vesoul", "70"), "Differential Equations", mathematics, 1, researcher);
		ecosystemManager.addStudent("Lilian", "Lambert", 20, geographyManager.getCity("Villeneuve-sur-Lot", "47"), "Machine Learning", computerScience, 3, researcher);
		ecosystemManager.addStudent("Marine", "Simon", 24, geographyManager.getCity("Yssingeaux", "43"), "Distributed Systems", computerScience, 3, researcher);
		 
		System.out.println("Default ecosystem successfully generated !");
	}

	/**
	 * Ajoute une ville de manière interactive en fonction des entrées de l'utilisateur.
	 * Permet à l'utilisateur de rechercher une ville par son nom et, si nécessaire, de spécifier le département.
	 * 
	 * @param geographyManager le gestionnaire géographique contenant les données des villes
	 * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
	 * @return la ville sélectionnée par l'utilisateur
	 */
	private static City addCityInteractive(GeographyManager geographyManager, Scanner scanner) {
		while (true) {
			System.out.print("Enter the city name where the student resides: ");
			String cityName = scanner.nextLine().trim();
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
				String departmentCode = scanner.nextLine().trim();
				for (City currentCity : foundCities) {
					if (currentCity.getDepartment().equalsIgnoreCase(departmentCode)) {
						return currentCity;
					}
				}
				System.out.println("City not found for the specified department. Please try again.");
			} else {
				return foundCities.get(0);
			}
		}
	}
	
	/**
	 * Permet à l'utilisateur de sélectionner un titulaire de manière interactive.
	 * L'utilisateur entre le prénom et le nom du titulaire, et en cas d'ambiguïté,
	 * une liste est affichée pour sélectionner le titulaire par son identifiant.
	 * 
	 * @param ecosystemManager le gestionnaire de l'écosystème
	 * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
	 * @return le titulaire sélectionné par l'utilisateur
	 */
	private static Titular getTitularInteractive(EcosystemManager ecosystemManager, Scanner scanner) {

		Titular titular = null;
		while (titular == null) {
			System.out.print("Enter titular's first name: ");
			String firstName = scanner.nextLine().trim();

			System.out.print("Enter titular's last name: ");
			String lastName = scanner.nextLine().trim();

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
						titularID = Integer.parseInt(scanner.nextLine().trim());
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
					System.out.println(
							"Titular \"" + firstName + " " + lastName + "\" (ID=" + titularID + ") have been found.");
			} else {
				titular = foundTitulars.get(0);
			}
		}

		return titular;
	}

	/**
     * Ajoute un étudiant à l'écosystème via une saisie interactive de l'utilisateur.
     * 
     * @param ecosystemManager le gestionnaire de l'écosystème
     * @param geographyManager le gestionnaire géographique
     * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
     */
	private static void addStudentInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {

		if (!ecosystemManager.canAssignTitular()) {
			System.out.print("No titular available to supervise a new student in the ecosystem.");
			return;
		}
		
		System.out.println("--- Add a Student ---");
		System.out.print("Enter student's first name: ");
		String firstName = scanner.nextLine().trim();

		System.out.print("Enter student's last name: ");
		String lastName = scanner.nextLine().trim();

		int age = -1;
		while (age < 1 || age > 110) {
			try {
				System.out.print("Enter student's age: ");
				age = Integer.parseInt(scanner.nextLine().trim());
				if (age < 1 || age > 110) {
					System.out.println("Age must be between 1 and 110.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		City city = addCityInteractive(geographyManager, scanner);

		if (city == null)
			return;

		System.out.print("Enter the thesis subject: ");
		String thesisSubject = scanner.nextLine().trim();

		Discipline discipline = MenuUtils.addDisciplineInteractive(scanner);

		int thesisYear = -1;
		while (thesisYear < 1 || thesisYear > 3) {
			try {
				System.out.print("Enter the thesis year (1, 2, or 3): ");
				thesisYear = Integer.parseInt(scanner.nextLine().trim());
				if (thesisYear < 1 || thesisYear > 3) {
					System.out.println("The thesis year must be 1, 2 or 3.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
		
		Titular titular = getTitularInteractive(ecosystemManager, scanner);
		Student student = new Student(firstName, lastName, age, city, thesisSubject, discipline, thesisYear, titular);
		
		while (!EcosystemManager.assignTitular(student, titular)) {
			titular = getTitularInteractive(ecosystemManager, scanner);
			student = new Student(firstName, lastName, age, city, thesisSubject, discipline, thesisYear, titular);
		}
		ecosystemManager.addStudent(student);
	}

	/**
	 * Ajoute un titulaire (MCF ou chercheur) de manière interactive.
	 * L'utilisateur entre les informations du titulaire, telles que son prénom,
	 * nom, âge, ville, disciplines et numéro de bureau.
	 * 
	 * @param ecosystemManager le gestionnaire de l'écosystème
	 * @param geographyManager le gestionnaire géographique
	 * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
	 * @param titularType      le type de titulaire à ajouter (MCF ou chercheur)
	 */
	private static void addTitularInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner, String titularType) {
		System.out.println("--- Add a " + titularType + " ---");
		System.out.print("Enter " + titularType + " first name: ");
		String firstName = scanner.nextLine().trim();

		System.out.print("Enter " + titularType + " last name: ");
		String lastName = scanner.nextLine().trim();

		int age = -1;
		while (age < 10 || age > 90) {
			try {
				System.out.print("Enter " + titularType + " age: ");
				age = Integer.parseInt(scanner.nextLine().trim());
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
		disciplines.add(MenuUtils.addDisciplineInteractive(scanner));
		while (true) {
			System.out.print("Would you like to add a second discipline? (yes/no): ");
			String addSecond = scanner.nextLine().trim();

			if (addSecond.equalsIgnoreCase("yes")) {
				disciplines.add(MenuUtils.addDisciplineInteractive(scanner));
				break;
			} else if (addSecond.equalsIgnoreCase("no")) {
				break;
			}
		}

		int officeNumber = -1;
		while (officeNumber < 0) {
			try {
				System.out.print("Enter " + titularType + " office number: ");
				officeNumber = Integer.parseInt(scanner.nextLine().trim());
				if (officeNumber < 0) {
					System.out.println("Office number must be a positive integer.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		if (titularType.equalsIgnoreCase("MCF")) {
			MCF mcf = new MCF(firstName, lastName, age, city, disciplines, officeNumber);
			ecosystemManager.addMCF(mcf);
		} else if (titularType.equalsIgnoreCase("Researcher")) {
			Researcher researcher = new Researcher(firstName, lastName, age, city, disciplines, officeNumber);
			ecosystemManager.addResearcher(researcher);
		}

	}
	
	/**
	 * Ajoute un maître de conférences (MCF) de manière interactive.
	 * Utilise la méthode générique {@link #addTitularInteractive} en précisant le type "MCF".
	 * 
	 * @param ecosystemManager le gestionnaire de l'écosystème
	 * @param geographyManager le gestionnaire géographique
	 * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
	 */
	private static void addMCFInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		addTitularInteractive(ecosystemManager, geographyManager, scanner, "MCF");
	}

	/**
	 * Ajoute un chercheur de manière interactive. Utilise la méthode générique
	 * {@link #addTitularInteractive} en précisant le type "Researcher".
	 * 
	 * @param ecosystemManager le gestionnaire de l'écosystème
	 * @param geographyManager le gestionnaire géographique
	 * @param scanner          l'objet {@link Scanner} pour la saisie utilisateur
	 */
	private static void addResearcherInteractive(EcosystemManager ecosystemManager, GeographyManager geographyManager,
			Scanner scanner) {
		addTitularInteractive(ecosystemManager, geographyManager, scanner, "Researcher");
	}

}
