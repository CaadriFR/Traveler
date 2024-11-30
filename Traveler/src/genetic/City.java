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

 
    public City(String city_name, double latitude, double longitude) {
        this.city_name = city_name;
        this.latitude = Math.toRadians(longitude); 
        this.longitude = Math.toRadians(longitude); 
    }

    // Getters
    public String getName() {
        return this.city_name;
    }

    public double getLatitude() {
        return this.latitude; // En radians
    }

    public double getLongitude() {
        return this.longitude; // En radians
    }

 
    
    public double Haversine(City city2) {
        final double EARTH_RADIUS = 6371.0; // Rayon moyen de la Terre en kilomètres

        // Calcul des différences de latitude et de longitude
        double deltaLat = (city2.latitude) - (this.latitude);
        double deltaLon = (city2.longitude) - (this.longitude);

        // Application de la formule de Haversine
        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                 + Math.cos(this.latitude) * Math.cos(city2.latitude)
                 * Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance finale
        return EARTH_RADIUS * c;
    }

    @Override
    public String toString() {
        return city_name + " (latitude: " + Math.toDegrees(latitude) + "°, longitude: " + Math.toDegrees(longitude) + "°)";
    }
    
    
    public static void main(String[] args) {
    	
    	City paris = new City("Paris", 48.866669, 2.333);
        City marseille = new City("Marseille", 43.299999, 5.4);
        City lyon = new City("Lyon", 45.75, 4.85);
        City toulouse = new City("Toulouse", 43.599998, 1.43333);
        City nice = new City("Nice", 43.700001, 7.25);
        
        
        
        
        System.out.println( marseille.Haversine(toulouse));
        System.out.println();
        System.out.println( toulouse.Haversine(lyon));
        System.out.println();
        System.out.println( lyon.Haversine(paris));
        System.out.println();
        System.out.println( paris.Haversine(nice));
        System.out.println();
        System.out.println( nice.Haversine(marseille));


        
    }
}
