package genetic;

import java.util.ArrayList;

public class Fitness {
	public double fitness;
	
	public Fitness() {
		this.fitness = -1;
	}
	
	public double getFitness() {
		return this.fitness;
	}

	public void ComputeFitness( ArrayList<City> my_individual) {
		double totalDistance=0;
		int i=0;
		while( i < (my_individual.size()) -1 ) {
			
			totalDistance += my_individual.get(i).Haversine( my_individual.get(i +1) );
			i++;
		}
	
		
		totalDistance += my_individual.get(i).Haversine( my_individual.get(0) );

		this.fitness = totalDistance ;
		
	}
	
}
