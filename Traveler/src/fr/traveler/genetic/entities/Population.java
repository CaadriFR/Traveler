package fr.traveler.genetic.entities;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.config.Config;
import fr.traveler.geography.entities.City;

/**
 * Classe représentant une population d'individus dans l'algorithme génétique.
 * Une population est constituée d'un ensemble d'individus (solutions) qui évoluent
 * au cours des itérations de l'algorithme.
 * 
 * La classe fournit des méthodes pour :
 * <ul>
 *   <li>Créer une nouvelle population.</li>
 *   <li>Initialiser des individus de manière aléatoire ou heuristique.</li>
 *   <li>Afficher la population ou l'individu avec les meilleures performances.</li>
 *   <li>Calculer les distances des cycles associés à chaque individu.</li>
 * </ul>
 * 
 * @author Néo Moret
 */
public class Population {

	/**
	 * Liste des villes représentant le problème à résoudre.
	 */
	private List<City> citiesOfProblem;

	/**
	 * Taille de la population.
	 */
	private int size;

	/**
	 * Liste des individus composant la population.
	 */
	private List<Individu> population = new ArrayList<>();

	/**
	 * Constructeur permettant de créer une population avec une liste de villes et
	 * une taille donnée.
	 * 
	 * @param citiesOfProblem Liste des villes associées au problème.
	 * @param size            Taille de la population à générer.
	 */
	public Population(List<City> citiesOfProblem, int size) {
		this.citiesOfProblem = new ArrayList<>(citiesOfProblem);
		this.size = size;
	}

	/**
	 * Constructeur permettant de créer une population à partir d'une liste
	 * d'individus existants.
	 * 
	 * @param population Liste des individus qui composent cette population.
	 */
	public Population(List<Individu> population) {
		this.size = population.size();
		this.population = population;
	}

	/**
	 * Constructeur permettant de créer une population avec une taille donnée.
	 * 
	 * @param size Taille de la population.
	 */
	public Population(int size) {
		this.size = size;
	}

	/**
	 * Affiche tous les individus de la population avec leurs cycles, distances et
	 * fitness.
	 */
	public void displayAllPopulation() {
		int count = 1;
		for (Individu individu : population) {
			System.out.println("Individu number : " + (count++) + " :");
			System.out.println(individu.displayCities());
			System.out.println(" DISTANCE : " + individu.getDistance());
			System.out.println(" FITNESS : " + individu.getFitness());
		}
	}

	/**
	 * Retourne la taille de la population.
	 * 
	 * @return La taille de la population.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Retourne la liste des individus composant la population.
	 * 
	 * @return La liste des individus de la population.
	 */
	public List<Individu> getPopulation() {
		return population;
	}

	/**
	 * Calcule les distances associées aux cycles de tous les individus de la
	 * population.
	 */
	public void computeAllDistances() {
		for (Individu individu : population) {
			individu.computeDistance();
		}
	}

	/**
	 * Crée un individu aléatoire à partir de la liste des villes du problème.
	 * 
	 * @return Un individu avec un cycle aléatoire.
	 */
	public Individu createRandomIndividu() {
		Individu individu = new Individu(new ArrayList<>(this.citiesOfProblem));
		individu.shuffleCycle();
		return individu;
	}

	/**
	 * Crée un individu de manière heuristique, en choisissant à chaque étape la
	 * ville la plus proche.
	 * 
	 * @return Un individu initialisé avec une heuristique de proximité.
	 */
	public Individu createIndividu() {
		List<City> remainingCities = new ArrayList<>(this.citiesOfProblem);
		List<City> cycle = new ArrayList<>();

		City currentCity = remainingCities.remove((int) (Math.random() * remainingCities.size()));
		cycle.add(currentCity);

		while (!remainingCities.isEmpty()) {
			City nearestCity = null;
			double minDistance = Double.MAX_VALUE;

			for (City city : remainingCities) {
				double distance = currentCity.distanceTo(city);
				if (distance < minDistance) {
					minDistance = distance;
					nearestCity = city;
				}
			}

			cycle.add(nearestCity);
			remainingCities.remove(nearestCity);
			currentCity = nearestCity;
		}

		return new Individu(cycle);
	}

	/**
	 * Génère une nouvelle population en combinant initialisations heuristique et
	 * aléatoire. Le pourcentage d'individus initialisés de manière heuristique est
	 * défini par {@code Config.HEURISTIC_INITIALISATION}.
	 */
	public void newPopulation() {
		int heuristicIndividuals = (int) Math.ceil(Config.HEURISTIC_INITIALISATION * this.size);

		// Initialisation heuristique
		for (int i = 0; i < heuristicIndividuals; i++) {
			Individu individu = createIndividu();
			this.population.add(individu);
		}

		// Initialisation aléatoire pour compléter la population
		while (this.population.size() < this.size) {
			Individu individu = createRandomIndividu();
			this.population.add(individu);
		}
	}
}