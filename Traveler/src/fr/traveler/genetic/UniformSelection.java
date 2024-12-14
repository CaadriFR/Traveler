/**
 * Classe permettant de sélectionner uniformément deux parents dans une population
 * afin de procéder à l'opération de croisement (crossing).
 * 
 * Cette classe implémente une méthode de sélection basée sur les fitness des individus.
 * 
 * @author Néo Moret
 */
package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.genetic.entities.Individu;

public class UniformSelection {

    /**
     * Liste des individus représentant la population.
     */
    private List<Individu> population;

    /**
     * Liste cumulative des fitness des individus, utilisée pour la sélection uniforme.
     */
    private List<Double> fitness_cumul;

    /**
     * Premier parent sélectionné.
     */
    private Individu parent1;

    /**
     * Deuxième parent sélectionné.
     */
    private Individu parent2;

    /**
     * Constructeur de la classe {@code UniformSelection}.
     * Initialise la population et prépare une liste vide pour les fitness cumulées.
     * 
     * @param population La population dans laquelle les deux parents seront sélectionnés.
     */
    public UniformSelection(List<Individu> population) {
        this.population = population;
        this.fitness_cumul = new ArrayList<>();
    }

    /**
     * Getter pour la population.
     * 
     * @return La liste des individus représentant la population.
     */
    public List<Individu> getPopulation() {
        return this.population;
    }

    /**
     * Getter pour le tableau cumulatif des fitness.
     * 
     * @return Une liste de doubles représentant les valeurs cumulées des fitness.
     */
    public List<Double> getTabFitnessCumul() {
        return this.fitness_cumul;
    }

    /**
     * Getter pour le premier parent sélectionné.
     * 
     * @return L'individu sélectionné comme premier parent.
     */
    public Individu getParent1() {
        return parent1;
    }

    /**
     * Getter pour le deuxième parent sélectionné.
     * 
     * @return L'individu sélectionné comme deuxième parent.
     */
    public Individu getParent2() {
        return parent2;
    }

    /**
     * Construit le tableau cumulatif des fitness pour la population.
     * Cette méthode calcule les valeurs cumulées des fitness normalisées en pourcentage
     * (sur une échelle de 0 à 100).
     */
    public void buildTabOfFitnessCumul() {
        double mysum = 0;

        // Calcul de la somme totale des fitness
        for (Individu individu : population) {
            mysum += individu.getFitness();
        }

        double fitness_cumul = 0;
        this.fitness_cumul.add(fitness_cumul);

        // Construction du tableau cumulatif
        for (Individu individu : population) {
            fitness_cumul += ((individu.getFitness() * 100) / mysum);
            this.fitness_cumul.add(fitness_cumul);
        }

        // Correction pour s'assurer que la dernière valeur est exactement 100
        if (this.fitness_cumul.get(this.fitness_cumul.size() - 1) != 100) {
            this.fitness_cumul.set(this.fitness_cumul.size() - 1, 100.0);
        }
    }

    /**
     * Sélectionne uniformément deux parents dans la population.
     * <p>
     * La méthode utilise un tableau cumulatif des fitness et génère des nombres
     * aléatoires pour déterminer les indices des parents sélectionnés. Les deux
     * parents sélectionnés sont différents.
     */
    public void ChooseMyParent() {
        buildTabOfFitnessCumul();

        // Sélection du premier parent
        int myRandom = (int) (Math.random() * 100);
        int indexOfParent1 = 0;

        while (true) {
            if (this.fitness_cumul.get(indexOfParent1) <= myRandom
                    && this.fitness_cumul.get(indexOfParent1 + 1) > myRandom) {
                break;
            }
            indexOfParent1++;
        }

        this.parent1 = population.get(indexOfParent1);

        // Sélection du deuxième parent
        myRandom = (int) (Math.random() * 100);
        int indexOfParent2 = 0;

        while (true) {
            if (this.fitness_cumul.get(indexOfParent2) <= myRandom
                    && this.fitness_cumul.get(indexOfParent2 + 1) >= myRandom) {
                if (indexOfParent2 == indexOfParent1) {
                    // Réinitialisation si le même parent est sélectionné
                    myRandom = (int) (Math.random() * 100);
                    indexOfParent2 = 0;
                    continue;
                } else {
                    break;
                }
            }
            indexOfParent2++;
        }

        this.parent2 = population.get(indexOfParent2);
    }
}