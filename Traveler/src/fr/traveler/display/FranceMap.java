package fr.traveler.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.traveler.geography.entities.City;

/**
 * Représente une carte de la France affichant un itinéraire passant par les villes spécifiées du cycle hamiltonien.
 * 
 * @author Adrien Riffaut
 * @author Néo Moret
 */
public class FranceMap extends JPanel {

	/**
	 * Variable pour enlever les erreurs de éclipse
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Image de la carte de la France.
     */
	private BufferedImage mapImage;
	
	 /**
     * Liste des villes à afficher sur la carte dans l'ordre de l'itinéraire.
     */
	private List<City> cities;

	
	/**
     * Construit une instance de FranceMap avec une liste de villes spécifiées.
     * Charge également l'image de la carte.
     * 
     * @param cities la liste des villes à afficher
     */
	public FranceMap(List<City> cities) {
		this.cities = cities;
		try {
			mapImage = ImageIO.read(getClass().getResourceAsStream("/france.png"));
		} catch (IOException e) {
			System.err.println("Error: Unable to load image.");
			e.printStackTrace();
		}
	}

	/**
     * Dessine la carte de la France ainsi que les villes et les lignes les reliant.
     * Les villes sont représentées par des cercles bleus, et l'itinéraire est en rouge.
     * 
     * @param g l'objet {@link Graphics} utilisé pour dessiner les composants
     */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (mapImage != null) {
			g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), null);
		}

		if (cities != null && !cities.isEmpty()) {
			int width = 645;
			int height = 634;

			List<int[]> cityPixels = new ArrayList<>();
			for (City city : cities) {
				cityPixels.add(geoToPixel(city.getLatitude(), city.getLongitude(), width, height));
			}

			g.setColor(Color.RED);
			for (int i = 0; i < cityPixels.size() - 1; i++) {
				int[] p1 = cityPixels.get(i);
				int[] p2 = cityPixels.get(i + 1);
				g.drawLine(p1[0], p1[1], p2[0], p2[1]);
			}

			g.setColor(Color.BLUE);
			for (int[] position : cityPixels) {
				int x = position[0];
				int y = position[1];
				g.fillOval(x - 5, y - 5, 10, 10);
			}
		}
	}

	/**
     * Convertit les coordonnées géographiques (latitude et longitude) en coordonnées de pixels sur l'image.
     * @see <a href="https://fr.m.wikipedia.org/wiki/Fichier:Carte_France_geo_dep2.png">Wikipedia</a>
     * 
     * @param lat         la latitude
     * @param lon         la longitude
     * @param imageWidth  la largeur de l'image
     * @param imageHeight la hauteur de l'image
     * @return un tableau contenant les coordonnées X et Y en pixels
     */
	private int[] geoToPixel(double lat, double lon, int imageWidth, int imageHeight) {
		int x = (int) ((0.067304 * lon + 0.348547) * imageWidth);
		int y = (int) ((-0.097507 * lat + 5.009050) * imageWidth);
		return new int[] { x, y };
	}
}