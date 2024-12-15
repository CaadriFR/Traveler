package fr.traveler.testsjunit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import fr.traveler.genetic.FitnessUtils;
import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

public class FitnessUtilsTest {
	
	private List<Individu> population;
	private double epsilon;
	
	@BeforeEach
	public void buildSetUp() {
		City paris = new City( "Paris", "0", 0, 0.0, "myDepartement", 48.8566,2.3522);
		City marseille = new City( "Marseille", "0", 0, 0.0, "myDepartement", 43.2965,5.3698);
		City lyon= new City( "Lyon", "0", 0, 0.0, "myDepartement", 45.7640,4.8357);
		City strasbourg= new City( "Strasbourg", "0", 0, 0.0, "myDepartement", 48.5734,7.7521);
		
		List<City> cities1 = new ArrayList<>();
		List<City> cities2 = new ArrayList<>();
		List<City> cities3 = new ArrayList<>();
		
		cities1.add(paris);
		cities1.add(marseille);
		cities1.add(lyon);
		cities1.add(strasbourg);
		
		Individu myIndividu1 = new Individu(cities1);	
	
		cities2.add(paris);
		cities2.add(lyon);
		cities2.add(strasbourg);
		cities2.add(marseille);
		
		Individu myIndividu2 = new Individu(cities2);
		
		cities3.add(marseille);
		cities3.add(strasbourg);
		cities3.add(paris);
		cities3.add(lyon);
		
		Individu myIndividu3 = new Individu(cities3);
	
		
		this.population = new ArrayList<>();
		this.population.add(myIndividu1);
		this.population.add(myIndividu2);
		this.population.add(myIndividu3);
		
		this.epsilon = 0;
		
	}
	
	@Test
	public void testGetMinDistance(){
		double myMin = FitnessUtils.getMinDistance(this.population);
		assertEquals(1681.379954316673, myMin,this.epsilon, "Distance du cycle incorrecte"); 

		
		
		
	}

}
