package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.traveler.config.Config;
import fr.traveler.genetic.entities.Individu;

/**
 * Cette classe vise à implémenter la population finale à l'itération courante.
 * À chaque itération dans {@code GeneticManager}, une nouvelle population est générée
 * sur la base de l'ancienne population. Cette classe sélectionne les {@code Individu}s
 * de l'ancienne et de la nouvelle population à inclure dans la population finale.
 * 
 * Note : 
 * La population finale ne correspond pas à la solution finale au problème du voyageur de commerce.
 * Elle représente simplement la population finale générée à l'itération actuelle de {@code GeneticManager}.
 * 
 * @author Néo Moret
 */
public class Elitism {

	/*
	 * Ancienne population
	 */
	private List<Individu> old_population;
	
	/*
	 * Nouvelle population
	 */
	private List<Individu> new_population;
	
	/*
	 * Population finale
	 */
	private List<Individu> final_population;
	

	/**
	 * Constructeur de la classe {@code Elitism}. Permet d'initialiser l'élitisme
	 * avec les populations de départ et le taux d'élite.
	 * 
	 * @param old_population L'ancienne population.
	 * @param new_population La nouvelle population.
	 */
	public Elitism(List<Individu> old_population, List<Individu> new_population) {
		this.old_population = old_population;
		this.new_population = new_population;
		this.final_population = new ArrayList<>();
	}

	/**
	 * Getter pour l'ancienne population.
	 * 
	 * @return La liste des {@code Individu}s correspondant à l'ancienne population.
	 */
	public List<Individu> getOldPopulation() {
		return this.old_population;
	}

	/**
	 * Getter pour la nouvelle population.
	 * 
	 * @return La liste des {@code Individu}s correspondant à la nouvelle
	 *         population.
	 */
	public List<Individu> getNewPopulation() {
		return this.new_population;
	}

	/**
	 * Getter pour la population finale.
	 * 
	 * @return La liste des {@code Individu}s correspondant à la population finale.
	 */
	public List<Individu> getFinalPopulation() {
		return this.final_population;
	}

	/**
	 * Méthode qui réarrange une population par ordre décroissant de fitness. Une
	 * fois la population réorganisée, le meilleur individu est en position 0, le
	 * second meilleur en position 1, etc.
	 * 
	 * @param population La population à réorganiser.
	 * @return Une nouvelle liste d'individus triée par ordre décroissant de
	 *         fitness.
	 */
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

	/**
	 * Méthode principale de la classe {@code Elitism}. Cette méthode construit la
	 * population finale en combinant l'ancienne et la nouvelle population.
	 * <ul>
	 * <li>Elle sélectionne un certain pourcentage d'individus de la nouvelle
	 * population, basé sur le taux choisi dans la configuration.</li>
	 * <li>Elle complète avec les individus de l'ancienne population pour atteindre
	 * la taille totale de la population.</li>
	 * </ul>
	 * 
	 * Le tri des populations est effectué à l'aide de {@code ReorderMyPopulation}.
	 */
	public void BuildMyFinalPopulation() {
		int index_rateElite = (int) Math.ceil(Config.ELITISM * this.new_population.size());

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