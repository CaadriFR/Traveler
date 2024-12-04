package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;

/* Cette classe vise à représenter la population initiale
 * La population initiale est l'ensemble des individus duquel nous 
 * partons pour, on l'espère, trouver le meilleur cycle Hamiltonien*/

public class Population_init {

	private ArrayList<City> cities_of_problem; // ArrayList of 'City' with the cities which we pass for resolve the
												// TravelerProblem
	private ArrayList<ArrayList<City>> population = new ArrayList<>(); // ArrayList of ArrayList of 'City' : modelize
																		// our population
	private int size_of_population; // number of individuals we would for the first iteration of GeneticAlgorithm
	private Fitness fitness; // tab of fitness : tab_of_fitness[i] it's the fitness of the individual in
										// population[i]

	// ----------------------------------------------------------------------------------------------

	// Constructor :
	public Population_init(ArrayList<City> cities_of_problem, int size_of_population) {
		this.cities_of_problem = cities_of_problem;
		this.size_of_population = size_of_population;
		this.fitness = null;
	}
	
	public Population_init(ArrayList<ArrayList<City>> population) {
		this.size_of_population = population.size();
		this.fitness = new Fitness(population);
		this.fitness.ComputeFitness();
		this.population = population;

	}

	// ----------------------------------------------------------------------------------------------
	// DISPLAY

	// Display Cities of an ArrayList of City
	public void displayCities(ArrayList<City> cityList) {

		String mystr="";
		for (City city : cityList) {
			mystr += city.getName() + ";";
		}
		System.out.println(mystr);
	}

	// Display all individuals of the population
	public void displayPopulation() {

		for (int i = 0; i < this.population.size(); i++) {

			System.out.println("Individu " + (i) + " :");
			displayCities(this.population.get(i));
			System.out.println(" ------- Fitness : " + getFitness(i) + "-------\n");

		}

	}

	// ----------------------------------------------------------------------------------------------

	// Getters :

	public double getFitness(int i) {
		return this.fitness.getFitness().get(i);
	}
	
	public ArrayList<Double> getTabFitness() {
		return this.fitness.getFitness();
	}

	public ArrayList<City> getListOfCities() {
		return this.cities_of_problem;
	}

	public ArrayList<ArrayList<City>> getListOfPopulation() {
		return this.population;
	}

	public int getSizeOfPopulation() {
		return this.size_of_population;
	}

	public ArrayList<City> getIndividual(int i) {
		return this.population.get(i);
	}


	// ----------------------------------------------------------------------------------------------

	// We should add a method to not add to identical individual

	// ----------------------------------------------------------------------------------------------

	// Create a new individual and add his to the population :

	public void createIndividual() {

		ArrayList<City> individual = new ArrayList<>(); // New ArrayList of 'City' for modelize our individual

		// Create a copy of the cities in our problem
		for (City city : this.cities_of_problem) {
			individual.add(new City(city.getName(), Math.toDegrees(city.getLatitude()), Math.toDegrees(city.getLongitude())));
			
		}
		

		// shuffle our cities
		Collections.shuffle(individual);
		

		//City temp = individual.get(0);
		//individual.add( new City( temp.getName(), Math.toDegrees(temp.getLatitude()), Math.toDegrees(temp.getLongitude())));
		

		// add individual into our population
		this.population.add(individual);
	}

	// ----------------------------------------------------------------------------------------------

	// Initiate a new population : create individual and add to population
	// 'this.size_of_population' times
	public void newPopulation() {

		for (int i = 0; i < this.size_of_population; i++) {

			createIndividual(); // Create individual

		}
		
		this.fitness = new Fitness(this.population);
		this.fitness.ComputeFitness();
	}
	
	
	


	// ----------------------------------------------------------------------------------------------

}
