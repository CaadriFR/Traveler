/**
 * Classe principale du package Genetic.
 * Cette classe implémente l'algorithme génétique pour résoudre le problème du voyageur de commerce.
 * 
 * L'algorithme est implémenté par l'appel successif des différentes méthodes présentes dans les classes
 * du package `genetic`.
 * 
 * @author Néo Moret
 */
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

    /**
     * Constructeur de la classe {@code GeneticManager}.
     * Initialise la solution et l'évolution des fitness au cours des itérations.
     */
    public GeneticManager() {
        this.individu = new Individu(new ArrayList<>());
        this.fitnessEvolution = new ArrayList<>();
    }

    /**
     * Getter pour la solution.
     * 
     * @return L'individu correspondant à la solution du problème du voyageur de commerce.
     */
    public Individu getSolution() {
        return this.individu;
    }

    /**
     * Getter pour l'évolution des fitness.
     * 
     * @return Une liste de doubles représentant l'évolution des fitness au fil des itérations.
     */
    public List<Double> getFitnessEvolution() {
        return this.fitnessEvolution;
    }

    /**
     * Méthode principale de la classe, implémentant l'algorithme génétique.
     * 
     * Cette méthode exécute l'algorithme selon les étapes suivantes :
     * <ul>
     *   <li>Initialisation de la population</li>
     *   <li>Choix des parents</li>
     *   <li>Opérations de croisement et de mutation</li>
     *   <li>Application de l'élitisme pour former la population suivante</li>
     *   <li>Calcul des distances et mise à jour de la meilleure solution</li>
     * </ul>
     * 
     * L'algorithme s'arrête lorsque le nombre maximal d'itérations est atteint ou lorsqu'il
     * n'y a plus d'améliorations pendant une période définie.
     * 
     * @param citiesOfProblem Liste des villes à parcourir pour résoudre le problème.
     * @param size Taille de la population initiale à créer.
     */
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
        mypopulation1.displayBestIndividu();

        int i = 0;
        int j;

        int noImprovementCounter = 0;
        double bestDistance = FitnessUtils.getMinDistance(mypopulation1.getPopulation());
        fitnessEvolution.add(10000 / bestDistance);

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
