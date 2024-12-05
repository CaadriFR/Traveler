package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.geography.entities.City;

public class Crossing {

	private List<City> parent1; //modelize parent1
	private List<City> parent2; //modelize parent2
	private List<City> child1;	 //modelize child1
	private List<City> child2; //modelize child2
	private List<List<Integer>> cycles; //modelize the cycles fort the implementation of child1 and child2
	private int size;

	
// ------------------------------------------------------------------------------
//Constructor : 
	
	public Crossing(List<City> parent1, List<City> parent2) {

		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child1 = new ArrayList<City>();
		this.child2 = new ArrayList<City>();
		this.cycles = new ArrayList<List<Integer>>(); //each List of Integer modelize a cycle : a cycle it's a suite of index that correspond to the place of the city in parent1 wich is imposed
		this.size = parent1.size(); //size of individual

		//Initialization of our child
		for (int i = 0; i < this.size; i++) {
			this.child1.add(null);
			this.child2.add(null);
		}
	}

//---------------------------------------------------------------------------------------
//getters 
	
	public List<City> getChild1() {
		return this.child1;
	}

	public List<City> getChild2() {
		return this.child2;
	}
	
	public List<City> getParent1(){
		return this.parent1;
	}
	
	public List<City> getParent2(){
		return this.parent2;
	}

	public List<List<Integer>> getCycles(){
		return this.cycles;
	}
	
	public int getSize() {
		return this.size;
	}
	
//---------------------------------------------------------------------------------
	
	//method who return the index of 'current_city' in parent2; -1 of not in
	public int getIndex(City current_city, List<City> parent2) {
		for (int i = 0; i < this.size; i++) {
			
			if (current_city.getName().equals(parent2.get(i).getName())) {
				return i;	
			}
		}
		return -1;
	}
	
	//buildCycle is the mother method of this class 
	//we build a List of integer (cycle) that correspond of the placement imposed by 
	//the form of parent1 and parent2

	public void buildCycle() {

		boolean[] isVisited = new boolean[this.size];

		int index;

		for (index = 0; index < this.size; index++) {
			if (isVisited[index])
				continue;

			List<Integer> cycle = new ArrayList<>();
			int current_index = index;

			do {
				cycle.add(current_index);
				isVisited[current_index] = true;

				City current_city = this.parent1.get(current_index);
				current_index = getIndex(current_city, this.parent2);

			} while (current_index != index);

			this.cycles.add(cycle);
			
		}


	}

	public void buildChilds() {

		buildCycle();

		int randomInt;

		for (List<Integer> cycle : this.cycles) {

			randomInt = (int) (Math.random() * 2);

			for (int index : cycle) {
				if (randomInt == 0) {
					child1.set(index, parent1.get(index));
					child2.set(index, parent2.get(index));
				} else {
					child1.set(index, parent2.get(index));
					child2.set(index, parent1.get(index));
				}
			}
		}

	}
}
