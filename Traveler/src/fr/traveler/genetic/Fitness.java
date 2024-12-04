package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;

public class Fitness {
	ArrayList<Double> fitness; // Fitness score
	private int size;
	ArrayList<ArrayList<City>> population;

	public Fitness( ArrayList<ArrayList<City>> population) {
		this.population = population;
		this.size = population.size();
		this.fitness = new ArrayList<Double>();
		 
	}
	

	

	public ArrayList<Double>  getFitness() {
		return this.fitness;
	}
	
	public double totalDistance(ArrayList<City> my_individual) {
		double mytotalDistance = 0;
		int i = 0;
		while (i < (my_individual.size()) - 1) {

			mytotalDistance += my_individual.get(i).Haversine(my_individual.get(i + 1)); // calcule de la distance entre																			// la ville i et la ville i+1
			i++;
		}
		mytotalDistance += my_individual.get(i).Haversine(my_individual.get(0)); // ajout du retour à la ville initiale
		return mytotalDistance;
		
	}

	public void ComputeFitness() {
		
		
		for( int i = 0; i<this.size; i++) {
			this.fitness.add( totalDistance(this.population.get(i)) );
		}
		
		double myMax = Collections.max(this.fitness);
		
		for( int j=0; j<this.size; j++) {
			
			
			this.fitness.set(j, (100 - (this.fitness.get(j)*100) / myMax) + 100 );
		}
	
		
		
	}
	

}
