package fr.traveler.genetic;

import java.util.ArrayList;

public class Mutation {
	
	private ArrayList<City> child_prime;
	private double prob_mutate;
	private int size;
	
	//---------------------------------------------------------------
	//constructor
	
	public Mutation( ArrayList<City> child,  double prob_mutate) {
		this.child_prime = child;
		this.prob_mutate = prob_mutate;
		this.size = child.size();
	}
	
	//----------------------------------------------------------------
	//Getters : 
	
	public ArrayList<City> getChildPrime(){
		return this.child_prime;
	}
	
	public double getProbMutate() {
		return this.prob_mutate;
	}
	
	public int getSize() {
		return this.size;
	}
	
	//----------------------------------------------------------------
	// Setter
	
	//if we want change the probability of mutation 
	public void setProbMuate( double new_ProbMutate) {
		this.prob_mutate = new_ProbMutate;
	}
	
	//----------------------------------------------------------------
	
	public void mutateChilds() {
		
		double randomDouble = Math.random(); 
		int randomIndex_1;
		int randomIndex_2;
		
		if( randomDouble <= this.prob_mutate ) {
			
			do {
				
				randomIndex_1 = (int) (Math.random() * this.size); 
				randomIndex_2 = (int) (Math.random() * this.size); 	
				
				
			} while( randomIndex_1 == randomIndex_2);
			
			//System.out.println("Mumation de la ville de : " + 
			//this.child_prime.get(randomIndex_1).getName() + 
			//" avec la ville de : " + this.child_prime.get(randomIndex_2).getName() + "\n");
			
			City temp_city = this.child_prime.get(randomIndex_1);
			this.child_prime.set( randomIndex_1, this.child_prime.get(randomIndex_2) );
			this.child_prime.set( randomIndex_2, temp_city);
	
		}
		
		else {
			//System.out.println("Pas de mutation " + 
			//randomDouble +  " supérieur à " + this.prob_mutate + "\n");

		}
		
	}
	
	
	

}
