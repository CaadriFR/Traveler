package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.geography.entities.City;

public class GeneticManager {
	
	private List<City> hamiltonienSolution;
	private double fitness;
	private double distance;
	private List<City> individu;
	
	public GeneticManager(List<City> individu) {
		this.hamiltonienSolution = new ArrayList<City>();
		this.individu = individu;
	}
	
	public List<City> getSolution() {
		return this.hamiltonienSolution;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getFitness() {
		return this.fitness;
	}
	
	public void startGeneticAlgorithm(int sizeOfPopulation) {
		
		Population mypopulation1 = new Population(this.individu, sizeOfPopulation);
		Population mypopulation2;
		UniformSelection selection;
		Crossing mycross;
		Mutation mymutation1;
		Mutation mymutation2;
		Elitism myelite;
	
		List<City> parent1;
		List<City> parent2;
		List<City> child1;
		List<City> child2;
		
		//double fitnessChild1;
		//double fitnessChild2;
		
		mypopulation1.newPopulation();
		mypopulation1.displayAllPopulation();
		
		int i = 0;
		int j;
		
		int noImprovementCounter = 0;
        double bestDistance = Fitness.minDistance(mypopulation1.getTabOfDistance());
		
        for (int iteration = 0; iteration < 500000 && noImprovementCounter < 2000; iteration++) {

			
			i++;
			j=0;
			
			mypopulation2 = new Population( sizeOfPopulation );
			
			while( j < sizeOfPopulation / 2) {
				
				selection = new UniformSelection(mypopulation1.getPopulation(), mypopulation1.getTabOfFitness());
				selection.ChooseMyParent();
				
				parent1 = selection.getParent1();
				parent2 = selection.getParent2();
				
				mycross = new Crossing(parent1, parent2);
				mycross.buildChilds();
				child1 = mycross.getChild1();
				child2 = mycross.getChild2();
				
				mymutation1 = new Mutation(child1, 0.05);
				mymutation2 = new Mutation(child2, 0.05);
				
				mymutation1.mutateChilds();
				mymutation1.mutateChilds();
				
				mypopulation2.addIndividual(mymutation1.getChildPrime());
				mypopulation2.addIndividual(mymutation2.getChildPrime());
				j++;
				
			}
			
			mypopulation2.setTabOfDistance();
			mypopulation2.setTabOfFitness();

			myelite = new Elitism(mypopulation1.getPopulation(), mypopulation2.getPopulation(), mypopulation1.getTabOfFitness() , mypopulation2.getTabOfFitness(), 0.4);
			myelite.BuildMyFinalPopulation();
			
			mypopulation1 = new Population( myelite.getFinalPopulation() );
			mypopulation1.setTabOfDistance();
			mypopulation1.setTabOfFitness(); 
			
			double currentBestDistance = Fitness.minDistance(mypopulation1.getTabOfDistance());

	        if (currentBestDistance < bestDistance) {
	        	bestDistance = currentBestDistance;
	            noImprovementCounter = 0;
	        } else {
	        	noImprovementCounter++;
	        }
	
		} 
		
		System.out.println("Itérations :" + i);
	
		this.hamiltonienSolution = mypopulation1.getPopulation().getFirst();
		this.distance = mypopulation1.getDistance(0);
		this.fitness = mypopulation1.getFitness(0);
		
		mypopulation1.displayAllPopulation();

	}

}
