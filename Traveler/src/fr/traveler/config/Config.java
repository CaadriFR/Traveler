package fr.traveler.config;

/**
 * Classe de configuration pour les paramètres globaux du projet. Cette classe
 * contient des constantes qui contrôlent le comportement de l'algorithme
 * génétique, la gestion de la solution, et la connexion à la base de données.
 * 
 * <p>
 * Elle est utilisée pour centraliser toutes les configurations afin de
 * faciliter leur modification sans devoir parcourir tout le code.
 * </p>
 * 
 * @author Adrien Riffaut
 * @author Néo Moret
 */
public class Config {

	// Configuration de l'algorithme génétique
	/**
	 * Probabilité de mutation pour l'algorithme génétique.
	 * 
	 * Valeur comprise entre 0 et 1.
	 */
	public static final double MUTATION = 0.1;

	/**
	 * Taux d'élitisme pour l'algorithme génétique. Proportion des meilleurs
	 * individus conservés dans chaque génération.
	 * 
	 * Valeur comprise entre 0 et 1.
	 */
	public static final double ELITISM = 0.2;

	/**
	 * Proportion d'individus initialisés heuristiquement dans la population
	 * intiale. Permet de converger plus rapidement vers la solution.
	 * 
	 * Valeur comprise entre 0 et 1.
	 */
	public static final double HEURISTIC_INITIALISATION = 0;

	/**
	 * Taille de la population utilisée par l'algorithme génétique. Définit le
	 * nombre total d'individus dans chaque génération.
	 */
	public static final int SIZE_POPULATION = 500;

	/**
	 * Nombre maximum d'itérations que l'algorithme génétique peut effectuer. Limite
	 * supérieure pour éviter une exécution infinie.
	 */
	public static final int MAX_ITERATIONS = 5000;

	/**
	 * Nombre maximal d'itérations consécutives sans amélioration du fitness.
	 * Utilisé pour détecter une stagnation.
	 */
	public static final int MAX_STAGNATION = 1000;

	// Configuration de l'écriture de la solution
	/**
	 * Indicateur déterminant si la solution doit être enregistrée dans un fichier.
	 * <code>true</code> pour activer l'enregistrement, <code>false</code> sinon.
	 */
	public static final boolean SOLUTION_TO_FILE = true;

	/**
	 * Chemin où la solution sera enregistrée si l'option est activée.
	 */
	public static final String SOLUTION_PATH = "/Users/adrienriffaut/Desktop/Projet/";

	// Affichage des résultats graphiques
	/**
	 * Indicateur déterminant si la map et le graphique de l'évolution du fitness
	 * doivent s'afficher une fois la solution trouvée. <code>true</code> pour
	 * activer l'affichage, <code>false</code> sinon.
	 */
	public static final boolean DISPLAY_SOLUTION = true;

	// Configuration de la base de données
	/**
	 * URL de connexion à la base de données PostgreSQL. Inclut le nom d'hôte, le
	 * port et le nom de la base de données cible.
	 */
	public static final String URL = "jdbc:postgresql://localhost:5432/projet_bdd";

	/**
	 * Nom d'utilisateur pour se connecter à la base de données.
	 */
	public static final String USER = "postgres";

	/**
	 * Mot de passe pour se connecter à la base de données.
	 */
	public static final String PASSWORD = "Salut";

}