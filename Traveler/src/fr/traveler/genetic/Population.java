package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.traveler.geography.entities.City;

public class Population {

	private List<City> citiesOfProblem; 
	private List<List<City>> population = new ArrayList<>(); 
	private List<Double> fitness = new ArrayList<Double>();
	private List<Double> distance = new ArrayList<Double>();
	private int sizeOfPopulation; 

	public Population (List<City> citiesOfProblem, int sizeOfPopulation ) {
		this.citiesOfProblem = new ArrayList<>(citiesOfProblem);
		this.sizeOfPopulation = sizeOfPopulation;
	}
	
	public Population (List<List<City>> population) {
		this.sizeOfPopulation = population.size();
		this.population = population;
	}
	
	public Population( int sizeOfPopulation ) {
		this.sizeOfPopulation = sizeOfPopulation;
	}

	public void displayAllPopulation() {

		for (int i = 0; i < this.population.size(); i++) {

			System.out.println("Individual number : " + (i) + " : "); 
			City.displayCities(this.population.get(i));
			System.out.println(" DISTANCE : " + getDistance(i) );
			System.out.println(" FITNESS : " + getFitness(i) );
			System.out.println( );

		}
	}

	public double getFitness( int i ) {
		return this.fitness.get(i);
	}
	
	public double getDistance( int i ) {
		return this.distance.get(i);
	}
	
	public int getSizeOfPopulation() {
		return this.sizeOfPopulation;
	}

	public List<Double> getTabOfFitness() {
		return this.fitness;
	}
	
	public List<Double> getTabOfDistance(){
		return this.distance;
	}

	public List<City> getListOfCities() {
		return this.citiesOfProblem;
	}

	public List<List<City>> getPopulation() {
		return this.population;
	}

	public List<City> getIndividual(int i) {
		return this.population.get(i);
	}
	
	public boolean isNoSame( double myFitness, int currentIndex ) {
		for( int i = 0 ; i < currentIndex ; i++ ) {
			if( myFitness == this.getDistance(i)) return false;
		}
		return true;
	}
	
	public void addIndividual( List<City> individual) {
		Double myDistance =  Fitness.computeDistance(individual);
		this.population.add(individual);
		this.distance.add(myDistance);
	}
	
    public List<City> createIndividual() {
        List<City> individual = new ArrayList<>(this.citiesOfProblem);
        Collections.shuffle(individual);
        return individual;
    }
	
    public void newPopulation() {
        while (this.population.size() < this.sizeOfPopulation) {
            List<City> individual = createIndividual();
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
