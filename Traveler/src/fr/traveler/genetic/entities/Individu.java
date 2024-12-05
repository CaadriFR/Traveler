package fr.traveler.genetic.entities;

import java.util.Collections;
import java.util.List;

import fr.traveler.geography.entities.City;

public class Individu {

	private List<City> cycle;
	private double distance;

	public Individu(List<City> cycle) {
		this.cycle = cycle;
		if (!cycle.isEmpty())
			computeDistance();
	}
	
	public double getDistance() {
		return distance;
	}

	public double getFitness() {
		return 10000 / distance;
	}

	public List<City> getCycle() {
		return cycle;
	}

	public void shuffleCycle() {
		Collections.shuffle(cycle);
		computeDistance();
	}

	public void computeDistance() {
		double distance = 0;
		for (int i = 0; i < cycle.size() - 1; i++) {
			distance += this.cycle.get(i).distanceTo(cycle.get(i + 1));
		}
		distance += this.cycle.getLast().distanceTo(cycle.getFirst());
		this.distance = distance;
	}

	public void displayCities() {

		String cities = "";
		for (City city : this.cycle) {
			cities += city.getName() + "->";
		}
		cities += this.cycle.getFirst();
		System.out.println(cities);
	}

	public void mutate() {

		double randomDouble = Math.random();

		int randomIndex_1;
		int randomIndex_2;

		if (randomDouble <= 0.15) {

			do {
				randomIndex_1 = (int) (Math.random() * this.cycle.size());
				randomIndex_2 = (int) (Math.random() * this.cycle.size());

			} while (randomIndex_1 == randomIndex_2);

			City temp_city = this.cycle.get(randomIndex_1);
			this.cycle.set(randomIndex_1, this.cycle.get(randomIndex_2));
			this.cycle.set(randomIndex_2, temp_city);

		}
		computeDistance();
	}

}
