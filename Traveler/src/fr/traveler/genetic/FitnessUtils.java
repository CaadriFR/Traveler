package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

public class FitnessUtils {
	private static final double EPSILON = 10;

	public static boolean hasSimilarIndividu(double distance, List<Individu> population) {
		for (Individu individu : population) {
			if (Math.abs(individu.getDistance() - distance) < EPSILON)
				return true;
		}
		return false;
	}

	public static double getMinDistance(List<Individu> population) {
		double minDistance = population.getFirst().getDistance();
		for (Individu individu : population) {
			if (individu.getDistance() < minDistance)
				minDistance = individu.getDistance();
		}
		return minDistance;

	}

	public static List<Double> computeeTadbOfFitness(List<List<City>> population, List<Double> distance) {
		double myFitness;
		List<Double> fitness = new ArrayList<Double>();
		for (int i = 0; i < population.size(); i++) {
			myFitness = 10000 / distance.get(i);
			fitness.add(myFitness);
		}
		return fitness;
	}
}
