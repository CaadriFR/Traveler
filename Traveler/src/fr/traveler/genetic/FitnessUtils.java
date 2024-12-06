package fr.traveler.genetic;

import java.util.List;

import fr.traveler.genetic.entities.Individu;

public class FitnessUtils {
	private static final double EPSILON = 1e-6;

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

}
