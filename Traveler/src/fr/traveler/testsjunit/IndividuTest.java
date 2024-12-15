package fr.traveler.testsjunit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

public class IndividuTest {
	
    private double epsilon;
    private Individu myIndividu1;
    private Individu myIndividu2;
    private Individu myIndividu3;
	
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
		
		this.myIndividu1 = new Individu(cities1);	
	
		cities2.add(paris);
		cities2.add(lyon);
		cities2.add(strasbourg);
		cities2.add(marseille);
		
		this.myIndividu2 = new Individu(cities2);
		
		cities3.add(marseille);
		cities3.add(strasbourg);
		cities3.add(paris);
		cities3.add(lyon);
		
		this.myIndividu3 = new Individu(cities3);
		
	
		this.epsilon = 10;
	
		
	}
	
	@Test
	public void testComputeDistance() {
		
		// On s'autorise une marge d'erreur de 10 kilomètres pour la distance globale
		// On note que lorsqu'on appelle le constructeur de Individu, la distance globale est bie  calculée 
		// en prenant soin de calculer la distance entre la dernière ville de notre cycle et le retour à la ville initiale
		
		assertEquals(1717.72, this.myIndividu1.getDistance(),this.epsilon, "Distance du cycle incorrecte"); 
		assertEquals(2049.19 , this.myIndividu2.getDistance(),this.epsilon, "Distance du cycle incorrecte"); 
		assertEquals(1681.38  , this.myIndividu3.getDistance(),this.epsilon, "Distance du cycle incorrecte"); 



		
	}

}
