package genetic;

/*
 * Cette classe vise à représenter une ville.
 * Une ville possède un nom, une latitude et une longitude.
 * On suppose que la latitude et longitude sont en degrés,
 * mais elles sont converties en radians dans le constructeur.
 */

public class City {

    private String city_name;
    private double latitude;  // En degrés
    private double longitude; // En degrés
    final double EARTH_RADIUS = 6371.0;
    
    // ---------------------------------------------------------------------------------------------
    // Constructor : 

 
    public City( String city_name, double latitude, double longitude ) {
        this.city_name = city_name; // nom de la ville
        this.latitude = Math.toRadians(longitude); // latitude de la ville transformée en radians
        this.longitude = Math.toRadians(longitude); //longitude de la ville transformée en radians
    }

    // ----------------------------------------------------------------------------------------------
    
    // Getters : 
    
    public String getName() {
        return this.city_name;
    }

    
    public double getLatitude() {
        return this.latitude; // En radians
    }

    public double getLongitude() {
        return this.longitude; // En radians
    }
    
    // ----------------------------------------------------------------------------------------------

    // Distance enter this.city and city2 by the Haversine formula


    public double Haversine(City city2) {
        

        // difference of the latitude and longitude of this.city and city2
    	
        double deltaLatitude = (city2.latitude) - (this.latitude);
        double deltaLongitude = (city2.longitude) - (this.longitude);

        // Formula : 
        double a = Math.pow(Math.sin(deltaLatitude / 2), 2)
                 + Math.cos(this.latitude) * Math.cos(city2.latitude)
                 * Math.pow(Math.sin(deltaLongitude / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); 
        return EARTH_RADIUS * c;
    }

    @Override
    public String toString() {
        return   (  this.getName() + " ");
    }
    
}
