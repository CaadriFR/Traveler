package fr.traveler.genetic;

import java.util.ArrayList;

public class GeneticSolution {
	
	private ArrayList<City> hamiltonienSolution;
	private double fitness;
	private double distance;
	
	public GeneticSolution() {
		this.hamiltonienSolution = new ArrayList<City>();
	}
	
	public ArrayList<City> getSolution() {
		return this.hamiltonienSolution;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getFitness() {
		return this.fitness;
	}
	
	
	
	
	public void GeneticAlgorithm( ArrayList<City> citiesOfProblem, int sizeOfPopulation) {
		
		Population mypopulation1 = new Population(citiesOfProblem, sizeOfPopulation);
		Population mypopulation2;
		UniformSelection selection;
		Crossing mycross;
		Mutation mymutation1;
		Mutation mymutation2;
		Elitism myelite;
	
		ArrayList<City> parent1;
		ArrayList<City> parent2;
		ArrayList<City> child1;
		ArrayList<City> child2;
		
		//double fitnessChild1;
		//double fitnessChild2;
		
		mypopulation1.newPopulation();
		mypopulation1.displayAllPopulation();
		
		int i = 0;
		int j;
		
		int noImprovementCounter = 0;
        double bestDistance = Fitness.minDistance(mypopulation1.getTabOfDistance());
		
        for (int iteration = 0; iteration < 500000 && noImprovementCounter < 1000; iteration++) {

			
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
				
				//fitnessChild1 = Fitness.computeDistance(mymutation1.getChildPrime());
				//fitnessChild2 = Fitness.computeDistance(mymutation2.getChildPrime());

				//if (Fitness.isSame(fitnessChild1,mypopulation2.getTabOfFitness()) || Fitness.isSame( fitnessChild2,mypopulation2.getTabOfFitness() )) {
					//continue;	
				//}
				
				mypopulation2.addIndividual(mymutation1.getChildPrime());
				mypopulation2.addIndividual(mymutation2.getChildPrime());
				j++;
				
			}
			
			mypopulation2.setTabOfDistance();
			mypopulation2.setTabOfFitness();

			myelite = new Elitism(mypopulation1.getPopulation(), mypopulation2.getPopulation(), mypopulation1.getTabOfFitness() , mypopulation2.getTabOfFitness(), 0.3);
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
