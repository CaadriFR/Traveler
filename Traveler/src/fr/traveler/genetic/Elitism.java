package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.traveler.geography.entities.City;

public class Elitism {

	private List<List<City>> old_population;
	private List<List<City>> new_population;
	private List<List<City>> final_population;
	private List<Double> fitnessOld;
	private List<Double> fitnessNew;

	private double rate_elite;

	public Elitism(List<List<City>> old_population, List<List<City>> new_population, List<Double> fitnessOld,
			List<Double> fitnessNew, double rate_elite) {
		this.old_population = old_population;
		this.new_population = new_population;
		this.fitnessNew = fitnessNew;
		this.fitnessOld = fitnessOld;
		this.final_population = new ArrayList<List<City>>();
		this.rate_elite = rate_elite;
	}

	public List<List<City>> getOldPopulation() {
		return this.old_population;
	}

	public List<List<City>> getNewPopulation() {
		return this.new_population;
	}

	public List<List<City>> getFinalPopulation() {
		return this.final_population;
	}

	public List<Double> getFitnessOld() {
		return this.fitnessOld;
	}

	public List<Double> getFitnessNew() {
		return this.fitnessNew;
	}

	public void setRateElite(double newRateElite) {
		this.rate_elite = newRateElite;

	}

	public List<List<City>> ReorderMyPopulation(List<List<City>> population, List<Double> fitness) {

		List<Integer> index = new ArrayList<>();
		for (int i = 0; i < fitness.size(); i++) {
			index.add(i);
		}

		index.sort(Comparator.comparingDouble(i -> fitness.get((int) i)).reversed());

		List<List<City>> populationOrdered = new ArrayList<List<City>>();

		for (int i : index) {
			populationOrdered.add(population.get(i));
		}

		return populationOrdered;

	}

	public void BuildMyFinalPopulation() {

		int index_rateElite = (int) Math.ceil(this.rate_elite * this.new_population.size());

		List<List<City>> old_populationOrdered = ReorderMyPopulation(this.old_population, this.fitnessOld);
		List<List<City>> new_populationOrdered = ReorderMyPopulation(this.new_population, this.fitnessNew);

		for (int i = 0; i < index_rateElite; i++) {
			this.final_population.add(new_populationOrdered.get(i));
		}

		for (int j = index_rateElite; j < this.old_population.size(); j++) {
			this.final_population.add(old_populationOrdered.get(j));
		}
	}

}
