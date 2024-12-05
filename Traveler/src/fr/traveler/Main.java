package fr.traveler;

import java.util.ArrayList;

import fr.traveler.genetic.City;
import fr.traveler.genetic.GeneticSolution;

public class Main {

	public static void main(String[] args) {

		ArrayList<City> cities = new ArrayList<>();
		

		
		cities.add(new City("Paris", 48.8566, 2.3522));
		cities.add(new City("Marseille", 43.2965, 5.3698));
		//cities.add(new City("Lyon", 45.7640, 4.8357));
		cities.add(new City("Toulouse", 43.6047, 1.4442));
		cities.add(new City("Nice", 43.7102, 7.2620));
		cities.add(new City("Nantes", 47.2184, -1.5536));
		//cities.add(new City("Strasbourg", 48.5734, 7.7521));
		cities.add(new City("Montpellier", 43.6108, 3.8767));
		//cities.add(new City("Bordeaux", 44.8378, -0.5792));
		cities.add(new City("Lille", 50.6292, 3.0573));
		//cities.add(new City("Rennes", 48.1173, -1.6778));
		//cities.add(new City("Reims", 49.2583, 4.0317));
		cities.add(new City("Le Havre", 49.4944, 0.1079));
		//cities.add(new City("Saint-Étienne", 45.4397, 4.3872));
		cities.add(new City("Toulon", 43.1242, 5.9280));
		//cities.add(new City("Grenoble", 45.1885, 5.7245));
		/*cities.add(new City("Dijon", 47.3220, 5.0415));
		cities.add(new City("Angers", 47.4784, -0.5632));
		cities.add(new City("Nîmes", 43.8367, 4.3601));
		cities.add(new City("Villeurbanne", 45.7719, 4.8902));
		cities.add(new City("Clermont-Ferrand", 45.7772, 3.0870));
		cities.add(new City("Le Mans", 48.0061, 0.1996));
		cities.add(new City("Aix-en-Provence", 43.5297, 5.4474));
		cities.add(new City("Brest", 48.3904, -4.4861));
		cities.add(new City("Tours", 47.3941, 0.6848));
		cities.add(new City("Amiens", 49.8941, 2.2957));
		cities.add(new City("Limoges", 45.8336, 1.2611));
		cities.add(new City("Annecy", 45.8992, 6.1294));
		cities.add(new City("Perpignan", 42.6886, 2.8948));
		cities.add(new City("Metz", 49.1193, 6.1757));
		cities.add(new City("Boulogne-Billancourt", 48.8397, 2.2399));
		cities.add(new City("Besançon", 47.2378, 6.0241));
		cities.add(new City("Orléans", 47.9025, 1.9090));
		cities.add(new City("Rouen", 49.4432, 1.0993));
		cities.add(new City("Mulhouse", 47.7508, 7.3359));
		cities.add(new City("Caen", 49.1829, -0.3707));
		cities.add(new City("Saint-Denis", 48.9362, 2.3574));
		cities.add(new City("Nancy", 48.6921, 6.1844));
		cities.add(new City("Argenteuil", 48.9472, 2.2467));
		cities.add(new City("Montreuil", 48.8638, 2.4485));
		cities.add(new City("Roubaix", 50.6927, 3.1746));
		cities.add(new City("Tourcoing", 50.7230, 3.1612));
		cities.add(new City("Nanterre", 48.8924, 2.1972));
		cities.add(new City("Avignon", 43.9493, 4.8055));
		cities.add(new City("Créteil", 48.7904, 2.4556));
		cities.add(new City("Poitiers", 46.5802, 0.3404));
		cities.add(new City("Versailles", 48.8049, 2.1204));
		cities.add(new City("Pau", 43.2951, -0.3708));
		cities.add(new City("Courbevoie", 48.8953, 2.2567));
		cities.add(new City("Vitry-sur-Seine", 48.7878, 2.4039));
		cities.add(new City("Colombes", 48.9226, 2.2540));
		cities.add(new City("Aulnay-sous-Bois", 48.9384, 2.4940));
		cities.add(new City("Asnières-sur-Seine", 48.9144, 2.2871));
		cities.add(new City("Rueil-Malmaison", 48.8834, 2.1817));
		cities.add(new City("Antibes", 43.5804, 7.1251));
		cities.add(new City("Saint-Maur-des-Fossés", 48.7921, 2.4930));
		cities.add(new City("Calais", 50.9513, 1.8587));
		cities.add(new City("Champigny-sur-Marne", 48.8155, 2.4936));
		cities.add(new City("Aubervilliers", 48.9145, 2.3834));
		cities.add(new City("Béziers", 43.3442, 3.2186));
		cities.add(new City("Bourges", 47.0810, 2.3988));
		cities.add(new City("Cannes", 43.5528, 7.0174));
		cities.add(new City("Colmar", 48.0790, 7.3585));
		cities.add(new City("Drancy", 48.9252, 2.4454));
		cities.add(new City("Mérignac", 44.8354, -0.6330));
		cities.add(new City("Ajaccio", 41.9192, 8.7386));
		cities.add(new City("Issy-les-Moulineaux", 48.8210, 2.2674));
		cities.add(new City("Levallois-Perret", 48.8939, 2.2886));
		cities.add(new City("La Rochelle", 46.1603, -1.1511));
		cities.add(new City("Quimper", 47.9961, -4.0963));
		cities.add(new City("Noisy-le-Grand", 48.8494, 2.5623));
		cities.add(new City("Villeneuve-d'Ascq", 50.6233, 3.1481));
		cities.add(new City("Neuilly-sur-Seine", 48.8848, 2.2686));
		cities.add(new City("Valence", 44.9334, 4.8924));
		cities.add(new City("Antony", 48.7598, 2.3020));
		cities.add(new City("Troyes", 48.2973, 4.0744));
		cities.add(new City("Clichy", 48.9006, 2.3088));
		cities.add(new City("Chambéry", 45.5646, 5.9178));
		cities.add(new City("Niort", 46.3237, -0.4588));
		cities.add(new City("Sarcelles", 48.9975, 2.3834));
		cities.add(new City("Les Abymes", 16.2700, -61.5040));
		cities.add(new City("Cergy", 49.0364, 2.0778));
		cities.add(new City("Pessac", 44.8105, -0.6413));
		cities.add(new City("Ivry-sur-Seine", 48.8130, 2.3888));
		cities.add(new City("Lorient", 47.7489, -3.3666));
		cities.add(new City("Cayenne", 4.9224, -52.3135));
		cities.add(new City("Maisons-Alfort", 48.8060, 2.4391));
		cities.add(new City("Saint-Quentin", 49.8489, 3.2876));
		cities.add(new City("Beauvais", 49.4298, 2.0886));
		cities.add(new City("Hyères", 43.1200, 6.1275));
		cities.add(new City("Meaux", 48.9604, 2.8785));
		cities.add(new City("Épinay-sur-Seine", 48.9556, 2.3156));
		cities.add(new City("Fréjus", 43.4323, 6.7350));
		cities.add(new City("Pantin", 48.8941, 2.4107));
		cities.add(new City("Villejuif", 48.7939, 2.3621));
		cities.add(new City("Narbonne", 43.1843, 3.0045));
		cities.add(new City("Bondy", 48.9044, 2.4752));
		cities.add(new City("Cholet", 47.0591, -0.8808));
		cities.add(new City("Vénissieux", 45.6979, 4.8714));
		cities.add(new City("Bobigny", 48.9065, 2.4397));
		cities.add(new City("Saint-Pierre", -21.3393, 55.4781));
		cities.add(new City("Aubagne", 43.2926, 5.5700));
		cities.add(new City("Corbeil-Essonnes", 48.6135, 2.4686));
		cities.add(new City("Saint-Ouen", 48.9116, 2.3333));
		cities.add(new City("Suresnes", 48.8718, 2.2293));*/
		
		GeneticSolution mysol = new GeneticSolution();
		mysol.GeneticAlgorithm(cities, 10);
		
		
		//GeneticSolution mysolution = new GeneticSolution();
		//mysolution.GeneticAlgorithm(cities, 100);
		
		System.out.println("Solution : ");
		System.out.println(mysol.getSolution());
		System.out.println("Distance : ");
		System.out.println(mysol.getDistance());
		System.out.println("Fitness : ");
		System.out.println(mysol.getFitness());

		
		
	
		
		

		
			
		}
		
	
		
	

		

	}

