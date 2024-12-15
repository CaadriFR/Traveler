package fr.traveler.testsjunit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import fr.traveler.geography.entities.City;

public class CityTest {
	
	private City city1; 
    private City poleNord; 
    private City equateur; 
    private City paris; 
    private City londres; 
    private double epsilon;
	
	@BeforeEach
	public void buildSetUp() {
		this.city1 = new City( "myCity1", "0", 0, 0.0, "myDepartement", 0,90);
		this.poleNord = new City( "poleNord", "0", 0, 0.0, "myDepartement", 90,0);
		this.equateur = new City( "Equateur", "0", 0, 0.0, "myDepartement", 0,0);
		this.paris = new City( "Paris", "0", 0, 0.0, "myDepartement", 48.8566,2.3522);
		this.londres = new City( "Londres", "0", 0, 0.0, "myDepartement", 51.5074,-0.1278);
		this.epsilon = 1;
		
	}
	
	@Test
	public void testDistanceTo() {
	 

		//On autorise 1 km de marge d'erreur entre la distance proposée par notre formule et la distance réelle
	    //  Distance sur l'équateur
		
		assertEquals(10007, this.equateur.distanceTo(this.city1),this.epsilon, "Distance sur l'équateur incorrecte"); 
		assertEquals(10007, this.poleNord.distanceTo(this.equateur),this.epsilon, "La distance entre l'équateur et le pôle Nord est incorrecte"); 
		assertEquals(0, this.paris.distanceTo(this.paris),this.epsilon, "La distance entre deux villes identiques est incorrecte"); 
		assertEquals(343, this.paris.distanceTo(this.londres),this.epsilon, "La distance entre Paris et Londres est incorrecte"); 
		assertEquals(343, this.londres.distanceTo(this.paris),this.epsilon, "La distance entre Londres et Paris est incorrecte"); 
	

	}


}
