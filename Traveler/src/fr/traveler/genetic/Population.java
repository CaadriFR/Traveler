package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

public class Population {

	private List<City> citiesOfProblem;
	private int size;
	private List<Individu> population;

	public Population(List<City> citiesOfProblem, int size) {
		this.citiesOfProblem = new ArrayList<>(citiesOfProblem);
		this.population = new ArrayList<>();
		this.size = size;
	}

	public Population(List<Individu> population) {
		this.population = population;
	}

	public void displayAllPopulation() {
		int count = 1;
		for (Individu individu : population) {

			System.out.println("Individual number : " + (count++) + " : ");
			individu.displayCities();
			System.out.println(" DISTANCE : " + individu.getDistance());
			System.out.println(" FITNESS : " + individu.getFitness());
			System.out.println();

		}
	}

	public int getSize() {
		return size;
	}

	public List<Individu> getPopulation() {
		return population;
	}

	public void Individu(Individu individu) {
		this.population.add(individu);
	}

	public void computeAllDistances() {
		for (Individu individu : population) {
			individu.computeDistance();
		}
	}
	
	public Individu createRandomIndividu() {
		// Ici soit on fait ça soit on code la possibilité de cloner un individu
		Individu individu = new Individu(new ArrayList<>(this.citiesOfProblem));
		individu.shuffleCycle();
		return individu;
	}

	public void newPopulation() {
		while (this.population.size() < this.size) {
			Individu individu = createRandomIndividu();

			if (!FitnessUtils.hasSimilarIndividu(individu.getDistance(), this.population)) {
				this.population.add(individu);
			}
		}
	}

}
