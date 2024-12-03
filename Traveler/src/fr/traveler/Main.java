package fr.traveler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.traveler.ecosystem.EcosystemManager;
import fr.traveler.ecosystem.entities.Discipline;
import fr.traveler.ecosystem.entities.MCF;
import fr.traveler.ecosystem.entities.Researcher;
import fr.traveler.ecosystem.entities.Student;
import fr.traveler.geography.CityManager;
import fr.traveler.geography.database.DatabaseManager;
import fr.traveler.geography.entities.City;
import fr.traveler.geography.entities.Region;

public class Main {

	public static void main(String[] args) {
		List<City> cities = DatabaseManager.getCities();
		if (cities.isEmpty()) {
			System.out.println("No cities found or there was an error.");
		} else {
			/*
			 * System.out.println("List of cities:"); for (City city : cities) {
			 * System.out.println(city.toString() + "\nRegion=" +
			 * Region.findRegionByCity(city).name()); }
			 */

			for (Region rg : Region.values()) {
				City capitalCity = CityManager.getCity(rg.getCapitalCity(), cities);
				if (capitalCity != null)
					System.out.println("Capital City of " + rg.name() + " is " + capitalCity.toString());
				else
					System.out.println("Capital City of " + rg.name() + " not found.");
			}
		}

		Discipline mathematics = Discipline.MATHEMATICS;
		Discipline computerScience = Discipline.COMPUTER_SCIENCE;

		Set<Discipline> disciplinesForMCF = new HashSet<>(List.of(mathematics));
		Set<Discipline> disciplinesForResearcher = new HashSet<>(List.of(mathematics, computerScience));

		MCF mcf = new MCF("Alice", "Dupont", 40, CityManager.getCity("Lille", cities), disciplinesForMCF, 101);

		Researcher researcher = new Researcher("Bob", "Martin", 55, CityManager.getCity("Paris", cities),
				disciplinesForResearcher, 202);

		Student student1 = new Student("Charles", "Leclerc", 25, CityManager.getCity("Lyon", cities), "Algebra",
				mathematics, 2);

		Student student2 = new Student("Diane", "Moreau", 28, CityManager.getCity("Rouen", cities), "AI",
				computerScience, 3);

        System.out.println("\n=== Titulars ===");
        System.out.println(mcf);
        System.out.println(researcher);

        System.out.println("\n=== Students ===");
        System.out.println(student1);
        System.out.println(student2);
        
        EcosystemManager.addTitular(student2, researcher);
        EcosystemManager.addTitular(student1, mcf);
        
        System.out.println("\n=== Titulars ===");
        System.out.println(mcf);
        System.out.println(researcher);

        System.out.println("\n=== Students ===");
        System.out.println(student1);
        System.out.println(student2);
		
	}

}
