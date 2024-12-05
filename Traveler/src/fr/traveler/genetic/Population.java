package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private ArrayList<City> citiesOfProblem; 
	private ArrayList<ArrayList<City>> population = new ArrayList<>(); 
	private ArrayList<Double> fitness = new ArrayList<Double>();
	private ArrayList<Double> distance = new ArrayList<Double>();
	private int sizeOfPopulation; 


	// ----------------------------------------------------------------------------------------------

	// Constructor :
	
	// initial population
	public Population (ArrayList<City> citiesOfProblem, int sizeOfPopulation ) {
		this.citiesOfProblem = new ArrayList<>(citiesOfProblem);
		this.sizeOfPopulation = sizeOfPopulation;
	}
	
	// for recreate a population 
	public Population (ArrayList<ArrayList<City>> population) {
		this.sizeOfPopulation = population.size();
		this.population = population;
	}
	
	public Population( int sizeOfPopulation ) {
		this.sizeOfPopulation = sizeOfPopulation;
	}

	// ----------------------------------------------------------------------------------------------
	// DISPLAY

	// Display all individuals of the population
	public void displayAllPopulation() {

		for (int i = 0; i < this.population.size(); i++) {

			System.out.println("Individual number : " + (i) + " : "); 
			City.displayCities(this.population.get(i));
			System.out.println(" DISTANCE : " + getDistance(i) );
			System.out.println( );

		}
	}

	// ----------------------------------------------------------------------------------------------

	// Getters :
	
	public double getFitness( int i ) {
		return this.fitness.get(i);
	}
	
	public double getDistance( int i ) {
		return this.distance.get(i);
	}
	
	public int getSizeOfPopulation() {
		return this.sizeOfPopulation;
	}

	public ArrayList<Double> getTabOfFitness() {
		return this.fitness;
	}
	
	public ArrayList<Double> getTabOfDistance(){
		return this.distance;
	}

	public ArrayList<City> getListOfCities() {
		return this.citiesOfProblem;
	}

	public ArrayList<ArrayList<City>> getPopulation() {
		return this.population;
	}

	public ArrayList<City> getIndividual(int i) {
		return this.population.get(i);
	}
	
	// ----------------------------------------------------------------------------------------------

	public boolean isNoSame( double myFitness, int currentIndex ) {
		for( int i = 0 ; i < currentIndex ; i++ ) {
			if( myFitness == this.getDistance(i)) return false;
		}
		return true;
	}
	
	public void addIndividual( ArrayList<City> individual) {
		Double myDistance =  Fitness.computeDistance(individual);
		this.population.add(individual);
		this.distance.add(myDistance);
	}
	
	/*
	public ArrayList<City> createIndividualASuPPRIMERSIMARCHE() {
		ArrayList<City> individual = new ArrayList<>(); 
		for (City city : this.citiesOfProblem) {
			individual.add( new City(city.getName(), city.getLatitude(), city.getLongitude()));
		}
		Collections.shuffle(individual);
		return individual ;
	}*/
	
    public ArrayList<City> createIndividual() {
        ArrayList<City> individual = new ArrayList<>(this.citiesOfProblem);
        Collections.shuffle(individual);
        return individual;
    }
	
    /*
	public void newPopulationASupprimerSimarche() {
		int i = 0;
		while( i < this.sizeOfPopulation ) {
			ArrayList<City> myIndividual = createIndividual(); 
			double myDistance = Fitness.computeDistance(myIndividual);
			if( isNoSame( myDistance, i) ) {
				this.population.add(myIndividual);
				this.distance.add(myDistance);
			}
			else continue;
			i++;
		}
		this.fitness = Fitness.computeTabOfFitness(this.population, this.distance);
	}*/
	
    public void newPopulation() {
        while (this.population.size() < this.sizeOfPopulation) {
            ArrayList<City> individual = createIndividual();
            double myDistance = Fitness.computeDistance(individual);

            if (!Fitness.isSame(myDistance, this.distance)) {
                this.population.add(individual);
                this.distance.add(myDistance);
            }
        }
        this.fitness = Fitness.computeTabOfFitness(this.population, this.distance);
    }
	
	public void setTabOfFitness() {
		this.fitness = Fitness.computeTabOfFitness(this.population, this.distance);
	}
	
	public void setTabOfDistance() {
		this.distance = Fitness.computeTabOfDistance(this.population);
	}
}
