package genetic;

import java.util.ArrayList;

public class Crossing {
	
	private ArrayList<City> parent1;
	private ArrayList<City> parent2;
	private ArrayList<City> child1;
	private ArrayList<City> child2;
	private ArrayList<ArrayList<Integer>> cycles;
	private int size;

	public Crossing( ArrayList<City> parent1, ArrayList<City> parent2) {
		
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child1 = new ArrayList<City>();
		this.child2 = new ArrayList<City>();
		this.cycles = new ArrayList<ArrayList<Integer>>();
		this.size = parent1.size();
		
		for( int i=0; i < this.size ; i++) {
			this.child1.add(null);
			this.child2.add(null);
		}	
	}
	
	public ArrayList<City> getChild1(){
		return this.child1;
	}
	
	public ArrayList<City> getChild2(){
		return this.child2;
	}
	
	
		
	public int getIndex( City current_city,  ArrayList<City> parent2) {
		for( int i = 0; i < this.size; i++ ) {
			if( current_city.getName().equals(parent2.get(i).getName()) ) {
				return i;
			}
		}
		return -1;
	}
	
	public void buildCycle() {
		
		boolean[] isVisited = new boolean[this.size];
		
		int index;
		
		for( index = 0; index < this.size ; index ++) {
			if(isVisited[index]) continue; 
			
			ArrayList<Integer> cycle = new ArrayList<>();
			int current_index = index;
			
			do {
				cycle.add(current_index);
				isVisited[current_index] = true;
				
				City current_city = this.parent1.get(current_index);
				current_index = getIndex(current_city, this.parent2);
				
			} while( current_index != index );
			
			this.cycles.add(cycle);
		}
		
		System.out.println(this.cycles);
		
	}
	
	public void buildChilds() {
		
		buildCycle();
		
		int randomInt;
		

        for (ArrayList<Integer> cycle : this.cycles) {
        	
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
