package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.config.Config;
import fr.traveler.genetic.entities.Individu;
import fr.traveler.genetic.entities.Population;
import fr.traveler.geography.entities.City;

public class GeneticManager {

	private Individu individu;
	private List<Double> fitnessEvolution;

	public GeneticManager() {
		this.individu = new Individu(new ArrayList<>());
		this.fitnessEvolution = new ArrayList<>();
	}

	public Individu getSolution() {
		return this.individu;
	}
	
	public List<Double> getDistanceEvolution() {
		return this.fitnessEvolution;
	}

	public void startGeneticAlgorithm(List<City> citiesOfProblem, int size) {

		Population mypopulation1 = new Population(citiesOfProblem, size);
		Population mypopulation2;
		UniformSelection selection;
		Crossing mycross;
		Elitism myelite;

		Individu parent1;
		Individu parent2;
		Individu child1;
		Individu child2;

		mypopulation1.newPopulation();
		mypopulation1.displayAllPopulation();

		int i = 0;
		int j;

		int noImprovementCounter = 0;
		double bestDistance = FitnessUtils.getMinDistance(mypopulation1.getPopulation());
		fitnessEvolution.add(10000 /bestDistance);
		for (int iteration = 0; iteration < Config.MAX_ITERATIONS && noImprovementCounter < Config.STAGNATION; iteration++) {
			
			i++;
			j = 0;

			mypopulation2 = new Population(size);

			while (j < mypopulation1.getSize() / 2) {

				selection = new UniformSelection(mypopulation1.getPopulation());
				selection.ChooseMyParent();

				parent1 = selection.getParent1();
				parent2 = selection.getParent2();

				mycross = new Crossing(parent1, parent2);
				mycross.buildChilds();
				child1 = mycross.getChild1();
				child2 = mycross.getChild2();

				child1.mutate();
				child2.mutate();

				mypopulation2.getPopulation().add(child1);
				mypopulation2.getPopulation().add(child2);
				j++;

			}
			mypopulation2.computeAllDistances();

			myelite = new Elitism(mypopulation1.getPopulation(), mypopulation2.getPopulation(), Config.ELITISM);
			myelite.BuildMyFinalPopulation();

			mypopulation1 = new Population(myelite.getFinalPopulation());
			mypopulation1.computeAllDistances();

			double currentBestDistance = FitnessUtils.getMinDistance(mypopulation1.getPopulation());
			fitnessEvolution.add(10000 / currentBestDistance);
			if (currentBestDistance < bestDistance) {
				bestDistance = currentBestDistance;
				noImprovementCounter = 0;
			} else {
				noImprovementCounter++;
			}

		}

		System.out.println("Itérations :" + i);

		this.individu = mypopulation1.getPopulation().getFirst();
		

	}

}
