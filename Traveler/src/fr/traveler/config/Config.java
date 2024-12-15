package fr.traveler.config;

public class Config {

    public static final double MUTATION = 0.1;  
    public static final double ELITISM = 0.2;
    public static final double HEURISTIC_INITIALISATION = 0;
    public static final int SIZE_POPULATION = 500;
    public static final int MAX_ITERATIONS = 5000; 
    public static final int MAX_STAGNATION = 1000;
    
    //BDD
	public static final String URL = "jdbc:postgresql://localhost:5432/projet_bdd";
	public static final String USER = "postgres";
	public static final String PASSWORD = "Salut";

}