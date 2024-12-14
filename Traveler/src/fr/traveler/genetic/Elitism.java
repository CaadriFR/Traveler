package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.traveler.genetic.entities.Individu;

public class Elitism {

	private List<Individu> old_population;
	private List<Individu> new_population;
	private List<Individu> final_population;

	private double rate_elite;

	public Elitism(List<Individu> old_population, List<Individu> new_population, double rate_elite) {
		this.old_population = old_population;
		this.new_population = new_population;
		this.final_population = new ArrayList<>();
		this.rate_elite = rate_elite;
	}

	public List<Individu> getOldPopulation() {
		return this.old_population;
	}

	public List<Individu> getNewPopulation() {
		return this.new_population;
	}

	public List<Individu> getFinalPopulation() {
		return this.final_population;
	}

	public void setRateElite(double newRateElite) {
		this.rate_elite = newRateElite;

	}

	public List<Individu> ReorderMyPopulation(List<Individu> population) {

		List<Integer> index = new ArrayList<>();
		for (int i = 0; i < population.size(); i++) {
			index.add(i);
		}

		index.sort(Comparator.comparingDouble(i -> population.get((int) i).getFitness()).reversed());
		
		List<Individu> populationOrdered = new ArrayList<>();

		for (int i : index) {
			populationOrdered.add(population.get(i));
		}

		return populationOrdered;

	}

	public void BuildMyFinalPopulation() {

		int index_rateElite = (int) Math.ceil(this.rate_elite * this.new_population.size());

		List<Individu> old_populationOrdered = ReorderMyPopulation(this.old_population);
		List<Individu> new_populationOrdered = ReorderMyPopulation(this.new_population);

		for (int i = 0; i < index_rateElite; i++) {
			this.final_population.add(new_populationOrdered.get(i));
		}

		for (int j = 0; j < this.old_population.size() - index_rateElite; j++) {
			this.final_population.add(old_populationOrdered.get(j));
		}
	}

}
