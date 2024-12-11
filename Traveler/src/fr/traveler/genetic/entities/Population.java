package fr.traveler.genetic.entities;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.genetic.FitnessUtils;
import fr.traveler.geography.entities.City;

public class Population {

	private List<City> citiesOfProblem;
	private int size;
	private List<Individu> population = new ArrayList<>();

	public Population(List<City> citiesOfProblem, int size) {
		this.citiesOfProblem = new ArrayList<>(citiesOfProblem);
		this.size = size;
	}

	public Population(List<Individu> population) {
		this.size = population.size();
		this.population = population;
	}

	public Population(int size) {
		this.size = size;
	}

	public void displayAllPopulation() {
		int count = 1;
		for (Individu individu : population) {
			System.out.println("Individual number : " + (count++) + " : ");
			individu.displayCities();
			System.out.println(" DISTANCE : " + individu.getDistance());
			System.out.println(" FITNESS : " + individu.getFitness());
		}
	}

	public int getSize() {
		return size;
	}

	public List<Individu> getPopulation() {
		return population;
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

	// Nouveau test d'initialisation des individus de la population
	public Individu createIndividu() {
		List<City> remainingCities = new ArrayList<>(this.citiesOfProblem);
		List<City> cycle = new ArrayList<>();

		City currentCity = remainingCities.remove((int) (Math.random() * remainingCities.size()));
		cycle.add(currentCity);

		while (!remainingCities.isEmpty()) {
			City nearestCity = null;
			double minDistance = Double.MAX_VALUE;

			for (City city : remainingCities) {
				double distance = currentCity.distanceTo(city);
				if (distance < minDistance) {
					minDistance = distance;
					nearestCity = city;
				}
			}

			cycle.add(nearestCity);
			remainingCities.remove(nearestCity);
			currentCity = nearestCity;
		}

		return new Individu(cycle);
	}

	public void newPopulation() {
		int heuristicIndividuals = (int) Math.ceil(0.3 * this.size);

		for (int i = 0; i < heuristicIndividuals; i++) {
			Individu individu = createIndividu();
			if (!FitnessUtils.hasSimilarIndividu(individu.getDistance(), this.population)) {
				this.population.add(individu);
			}
		}

		while (this.population.size() < this.size) {
			Individu individu = createRandomIndividu();
			if (!FitnessUtils.hasSimilarIndividu(individu.getDistance(), this.population)) {
				this.population.add(individu);
			}
		}
	}

}
