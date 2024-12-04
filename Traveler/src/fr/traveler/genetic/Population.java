package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;

/* Cette classe vise à représenter la population initiale
 * La population initiale est l'ensemble des individus duquel nous 
 * partons pour, on l'espère, trouver le meilleur cycle Hamiltonien*/

public class Population {

	private ArrayList<City> citiesOfProblem; 
	private ArrayList<ArrayList<City>> population = new ArrayList<>(); 
	private ArrayList<Double> fitness = new ArrayList<Double>();
	private int sizeOfPopulation; 


	// ----------------------------------------------------------------------------------------------

	// Constructor :
	public Population (ArrayList<City> citiesOfProblem, int sizeOfPopulation ) {
		this.citiesOfProblem = citiesOfProblem;
		this.sizeOfPopulation = sizeOfPopulation;
	}
	
	public Population (ArrayList<ArrayList<City>> population) {
		this.sizeOfPopulation = population.size();
		this.population = population;
	}
	
	public Population( int sizeOfPopulation ) {
		this.sizeOfPopulation = sizeOfPopulation;


	}

	// ----------------------------------------------------------------------------------------------
	// DISPLAY

	// Display Cities of an ArrayList of City
	public void displayCities(ArrayList<City> individual ) {

		String mystr="";
		for (City city : individual ) {
			mystr += city.getName() + "->";
		}
		mystr +=  individual.getFirst().getName();
		System.out.println(mystr);
	}

	// Display all individuals of the population
	public void displayAllPopulation() {

		for (int i = 0; i < this.population.size(); i++) {

			System.out.println("Individual number : " + (i) + " : ");
			displayCities(this.population.get(i));
			System.out.println(" --------- FITNESS : " + getFitness(i) + "---------");
		}
	}

	// ----------------------------------------------------------------------------------------------

	// Getters :
	
	public double getFitness( int i ) {
		return this.fitness.get(i);
	}

	public ArrayList<Double> getTabOfFitness() {
		return this.fitness;
	}

	public ArrayList<City> getListOfCities() {
		return this.citiesOfProblem;
	}

	public ArrayList<ArrayList<City>> getListOfPopulation() {
		return this.population;
	}

	public int getSizeOfPopulation() {
		return this.sizeOfPopulation;
	}

	public ArrayList<City> getIndividual(int i) {
		return this.population.get(i);
	}


	// ----------------------------------------------------------------------------------------------

	// We should add a method to not add to identical individual

	// ----------------------------------------------------------------------------------------------

	// Create a new individual and add his to the population :
	
	public boolean isNoSame( double myFitness, int currentIndex ) {
		for( int i = 0 ; i < currentIndex ; i++) {
			if( myFitness == this.getFitness(i) ) {
				return false;
			}
		}
		return true;
	}
		
	

	public ArrayList<City> createIndividual() {

		ArrayList<City> individual = new ArrayList<>(); // New ArrayList of 'City' for modelize our individual

		// Create a copy of the cities in our problem
		for (City city : this.citiesOfProblem) {
			individual.add( new City(city.getName(), Math.toDegrees(city.getLatitude()), Math.toDegrees(city.getLongitude())));
			
		}
		// shuffle our cities
		Collections.shuffle(individual);
		
		return individual ;
	}

	// ----------------------------------------------------------------------------------------------

	// Initiate a new population : create individual and add to population
	// 'this.size_of_population' times
	public void newPopulation() {
		int i = 0;
		while( i < this.sizeOfPopulation ) {

			ArrayList<City> myIndividual = createIndividual(); // Create individual
			double myFitness = Fitness.computeFitness(myIndividual);
			
			if( isNoSame( myFitness, i) ) {
				this.population.add(myIndividual);
				this.fitness.add(myFitness);
			}
			else continue;
			i++;

		}
	}
	
	public void addIndividual( ArrayList<City> individual, double fitness) {
		this.population.add(individual);
		this.fitness.add(fitness);
	}
	
	public void setTabOfFitness() {
		for( int i = 0; i < this.sizeOfPopulation; i++) {
			this.fitness.add(  Fitness.computeFitness( this.population.get(i)) );
		}
	}
	
	
	
	
	


	// ----------------------------------------------------------------------------------------------

}
