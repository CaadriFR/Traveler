package fr.traveler.geography.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.traveler.geography.entities.City;

public class FranceMap extends JPanel {
	private BufferedImage mapImage;

	private List<City> cities;

	public FranceMap(List<City> cities) {
		this.cities = cities;
		try {
		    mapImage = ImageIO.read(getClass().getResourceAsStream("/france.png"));
		} catch (IOException e) {
		    System.err.println("Erreur : Impossible de charger l'image.");
		    e.printStackTrace();
		}
	}

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

	private int[] geoToPixel(double lat, double lon, int imageWidth, int imageHeight) {
		// Calcul des positions x et y selon des formules trouvées sur wikipedia
		// https://commons.wikimedia.org/wiki/File:Carte_France_geo_dep2.png
		int x = (int) ((0.067304 * lon + 0.348547) * imageWidth);
		int y = (int) ((-0.097507 * lat + 5.009050) * imageWidth);
		return new int[] { x, y };
	}

	public static void showMap(List<City> cities) {
		cities.add(cities.getFirst());
		JFrame frame = new JFrame("Carte de l'itinéraire");
		FranceMap mapPanel = new FranceMap(cities);

		frame.add(mapPanel);
		// j'ai ajusté à la main pour que ça rende mieux
		frame.setSize(645, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}