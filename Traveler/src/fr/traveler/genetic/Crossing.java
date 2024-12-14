package fr.traveler.genetic;

import java.util.ArrayList;
import java.util.List;

import fr.traveler.genetic.entities.Individu;
import fr.traveler.geography.entities.City;

/**
 * Classe qui permet de réaliser l'opération de croisement entre deux parents.
 * 
 * @author Néo MORET
 */
public class Crossing {

	/*
	 * Premier parent
	 */
	private Individu parent1;
	
	/*
	 * Deuxième parent
	 */
	private Individu parent2;
	
	/*
	 * Premier enfant créé par l'opération de croisement
	 */
	private Individu child1;
	
	/*
	 * Deuxième enfant créé par l'opération de croisement
	 */
	private Individu child2;
	
	/*
	 * Les cycles présents dans les parents
	 */
	private List<List<Integer>> cycles;
	
	/*
	 * La taille d'un cycle hamiltonien
	 */
	private int size;

	/**
	 * Constructeur de la classe Crossing. Crée un nouveau croisement avec deux
	 * parents donnés. Initialise les enfants avec des cycles vides et la liste des
	 * cycles.
	 *
	 * @param parent1 Le premier parent.
	 * @param parent2 Le deuxième parent.
	 */
	public Crossing(Individu parent1, Individu parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child1 = new Individu(new ArrayList<>());
		this.child2 = new Individu(new ArrayList<>());
		this.cycles = new ArrayList<>();
		this.size = parent1.getCycle().size();

		for (int i = 0; i < this.size; i++) {
			this.child1.getCycle().add(null);
			this.child2.getCycle().add(null);
		}
	}

	/**
	 * Getter pour l'enfant 1.
	 * 
	 * @return L'Individu représentant l'enfant 1.
	 */
	public Individu getChild1() {
		return this.child1;
	}

	/**
	 * Getter pour l'enfant 2.
	 * 
	 * @return L'Individu représentant l'enfant 2.
	 */
	public Individu getChild2() {
		return this.child2;
	}

	/**
	 * Getter pour le parent 1.
	 * 
	 * @return L'Individu représentant le parent 1.
	 */
	public Individu getParent1() {
		return this.parent1;
	}

	/**
	 * Getter pour le parent 2.
	 * 
	 * @return L'Individu représentant le parent 2.
	 */
	public Individu getParent2() {
		return this.parent2;
	}

	/**
	 * Getter pour les cycles.
	 * 
	 * @return Une liste de listes d'entiers représentant les cycles présents dans
	 *         les parents.
	 */
	public List<List<Integer>> getCycles() {
		return this.cycles;
	}

	/**
	 * Getter pour la taille d'un cycle hamiltonien.
	 * 
	 * @return La taille des cycles (nombre de villes).
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Méthode qui retourne l'index d'une ville donnée dans un parent.
	 * 
	 * @param current_city La ville dont on veut connaître l'index.
	 * @param parent2      Le parent dans lequel chercher.
	 * @return L'index de la ville dans le cycle du parent, ou -1 si elle n'est pas
	 *         présente.
	 */
	public int getIndex(City current_city, Individu parent2) {
		for (int i = 0; i < this.size; i++) {
			if (current_city.getName().equals(parent2.getCycle().get(i).getName())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Méthode qui construit les cycles présents dans les parents. Parcourt les
	 * villes des parents pour identifier les cycles communs.
	 */
	public void buildCycle() {
		boolean[] isVisited = new boolean[this.size];
		int index;

		for (index = 0; index < this.size; index++) {
			if (isVisited[index]) {
				continue;
			}

			List<Integer> cycle = new ArrayList<>();
			int current_index = index;

			do {
				cycle.add(current_index);
				isVisited[current_index] = true;

				City current_city = this.parent1.getCycle().get(current_index);
				current_index = getIndex(current_city, this.parent2);

			} while (current_index != index);

			this.cycles.add(cycle);
		}
	}

	/**
	 * Méthode principale de la classe. Construit les enfants en utilisant les
	 * cycles définis dans les parents. Le choix des valeurs pour les enfants est
	 * déterminé par un tirage aléatoire entre les deux parents Ce tirage est 0 ou
	 * 1, ce qui modélise un tirage pile ou face Si on tire 0 : On met dans child1
	 * les valeurs de parent1 imposées par le cycle. On met dans child2 les valeurs
	 * de parent2 imposées par le cycle. Si on tire 1: On met dans child1 les
	 * valeurs de parent2 imposées par le cycle. On met dans child2 les valeurs de
	 * parent1 imposées par le cycle.
	 */
	public void buildChilds() {
		buildCycle();

		int randomInt;

		for (List<Integer> cycle : this.cycles) {
			randomInt = (int) (Math.random() * 2);

			for (int index : cycle) {
				if (randomInt == 0) {
					child1.getCycle().set(index, parent1.getCycle().get(index));
					child2.getCycle().set(index, parent2.getCycle().get(index));
				} else {
					child1.getCycle().set(index, parent2.getCycle().get(index));
					child2.getCycle().set(index, parent1.getCycle().get(index));
				}
			}
		}
	}
}