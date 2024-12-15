package fr.traveler.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.traveler.geography.entities.City;

/**
 * Classe de test pour la classe {@link City}. Elle vérifie le bon
 * fonctionnement de la méthode {@code distanceTo} pour calculer la distance
 * entre deux villes.
 * 
 * @author Néo MORET
 */

public class CityTest {

	/**
	 * Ville de test
	 */
	private City city1;
	
	/**
	 * Ville de test
	 */
	private City poleNord;
	
	/**
	 * Ville de test
	 */
	private City equateur;
	
	/**
	 * Ville de test
	 */
	private City paris;
	
	/**
	 * Ville de test
	 */
	private City londres;
	
	/**
	 * Marge d'erreur
	 */
	private double epsilon;

	/**
	 * Méthode exécutée avant chaque test pour initialiser les données de test. Elle
	 * crée des instances de la classe {@link City} représentant différentes villes.
	 */

	@BeforeEach
	public void buildSetUp() {
		this.city1 = new City("myCity1", "0", 0, 0.0, "myDepartement", 0, 90);
		this.poleNord = new City("poleNord", "0", 0, 0.0, "myDepartement", 90, 0);
		this.equateur = new City("Equateur", "0", 0, 0.0, "myDepartement", 0, 0);
		this.paris = new City("Paris", "0", 0, 0.0, "myDepartement", 48.8566, 2.3522);
		this.londres = new City("Londres", "0", 0, 0.0, "myDepartement", 51.5074, -0.1278);
		this.epsilon = 1;

	}

	/**
	 * Teste la méthode {@code distanceTo} pour vérifier le calcul de la distance
	 * entre différentes villes avec une marge d'erreur définie. On autorise 1 km de
	 * marge d'erreur entre la distance proposée par notre formule et la distance
	 * réelle
	 * 
	 */

	@Test
	public void testDistanceTo() {

		assertEquals(10007, this.equateur.distanceTo(this.city1), this.epsilon, "Distance sur l'équateur incorrecte");
		assertEquals(10007, this.poleNord.distanceTo(this.equateur), this.epsilon,
				"La distance entre l'équateur et le pôle Nord est incorrecte");
		assertEquals(0, this.paris.distanceTo(this.paris), this.epsilon,
				"La distance entre deux villes identiques est incorrecte");
		assertEquals(343, this.paris.distanceTo(this.londres), this.epsilon,
				"La distance entre Paris et Londres est incorrecte");
		assertEquals(343, this.londres.distanceTo(this.paris), this.epsilon,
				"La distance entre Londres et Paris est incorrecte");

	}

}