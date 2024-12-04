package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;

public class Fitness {
	
	ArrayList<ArrayList<City>> population;
	ArrayList<Double> fitness; // Fitness score
	ArrayList<Double> distance; // Fitness score
	private int size;

	public Fitness( ArrayList<ArrayList<City>> population) {
		this.population = population;
		this.size = population.size();
		this.fitness = new ArrayList<Double>();
		this.distance = new ArrayList<Double>();
		 
	}
	
	public ArrayList<Double> getFitness() {
		return this.fitness;
	}
	
	public static boolean isSame( double fitness, ArrayList<Double> tabOfFitness ) {
		for( int i = 0; i<tabOfFitness.size(); i++) {
			if( fitness == tabOfFitness.get(i) ) return true;
		}
		return false;
		
	}
	
	public static double computeDistance( ArrayList<City> individual ) {
		double myDistance=0;
		int i;
		for( i=0; i<individual.size() - 1; i++) {
			myDistance += individual.get(i).Haversine(individual.get(i + 1));
		}
		myDistance += individual.get(i).Haversine(individual.get(0));
		return myDistance;	
	}
	
	public static double computeFitness( ArrayList<City> individual ) {
		double myFitness = computeDistance(individual);
		//myFitness = 
		return myFitness;
	}
	
	public void computeTabOfDistance() {
		for( int i = 0 ; i < this.size; i++) {
			this.distance.add( computeDistance( this.population.get(i) ) );	
		}	
	}

	public void computeTabOfFitness() {
		for( int i = 0; i < this.size; i++ ) {
			this.fitness.add( computeFitness( this.population.get(i) ) );
		}	
	}
}
