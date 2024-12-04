package fr.traveler.genetic;
import java.util.ArrayList;
import java.util.Comparator;

public class Elitism {
	
	private ArrayList<ArrayList<City>> old_population;
	private ArrayList<ArrayList<City>> new_population;
	private ArrayList<ArrayList<City>> final_population;
	private ArrayList<Double> fitnessOld;
	private ArrayList<Double> fitnessNew;

	private double rate_elite;
	
	public Elitism( ArrayList<ArrayList<City>> old_population, ArrayList<ArrayList<City>> new_population, ArrayList<Double> fitnessOld, ArrayList<Double> fitnessNew,double rate_elite ) {
		this.old_population = old_population;
		this.new_population = new_population;
		this.fitnessNew = fitnessNew;
		this.fitnessOld = fitnessOld;
		this.final_population = new ArrayList<ArrayList<City>>();
		this.rate_elite = rate_elite;
	}
	
	public ArrayList<ArrayList<City>> getOldPopulation(){
		return this.old_population;
	}
	
	public ArrayList<ArrayList<City>> getNewPopulation(){
		return this.new_population;
	}
	
	public ArrayList<ArrayList<City>> getFinalPopulation(){
		return this.final_population;
	}
	
	public ArrayList<Double> getFitnessOld() {
		return this.fitnessOld;
	}
	
	public ArrayList<Double> getFitnessNew() {
		return this.fitnessNew;
	}
	
	public void setRateElite( double newRateElite ) {
		// ajouter contrainte d'intégrité
		this.rate_elite = newRateElite;
		
	}
	
	public ArrayList<ArrayList<City>> ReorderMyPopulation( ArrayList<ArrayList<City>> population, ArrayList<Double> fitness){
		
		ArrayList<Integer> index = new ArrayList<>();
		for (int i = 0; i < fitness.size() ; i++) {
            index.add(i);
        }
		
        index.sort(Comparator.comparingDouble(i -> fitness.get((int) i)));


        //index.sort((i1, i2) -> Double.compare(fitness[i2], fitness[i1]));
        
        ArrayList<ArrayList<City>> populationOrdered = new ArrayList<ArrayList<City>>();
        
        for (int i : index) {
            populationOrdered.add( population.get(i) );
        }
        
        
        return populationOrdered;

		
		
	}
	
	public void BuildMyFinalPopulation() {
		
		int index_rateElite = (int) Math.ceil(this.rate_elite * this.new_population.size());
		
		
		ArrayList<ArrayList<City>> old_populationOrdered = ReorderMyPopulation( this.old_population, this.fitnessOld);
		ArrayList<ArrayList<City>> new_populationOrdered = ReorderMyPopulation( this.new_population, this.fitnessNew);
		

		for( int i = 0; i < index_rateElite; i++) {
			this.final_population.add(new_populationOrdered.get(i));
		}
		
		for( int j = index_rateElite; j < this.old_population.size(); j++) {
			this.final_population.add(old_populationOrdered.get(j));
		}
		
	
	}

}
