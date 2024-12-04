package fr.traveler.genetic;

import java.util.ArrayList;

public class GeneticSolution {
	
	private ArrayList<City> hamiltonienSolution;
	
	public GeneticSolution() {
		this.hamiltonienSolution = new ArrayList<City>();
	}
	
	public ArrayList<City> getSolution() {
		return this.hamiltonienSolution;
	}
	
	
	
	
	public void GeneticAlgorithm( ArrayList<City> citiesOfProblem, int sizeOfPopulation) {
		
		Population mypopulation1 = new Population(citiesOfProblem, sizeOfPopulation);
		mypopulation1.newPopulation();
		mypopulation1.displayAllPopulation();
		
		int i = 0;
		int j;
		
		while( i < 20000) {
			
			i++;
			j=0;
			
			Population newPopulation = new Population( sizeOfPopulation );
			
			while( j < sizeOfPopulation / 2) {
				
				UniformSelection selection = new UniformSelection(mypopulation1.getListOfPopulation(), mypopulation1.getTabOfFitness());
				
				selection.ChooseMyParent();
								
				ArrayList<City> parent1 = selection.getParent1();
				ArrayList<City> parent2 = selection.getParent2();
				
				System.out.println("Parent 1 :");
				System.out.println(parent1);
				System.out.println("Parent 2 :");
				System.out.println(parent2);
				
	
				Crossing mycross = new Crossing(parent1, parent2);
				
				mycross.buildChilds();
				
				ArrayList<City> child1 = mycross.getChild1();
				ArrayList<City> child2 = mycross.getChild2();
				
				
				Mutation mymutation1 = new Mutation(child1, 0.1);
				Mutation mymutation2 = new Mutation(child2, 0.1);
				
				System.out.println(child1);
				mymutation1.mutateChilds();
				System.out.println(mymutation1.getChildPrime());
				System.out.println(child2);
				mymutation1.mutateChilds();
				System.out.println(mymutation2.getChildPrime());
				
				double fitnessChild1 = Fitness.computeFitness( mymutation1.getChildPrime() );
				double fitnessChild2 = Fitness.computeFitness( mymutation2.getChildPrime() );

				if (Fitness.isSame( fitnessChild1,newPopulation.getTabOfFitness()) || Fitness.isSame( fitnessChild1,newPopulation.getTabOfFitness() )) {
					continue;	
				}
				
				newPopulation.addIndividual(mymutation1.getChildPrime(), fitnessChild1);
				newPopulation.addIndividual(mymutation2.getChildPrime(), fitnessChild2);
				
				j++;
				
			}
			
			Elitism myelite = new Elitism(mypopulation1.getListOfPopulation(), newPopulation.getListOfPopulation(), mypopulation1.getTabOfFitness() , newPopulation.getTabOfFitness(), 0.3);
			myelite.BuildMyFinalPopulation();
			mypopulation1 = new Population( myelite.getFinalPopulation() );
			mypopulation1.setTabOfFitness();
			
	
		} 
	
		this.hamiltonienSolution = mypopulation1.getListOfPopulation().getFirst();
		System.out.println("Solution :");
		mypopulation1.displayAllPopulation();
	}

}
