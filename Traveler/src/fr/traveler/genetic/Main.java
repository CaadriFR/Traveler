package fr.traveler.genetic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<City> cities = new ArrayList<>();
		cities.add(new City("Paris", 48.8566, 2.3522));
		cities.add(new City("Marseille", 43.2965, 5.3698));
		//cities.add(new City("Lyon", 45.7640, 4.8357));
		//cities.add(new City("Toulouse", 43.6047, 1.4442));
		cities.add(new City("Nice", 43.7102, 7.2620));
		//cities.add(new City("Strasbourg", 48.5734, 7.7521));
		//cities.add(new City("Montpellier", 43.6117, 3.8772));
		cities.add(new City("Lille", 50.6292, 3.0573));
		//cities.add(new City("Reims", 49.2583, 4.0317));
		cities.add(new City("Le Havre", 49.4944, 0.1079));
		//cities.add(new City("Saint-Étienne", 45.4397, 4.3872));
		//cities.add(new City("Toulon", 43.1242, 5.9280));
		//cities.add(new City("Grenoble", 45.1885, 5.7245));
		//cities.add(new City("Dijon", 47.3220, 5.0415));
		//cities.add(new City("Nîmes", 43.8375, 4.3601));
		//cities.add(new City("Bordeaux", 44.8378, -0.5792));
		cities.add(new City("Aix-en-Provence", 43.5297, 5.4474));
		//cities.add(new City("Clermont-Ferrand", 45.7772, 3.0870));
		//cities.add(new City("Tours", 47.3941, 0.6848));
		//cities.add(new City("Amiens", 49.8941, 2.2957));
		//cities.add(new City("Limoges", 45.8315, 1.2578));
		
		GeneticSolution mysol = new GeneticSolution();
		mysol.GeneticAlgorithm(cities, 10);
		
		
		//GeneticSolution mysolution = new GeneticSolution();
		//mysolution.GeneticAlgorithm(cities, 100);
		
		//System.out.println("Solution : ");
		//System.out.println(mysolution.getSolution());
	

		/*// Créer et initialiser la population
		Population mypopulation1 = new Population(cities, 10);
		mypopulation1.newPopulation();
		mypopulation1.displayAllPopulation();
		
		
		
		//Population_init mypopulation2 = new Population_init(cities, 5);
		//mypopulation2.newPopulation();
		//mypopulation2.displayPopulation();
		
		UniformSelection selection = new UniformSelection(mypopulation1.getListOfPopulation(), mypopulation1.getTabOfFitness());
		
		selection.ChooseMyParent();
		
		System.out.println(selection.getTabFitnessCumul());
		
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
		
		
		Mutation mymutation1 = new Mutation(child1, 0.7);
		Mutation mymutation2 = new Mutation(child2, 0.7);
		
		
		
		
		System.out.println(child1);
		mymutation1.mutateChilds();
		System.out.println(mymutation1.getChildPrime());
		System.out.println(child2);
		mymutation1.mutateChilds();
		System.out.println(mymutation2.getChildPrime());
		
		
		
		
		//Elitism myelite = new Elitism(mypopulation1.getListOfPopulation(), mypopulation2.getListOfPopulation(), mypopulation1.getTabFitness(), mypopulation2.getTabFitness(), 0.5);
		
		//mypopulation2.displayPopulation();
		//mypopulation1.displayPopulation();
		
		//myelite.BuildMyFinalPopulation();
		
		
		
		//mypopulation2.displayPopulation();*/


		
			
		}
		
	
		
	

		

	}

