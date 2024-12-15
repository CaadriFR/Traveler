package fr.traveler.testsjunit;

import org.junit.jupiter.api.Test;

/**
 * Classe de test pour la classe {@link Crossing}.
 * Elle vérifie le bon fonctionnement des opérations de croisement 
 * dans le cadre de l'algorithme génétique.
 * @author Néo MORET
 */

import fr.traveler.genetic.Crossing;
import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class CrossingTest {
	
	private Crossing myCrossing1;
	private Crossing myCrossing2;
	private Crossing myCrossing3;
	
	

    /**
     * Méthode exécutée avant chaque test pour initialiser les données de test.
     * Elle crée des instances de {@link Crossing} avec différents cas de figure.
     */

	
	@BeforeEach
	public void buildSetUp() {
		
		City a = new City( "a", "0", 0, 0.0, "myDepartement", 0,0);
		City b = new City( "b", "0", 0, 0.0, "myDepartement", 0,0);
		City c= new City( "c", "0", 0, 0.0, "myDepartement", 0,0);
		City d= new City( "d", "0", 0, 0.0, "myDepartement", 0,0);
		City e= new City( "e", "0", 0, 0.0, "myDepartement", 0,0);
		City f= new City( "f", "0", 0, 0.0, "myDepartement", 0,0);
		City g= new City( "g", "0", 0, 0.0, "myDepartement", 0,0);
		City h= new City( "h", "0", 0, 0.0, "myDepartement", 0,0);
		City i= new City( "i", "0", 0, 0.0, "myDepartement", 0,0);
		City j= new City( "j", "0", 0, 0.0, "myDepartement", 0,0);


		
		List<City> cycle11 = new ArrayList<>();
		List<City> cycle21 = new ArrayList<>();
		
		cycle11.add(b);
		cycle11.add(d);
		cycle11.add(a);
		cycle11.add(c);
		cycle11.add(g);
		cycle11.add(e);
		cycle11.add(f);
		
		cycle21.add(a);
		cycle21.add(g);
		cycle21.add(f);
		cycle21.add(e);
		cycle21.add(c);
		cycle21.add(d);
		cycle21.add(b);
		
		Individu parent11 = new Individu(cycle11);
		Individu parent21 = new Individu(cycle21);
		
		this.myCrossing1 = new Crossing(parent11, parent21);
		
		List<City> cycle12 = new ArrayList<>();
		List<City> cycle22 = new ArrayList<>();
		
		cycle12.add(a);
		cycle12.add(b);
		cycle12.add(c);
		cycle12.add(d);
		cycle12.add(e);
		cycle12.add(f);
		cycle12.add(g);
		cycle12.add(h);
		cycle12.add(i);
		cycle12.add(j);

		
		cycle22.add(a);
		cycle22.add(b);
		cycle22.add(c);
		cycle22.add(d);
		cycle22.add(e);
		cycle22.add(f);
		cycle22.add(g);
		cycle22.add(h);
		cycle22.add(i);
		cycle22.add(j);

		
		Individu parent12 = new Individu(cycle12);
		Individu parent22 = new Individu(cycle22);
		
		this.myCrossing2 = new Crossing(parent12, parent22);
		
		List<City> cycle13 = new ArrayList<>();
		List<City> cycle23 = new ArrayList<>();
		
		cycle13.add(b);
		cycle13.add(d);
		cycle13.add(a);
		cycle13.add(i);
		cycle13.add(g);
		cycle13.add(h);
		cycle13.add(e);
		cycle13.add(f);
		cycle13.add(c);
		cycle13.add(j);

		
		cycle23.add(h);
		cycle23.add(a);
		cycle23.add(b);
		cycle23.add(f);
		cycle23.add(g);
		cycle23.add(c);
		cycle23.add(d);
		cycle23.add(i);
		cycle23.add(j);
		cycle23.add(e);

		
		Individu parent13 = new Individu(cycle13);
		Individu parent23 = new Individu(cycle23);
		
		this.myCrossing3 = new Crossing(parent13, parent23);
	

	}
	
	   /**
     * Teste la méthode {@code buildCycle} pour vérifier la construction correcte des cycles
     * entre les parents dans différents cas de figure.
     * Ce test est très important car la fonctionnalité ou non de l'algorithme génétique 
		repose essentiellement sur les opération de croissement 
     */
	
	@Test
	public void TestBuildCycle() {
		

			
		/*
		 *  Test avec l'exemple du sujet :
		 */
		
		this.myCrossing1.buildCycle();
		
		List<List<Integer>> expected1 = new ArrayList<>(List.of(
	            new ArrayList<>(List.of(0, 6, 2)),
	            new ArrayList<>(List.of(1, 5, 3, 4))
			));
		
        assertEquals(expected1, this.myCrossing1.getCycles(), "Le cycle crée est différent du cycle attendu");
	
        
        /*
         * Test de construction des cycles pour deux parents identiques
         */
        this.myCrossing2.buildCycle();
		
		List<List<Integer>> expected2 = new ArrayList<>(List.of(
	            new ArrayList<>(List.of(0)),
	            new ArrayList<>(List.of(1)),
	            new ArrayList<>(List.of(2)),
	            new ArrayList<>(List.of(3)),
	            new ArrayList<>(List.of(4)),
	            new ArrayList<>(List.of(5)),
	            new ArrayList<>(List.of(6)),
	            new ArrayList<>(List.of(7)),
	            new ArrayList<>(List.of(8)),
	            new ArrayList<>(List.of(9))
			));
		
        assertEquals(expected2, this.myCrossing2.getCycles(), "Le cycle crée est différent du cycle attendu");
	
        /* 
         * Troisième test avec 10 villes
         */
        
        this.myCrossing3.buildCycle();
        List<List<Integer>> expected3 = new ArrayList<>(List.of(
	            new ArrayList<>(List.of(0, 2, 1, 6, 9, 8, 5)),
	            new ArrayList<>(List.of(3, 7)),
	            new ArrayList<>(List.of(4))  
			));
        
        assertEquals(expected3, this.myCrossing3.getCycles(), "Le cycle crée est différent du cycle attendu");

        
	}

}
