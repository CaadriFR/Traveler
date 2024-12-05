package fr.traveler.genetic;

import java.util.ArrayList;

public class Fitness {
    private static final double EPSILON = 1e-6;
	
	public static boolean isSame( double distance, ArrayList<Double> tabOfDistance ) {
		for (double d : tabOfDistance) {
            if (Math.abs(d - distance) < EPSILON) return true; 
        }
        return false;
		
	}
	
	public static double computeDistance( ArrayList<City> individual ) {
		double myDistance=0;
		for(int  i = 0 ; i < individual.size() - 1 ; i++) {
			myDistance += individual.get(i).distanceTo(individual.get(i + 1));
		}
		myDistance += individual.get(individual.size()-1).distanceTo(individual.get(0));
		return myDistance;	
	}
	
	public static  ArrayList<Double> computeTabOfDistance( ArrayList<ArrayList<City>> population) {
		ArrayList<Double> distance = new ArrayList<Double>();
		for (ArrayList<City> individual : population) {
            distance.add(computeDistance(individual));
        }
        return distance;
	}
	
	public static double minDistance( ArrayList<Double> distance ) {
		double mymin=distance.get(0);
		for( Double myDistance : distance ) {
			if(myDistance < mymin) mymin = myDistance;
		}
		return mymin;
		
	}
	
	public static ArrayList<Double> computeTabOfFitness( ArrayList<ArrayList<City>> population, ArrayList<Double> distance) {
		double myFitness;
		ArrayList<Double> fitness = new ArrayList<Double>();
		for( int i = 0; i < population.size() ; i++ ) {
			myFitness = 10000 / distance.get(i)    ;
			fitness.add(myFitness);
		}	
		return fitness;
	}
}
