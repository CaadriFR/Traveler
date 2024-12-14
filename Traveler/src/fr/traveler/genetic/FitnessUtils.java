/**
 * Classe utilitaire permettant de calculer des informations relatives au fitness
 * d'une population d'individus. 
 * Elle fournit une méthode statique pour analyser et traiter une population.
 * 
 * @author Néo Moret
 */

package fr.traveler.genetic;

import java.util.List;
import fr.traveler.genetic.entities.Individu;

public class FitnessUtils {

    /**
     * Méthode statique permettant de trouver la plus petite distance dans une population.
     * 
     * @param population La population d'individus dont on souhaite déterminer la distance minimale.
     * @return La plus petite distance parmi les individus de la population.
     * @throws IllegalArgumentException si la population est vide.
     */
    public static double getMinDistance(List<Individu> population) {
        if (population == null || population.isEmpty()) {
            throw new IllegalArgumentException("La population ne peut pas être nulle ou vide.");
        }

        double minDistance = population.get(0).getDistance(); // Correction : population.getFirst() n'est pas une méthode valide pour une List
        for (Individu individu : population) {
            if (individu.getDistance() < minDistance) {
                minDistance = individu.getDistance();
            }
        }
        return minDistance;
    }
}
