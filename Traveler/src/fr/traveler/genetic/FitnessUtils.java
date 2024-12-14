package fr.traveler.genetic;

import java.util.List;

import fr.traveler.genetic.entities.Individu;

public class FitnessUtils {

	public static double getMinDistance(List<Individu> population) {
		double minDistance = population.getFirst().getDistance();
		for (Individu individu : population) {
			if (individu.getDistance() < minDistance)
				minDistance = individu.getDistance();
		}
		return minDistance;

	}

}
