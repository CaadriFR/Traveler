package fr.traveler.testsjunit;

import org.junit.jupiter.api.Test;

import fr.traveler.genetic.UniformSelection;
import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class UniformSelectionTest {
	
	UniformSelection mySelection;
	
	@BeforeEach
	public void buildSetUp() {
		City paris = new City( "Paris", "0", 0, 0.0, "myDepartement", 48.8566,2.3522);
		City marseille = new City( "Marseille", "0", 0, 0.0, "myDepartement", 43.2965,5.3698);
		City lyon= new City( "Lyon", "0", 0, 0.0, "myDepartement", 45.7640,4.8357);
		City strasbourg= new City( "Strasbourg", "0", 0, 0.0, "myDepartement", 48.5734,7.7521);
		City bordeaux= new City( "Bordeaux", "0", 0, 0.0, "myDepartement", 44.8378,-0.5792);

		List<City> cities1 = new ArrayList<>();
		List<City> cities2 = new ArrayList<>();
		List<City> cities3 = new ArrayList<>();
		List<City> cities4 = new ArrayList<>();

		
		cities1.add(paris);
		cities1.add(marseille);
		cities1.add(lyon);
		cities1.add(strasbourg);
		cities1.add(bordeaux);
		
		Individu myIndividu1 = new Individu(cities1);	
	
		cities2.add(paris);
		cities2.add(lyon);
		cities2.add(bordeaux);
		cities2.add(strasbourg);
		cities2.add(marseille);
		
		Individu myIndividu2 = new Individu(cities2);
		
		cities3.add(bordeaux);
		cities3.add(marseille);
		cities3.add(strasbourg);
		cities3.add(paris);
		cities3.add(lyon);
		
		Individu myIndividu3 = new Individu(cities3);
		
		cities4.add(strasbourg);
		cities4.add(bordeaux);
		cities4.add(lyon);
		cities4.add(paris);
		cities4.add(marseille);
		
		Individu myIndividu4 = new Individu(cities4);
	
		List<Individu> population= new ArrayList<>();
		population.add(myIndividu1);
		population.add(myIndividu2);
		population.add(myIndividu3);
		population.add(myIndividu4);
		
		this.mySelection = new UniformSelection(population);
	}
	
	@Test
	public void TestbuildTabOfFitnessCumul() {
		this.mySelection.buildTabOfFitnessCumul();
		System.out.println(this.mySelection.getTabFitnessCumul());
		
		List<Double> expected = new ArrayList<>(List.of(
				0.0,
				25.629623382867432,
				48.72426549829195,
				76.90535788457547,
	            100.0
			));
	
		
		assertEquals(expected, this.mySelection.getTabFitnessCumul(), "Tableau cumulatif des fitness incorrect"); 
		
	}

}
