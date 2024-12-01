package genetic;

import java.util.ArrayList;

public class Fitness {
	private double fitness; // Fitness score
	
	public Fitness() {
		this.fitness = -1; // Par défaut on initialise le fitness à -1 
	}
	
	public double getFitness() {
		return this.fitness;
	}

	public void ComputeFitness( ArrayList<City> my_individual) {
		
		double totalDistance=0;
		int i=0;
		while( i < (my_individual.size()) -1 ) {
			
			totalDistance += my_individual.get(i).Haversine( my_individual.get(i +1) ); // calcule de la distance entre la ville i et la ville i+1
			i++;
		}
		totalDistance += my_individual.get(i).Haversine( my_individual.get(0) ); // ajout du retour à la ville initiale 
		this.fitness = totalDistance / my_individual.size(); 
	}
	
}
