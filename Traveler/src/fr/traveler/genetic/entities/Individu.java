/**
 * Classe représentant un individu dans l'algorithme génétique.
 * Un individu correspond à une solution potentielle pour le problème du voyageur de commerce.
 * 
 * Chaque individu est caractérisé par :
 * <ul>
 *   <li>Un cycle représentant l'ordre des villes à visiter.</li>
 *   <li>La distance totale du cycle.</li>
 * </ul>
 * 
 * Cette classe fournit des méthodes pour calculer la distance, mélanger le cycle,
 * afficher les villes, et appliquer des mutations pour l'évolution.
 * 
 * @author Néo Moret
 */
package fr.traveler.genetic.entities;

import java.util.Collections;
import java.util.List;

import fr.traveler.config.Config;
import fr.traveler.geography.entities.City;

public class Individu {

    /**
     * Liste des villes représentant le cycle de l'individu.
     */
    private List<City> cycle;

    /**
     * Distance totale du cycle.
     */
    private double distance;

    /**
     * Constructeur de la classe {@code Individu}.
     * Initialise le cycle et calcule la distance si le cycle n'est pas vide.
     * 
     * @param cycle Liste des villes représentant l'ordre des visites.
     */
    public Individu(List<City> cycle) {
        this.cycle = cycle;
        if (!cycle.isEmpty())
            computeDistance();
    }

    /**
     * Getter pour la distance totale du cycle.
     * 
     * @return La distance totale du cycle.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Calcule la fitness de l'individu.
     * La fitness est définie comme l'inverse de la distance (échelle 1 / 10 000).
     * 
     * @return Le fitness de l'individu.
     */
    public double getFitness() {
        return 10000 / distance;
    }

    /**
     * Getter pour le cycle de l'individu.
     * 
     * @return La liste des villes représentant le cycle.
     */
    public List<City> getCycle() {
        return cycle;
    }

    /**
     * Mélange aléatoirement l'ordre des villes dans le cycle.
     * Recalcule la distance après le mélange.
     */
    public void shuffleCycle() {
        Collections.shuffle(cycle);
        computeDistance();
    }

    /**
     * Calcule la distance totale du cycle.
     * Parcourt toutes les paires consécutives de villes dans le cycle et
     * inclut la distance entre la dernière et la première ville.
     */
    public void computeDistance() {
        double distance = 0;
        for (int i = 0; i < this.cycle.size() - 1; i++) {
            distance += this.cycle.get(i).distanceTo(cycle.get(i + 1));
        }
        distance += this.cycle.get(this.cycle.size() - 1).distanceTo(cycle.get(0));
        this.distance = distance;
    }

    /**
     * Affiche les noms des villes dans le cycle dans l'ordre.
     * Si le cycle est vide, affiche un message indiquant qu'il est vide.
     */
    public void displayCities() {
        if (this.cycle.isEmpty()) {
            System.out.println("This cycle is empty.");
            return;
        }
        String cities = "";
        for (City city : this.cycle) {
            cities += city.getName() + "->";
        }
        cities += this.cycle.get(0).getName();
        System.out.println(cities);
    }

    /**
     * Applique une mutation sur le cycle.
     * <p>
     * Une mutation consiste à échanger deux villes aléatoires dans le cycle avec une
     * probabilité définie par {@code Config.MUTATION}.
     * Recalcule la distance après la mutation.
     */
    public void mutate() {
        double randomDouble = Math.random();

        int randomIndex_1;
        int randomIndex_2;

        if (randomDouble <= Config.MUTATION) {

            do {
                randomIndex_1 = (int) (Math.random() * this.cycle.size());
                randomIndex_2 = (int) (Math.random() * this.cycle.size());
            } while (randomIndex_1 == randomIndex_2);

            City temp_city = this.cycle.get(randomIndex_1);
            this.cycle.set(randomIndex_1, this.cycle.get(randomIndex_2));
            this.cycle.set(randomIndex_2, temp_city);
        }
        computeDistance();
    }
}
