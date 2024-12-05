package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.geography.entities.City;

public class Fitness {
	private static final double EPSILON = 1e-6;

	public static boolean isSame(double distance, List<Double> tabOfDistance) {
		for (double d : tabOfDistance) {
			if (Math.abs(d - distance) < EPSILON)
				return true;
		}
		return false;
	}

	public static double computeDistance(List<City> individual) {
		double myDistance = 0;
		for (int i = 0; i < individual.size() - 1; i++) {
			myDistance += individual.get(i).distanceTo(individual.get(i + 1));
		}
		myDistance += individual.get(individual.size() - 1).distanceTo(individual.get(0));
		return myDistance;
	}

	public static List<Double> computeTabOfDistance(List<List<City>> population) {
		List<Double> distance = new ArrayList<Double>();
		for (List<City> individual : population) {
			distance.add(computeDistance(individual));
		}
		return distance;
	}

	public static double minDistance(List<Double> distance) {
		double mymin = distance.get(0);
		for (Double myDistance : distance) {
			if (myDistance < mymin)
				mymin = myDistance;
		}
		return mymin;

	}

	public static List<Double> computeTabOfFitness(List<List<City>> population, List<Double> distance) {
		double myFitness;
		List<Double> fitness = new ArrayList<Double>();
		for (int i = 0; i < population.size(); i++) {
			myFitness = 10000 / distance.get(i);
			fitness.add(myFitness);
		}
		return fitness;
	}
}
