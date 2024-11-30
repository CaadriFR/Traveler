package genetic;
import java.util.ArrayList;
import java.util.Collections;

/* Cette classe vise à représenter la population initiale
 * La population initiale est l'ensemble des individus duquel nous 
 * partons pour, on l'espère, trouver le meilleur cycle Hamiltonien*/

public class Population_init {

    private ArrayList<City> cities_of_problem; //ArrayList des villes par lesquelles on va passer pour ressoudre le problème du voyageur de commerce
    private ArrayList<ArrayList<City>> population = new ArrayList<>(); //ArrayList de ArrayList de City : modélise notre population initiale
    private int size_of_population; // correspond au nombre de populations envisagées pour la première itération de l'algorithme génétique
    private double[] tab_of_fitness;
    
    
    // Constructeur
    public Population_init( ArrayList<City> cities_of_problem, int size_of_population) {
        this.cities_of_problem = cities_of_problem;
        this.size_of_population = size_of_population;
        this.tab_of_fitness = new double[ size_of_population ];
    }

    // Créer un nouvel individu et l'ajouter à la population
    
    public void createIndividual() {
    	
        ArrayList<City> individual = new ArrayList<>();

        // Créer une copie de la liste des villes
        for (City city : this.cities_of_problem) {
            individual.add(new City(city.getName(), Math.toDegrees(city.getLatitude()), Math.toDegrees(city.getLongitude())));
        }

        // Mélanger l'ordre des villes afin de ne pas avoir une population avec des individus semblables
        Collections.shuffle(individual);
               

        // Ajouter l'individu à la population
        this.population.add(individual);
    }
    
    public double getFitness( int i ) {
    	return this.tab_of_fitness[i];
    }

    // Initialiser la population avec le nombre d'individus spécifié
    public void init() {
        for (int i = 0; i < this.size_of_population; i++) {
            createIndividual();
            Fitness fitness = new Fitness();
            fitness.ComputeFitness( this.population.get(i) );
            this.tab_of_fitness[i] = fitness.getFitness();
            
        }
    }

    // Afficher une liste de villes
    public void displayCities(ArrayList<City> cityList) {
        for (City city : cityList) {
            System.out.println("Ville : " + city.getName() +
                               "; Latitude : " + city.getLatitude() +
                               "; Longitude : " + city.getLongitude());
        }
        

    }

    // Afficher toute la population
    public void displayPopulation() {
        for (int i = 0; i < this.population.size(); i++) {
            System.out.println("Population " + i + " :");
            displayCities(this.population.get(i));
            System.out.println();
            System.out.println("Fitness : " + getFitness(i) );

        }
        
       
    }

    // Méthode principale pour tester
    public static void main(String[] args) {
    

     
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Paris", 48.8566, 2.3522));
        cities.add(new City("Marseille", 43.2965, 5.3698));
        cities.add(new City("Lyon", 45.7640, 4.8357));
        cities.add(new City("Toulouse", 43.6047, 1.4442));
        cities.add(new City("Nice", 43.7102, 7.2620));
        cities.add(new City("Strasbourg", 48.5734, 7.7521));
        cities.add(new City("Montpellier", 43.6117, 3.8772));
        cities.add(new City("Lille", 50.6292, 3.0573));
        cities.add(new City("Reims", 49.2583, 4.0317));
        cities.add(new City("Le Havre", 49.4944, 0.1079));
        cities.add(new City("Saint-Étienne", 45.4397, 4.3872));
        cities.add(new City("Toulon", 43.1242, 5.9280));
        cities.add(new City("Grenoble", 45.1885, 5.7245));
        cities.add(new City("Dijon", 47.3220, 5.0415));
        cities.add(new City("Nîmes", 43.8375, 4.3601));
        cities.add(new City("Bordeaux", 44.8378, -0.5792));
        
        cities.add(new City("Aix-en-Provence", 43.5297, 5.4474));
        cities.add(new City("Clermont-Ferrand", 45.7772, 3.0870));
        cities.add(new City("Tours", 47.3941, 0.6848));
        cities.add(new City("Amiens", 49.8941, 2.2957));
        cities.add(new City("Limoges", 45.8315, 1.2578));

       
        //Créer et initialiser la population
        Population_init mypopulation = new Population_init(cities, 20);
        mypopulation.init();
        mypopulation.displayPopulation();
        
    }
}
