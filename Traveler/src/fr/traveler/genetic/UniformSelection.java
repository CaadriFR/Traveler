package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.geography.entities.City;

public class UniformSelection {
	
	private List<List<City>> population;
	private List<Double> fitness;
	private List<Double> fitness_cumul;
	private List<City> parent1;
	private List<City> parent2;
	
	public UniformSelection( List<List<City>> population, List<Double> fitness ) {
		this.population = population;
		this.fitness = fitness;
		this.fitness_cumul = new ArrayList<Double>();
		this.parent1 = null;
		this.parent2 = null;
	}
	
	public List<List<City>> getPopulation(){
		return this.population;
	}
	
	
	public List<Double> getTabFitnessCumul() {
		return this.fitness_cumul;
	}
	
	
	public List<City> getParent1(){
		return this.parent1;
	}
	
	public List<City> getParent2(){
		return this.parent2;
	}
	
	public void buildTabOfFitnessCumul() {
		
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
		
		buildTabOfFitnessCumul();
		
		int myRandom= (int) ((Math.random() * 100)); 
		
		int indexOfParent1=0;
		int indexOfParent2=0;
		
		//System.out.println("Random 1 :");
		//System.out.println(myRandom);
		
		while( true ) { // a modifier pour eviter pb boucle inf
			if( this.fitness_cumul.get(indexOfParent1) <= myRandom  && this.fitness_cumul.get(indexOfParent1 +1)  > myRandom) {
				break;
			}
			indexOfParent1++;
		}
		
		//System.out.println("index 1 :" + indexOfParent1);

		
		this.parent1 = population.get(indexOfParent1);
			
		myRandom= (int) ((Math.random() * 100)); 
		
		//System.out.println("Random 2 :");
		//System.out.println(myRandom);
	
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
		
		//System.out.println("index 2 :" + indexOfParent2);
		
		this.parent2 = population.get(indexOfParent2);	
		
	}
}
