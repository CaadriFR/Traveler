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
	
	
	
	
	public void GeneticAlgorithm( ArrayList<City> cities_of_problem, int size_of_population) {
		
		Population_init initialPopulation = new Population_init(cities_of_problem, size_of_population);
		initialPopulation.newPopulation();
		Fitness myfitness;
		myfitness = new Fitness(initialPopulation.getListOfPopulation());
		myfitness.ComputeFitness();
		
		Population_init transformedPopulation;
		UniformSelection myselectionOfParents;
		Crossing myCrossing;
		Mutation mymutation1;
		Mutation mymutation2;
		Elitism myElit;
		
		
		int i = 0;
		
		while( i < 10000) {
			i++;
			
			ArrayList<ArrayList<City>> newPopulation = new ArrayList<ArrayList<City>>();
			
			for(int j = 0; j < initialPopulation.getSizeOfPopulation() / 2 ; j++) {
				
				myselectionOfParents = new UniformSelection(initialPopulation.getListOfPopulation(), myfitness.getFitness() );
				myselectionOfParents.ChooseMyParent();
				
				myCrossing = new Crossing(myselectionOfParents.getParent1(), myselectionOfParents.getParent2());
				myCrossing.buildChilds();
				
				mymutation1 = new Mutation(myCrossing.getChild1(), 0.3);
				mymutation2 = new Mutation(myCrossing.getChild2(), 0.3);
				
				mymutation1.mutateChilds();
				mymutation2.mutateChilds();
				
				newPopulation.add(mymutation1.getChildPrime());
				newPopulation.add(mymutation2.getChildPrime());
			}
			
			transformedPopulation = new Population_init(newPopulation);
			
			myfitness = new Fitness(transformedPopulation.getListOfPopulation());
			myfitness.ComputeFitness();
			
			myElit = new Elitism( initialPopulation.getListOfPopulation() ,transformedPopulation.getListOfPopulation(), initialPopulation.getTabFitness(), myfitness.getFitness(), 0.5);
			myElit.BuildMyFinalPopulation();
			
			initialPopulation = new Population_init(myElit.getFinalPopulation());	
			myfitness = new Fitness(myElit.getFinalPopulation());
			myfitness.ComputeFitness();

			
			//initialPopulation.displayPopulation();
	
		} 
		System.out.println("Fitness final :");
		System.out.println(initialPopulation.getTabFitness());
		this.hamiltonienSolution = initialPopulation.getListOfPopulation().getLast();
	}

}
