package fr.traveler.genetic;

import java.util.ArrayList;

public class UniformSelection {
	
	private ArrayList<ArrayList<City>> population;
	private ArrayList<Double> fitness;
	private ArrayList<Double> fitness_cumul;
	private ArrayList<City> parent1;
	private ArrayList<City> parent2;
	
	public UniformSelection( ArrayList<ArrayList<City>> population, ArrayList<Double> fitness ) {
		this.population = population;
		this.fitness = fitness;
		this.fitness_cumul = new ArrayList<Double>();
		this.parent1 = null;
		this.parent2 = null;
	}
	
	public ArrayList<ArrayList<City>> getPopulation(){
		return this.population;
	}
	
	
	public ArrayList<Double> getTabFitnessCumul() {
		return this.fitness_cumul;
	}
	
	
	public ArrayList<City> getParent1(){
		return this.parent1;
	}
	
	public ArrayList<City> getParent2(){
		return this.parent2;
	}
	
	public void FitnessPourcent() {
		
		double mysum=0;
		for( int i = 0; i < this.fitness.size() ; i++) {
			mysum += this.fitness.get(i);	
		}
		
		double fitness_cumul=0;
		this.fitness_cumul.add(fitness_cumul);	
				
		for( int j = 0; j< this.fitness.size() ; j++ ) {
			fitness_cumul += ((this.fitness.get(j) * 100) / mysum)  ;

			this.fitness_cumul.add(fitness_cumul);				
		}
		
		if( this.fitness_cumul.getLast() != 100) this.fitness_cumul.set(this.fitness_cumul.size()-1, (double) 100);
			}
	
	public void ChooseMyParent() {
		
		FitnessPourcent();
		
		int myRandom= (int) ((Math.random() * 100)); 
		int indexOfParent1=0;
		int indexOfParent2=0;
		
		

		
		while( true ) { // a modifier pour eviter pb boucle inf
			if( this.fitness_cumul.get(indexOfParent1) <= myRandom  && this.fitness_cumul.get(indexOfParent1 +1)  > myRandom) {
				break;
			}
			indexOfParent1++;

		}
		
		this.parent1 = population.get(indexOfParent1);
			
		myRandom= (int) ((Math.random() * 100)); 
	


		
		while( true) {
			if( this.fitness_cumul.get(indexOfParent2)  <= myRandom  && this.fitness_cumul.get(indexOfParent2 +1)  >= myRandom) {
				if( indexOfParent2 == indexOfParent1 ) {
					myRandom= (int) ((Math.random() * 100)); 
					//System.out.println(" New Random : " + myRandom);
					indexOfParent2 = 0;
					continue;
				}
				else break;
			}
			indexOfParent2++;

		}
		
		this.parent2 = population.get(indexOfParent2);

		
	
		
		
		
		
		
		
		
		
		
		
		
	}
	


}
