package fr.traveler.genetic;

import java.util.ArrayList;

public class UniformSelection {
	
	private ArrayList<ArrayList<City>> population;
	private double[] tab_of_fitness;
	private double[] tab_of_fitness_cumul;
	int[] numbers = new int[5];
	private ArrayList<City> parent1;
	private ArrayList<City> parent2;
	
	public UniformSelection( ArrayList<ArrayList<City>> population, double[] tab_of_fitness ) {
		this.population = population;
		this.tab_of_fitness = tab_of_fitness;
		this.tab_of_fitness_cumul = new double[tab_of_fitness.length+1];
		this.parent1 = null;
		this.parent2 = null;
	}
	
	public ArrayList<ArrayList<City>> getPopulation(){
		return this.population;
	}
	
	public double[] getTabFitness() {
		return this.tab_of_fitness;
	}
	
	public double[] getTabFitnessCumul() {
		return this.tab_of_fitness_cumul;
	}
	
	public double getFitness( int i ) {
		return this.tab_of_fitness[i];
	}
	
	public ArrayList<City> getParent1(){
		return this.parent1;
	}
	
	public ArrayList<City> getParent2(){
		return this.parent2;
	}
	
	public void FitnessPourcent() {
		
		double mysum=0;
		for( int i = 0; i < this.tab_of_fitness.length ; i++) {
			mysum += this.tab_of_fitness[i];	
		}
		
		double fitness_cumul=0;
		this.tab_of_fitness_cumul[0] = fitness_cumul;			
		for( int j = 1; j< this.tab_of_fitness.length ; j++ ) {
			fitness_cumul += ((this.tab_of_fitness[j] * 100) / mysum)  ;

			this.tab_of_fitness_cumul[j] = fitness_cumul;			
		}
		
		if( this.tab_of_fitness_cumul[ this.tab_of_fitness_cumul.length-1 ] != 100) this.tab_of_fitness_cumul[ this.tab_of_fitness_cumul.length ]=100;
		
	}
	
	public void ChooseMyParent() {
		
		FitnessPourcent();
		
		int myRandom= (int) ((Math.random() * 101)); 
		int indexOfParent1=0;
		int indexOfParent2=0;
		
		System.out.println("Random1 :" + myRandom);
		
		while( true ) { // a modifier pour eviter pb boucle inf
			if( this.tab_of_fitness_cumul[indexOfParent1] <= myRandom  && this.tab_of_fitness_cumul[indexOfParent1+1] > myRandom) {
				indexOfParent1++;
			}
			else break;
		}
			
		myRandom= (int) ((Math.random() * 101)); 
		
		//il faut transformer  tab_of_fitness_cumul en arraylist ca sera bcp plus simple
		
		System.out.println("Random2 :" + myRandom);
		while( true) {
			if( this.tab_of_fitness_cumul[indexOfParent1] <= myRandom  && this.tab_of_fitness_cumul[indexOfParent1+1] > myRandom) {
				indexOfParent2++;
			}
			else if (indexOfParent2 == indexOfParent1){
				this.tab_of_fitness_cumul[indexOfParent1]=this.tab_of_fitness_cumul[indexOfParent1-1]
				indexOfParent2=0;
				myRandom= (int) ((Math.random() * 100) + 1);	
			}

				 	
			}	
		}
		
		//if(indexOfParent1 > 0) indexOfParent1--;
		//if(indexOfParent2 > 0) indexOfParent2--;
		
		
		System.out.println(indexOfParent1);
		System.out.println(indexOfParent2);
		
		System.out.println(" Individus " + indexOfParent1 + " et " + indexOfParent2 + "choisis");
		
		this.parent1 = population.get(indexOfParent1);
		this.parent2 = population.get(indexOfParent2);
		
		
		
		
	}
	


}
