package fr.traveler.geography.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
        int x = (int) ((0.067304 * lon + 0.348547) * imageWidth);
        int y = (int) ((-0.097507 * lat + 5.009050) * imageWidth);
        return new int[]{x, y};
    }

    public static void showMapWithGraph(List<City> cities, List<Double> fitnessValues) {
    	cities.add(cities.getFirst());
        JFrame frame = new JFrame("Carte et Graphique");
        frame.setLayout(new GridLayout(1, 2)); // Disposer la carte et le graphique côte à côte

        // Panneau pour la carte
        FranceMap mapPanel = new FranceMap(cities);

        // Panneau pour le graphique
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                int margin = 50;
                int graphWidth = width - 2 * margin;
                int graphHeight = height - 2 * margin;

                // Dessiner les axes
                g.drawLine(margin, height - margin, width - margin, height - margin); // Axe X
                g.drawLine(margin, height - margin, margin, margin);                 // Axe Y

                // Échelle
                Double maxFitness = fitnessValues.stream().max(Double::compare).orElse((double) 1);
                int points = fitnessValues.size();
                double xScale = (double) graphWidth / (points - 1);
                double yScale = (double) graphHeight / maxFitness;

                // Points et lignes
                g.setColor(Color.BLUE);
                for (int i = 0; i < points - 1; i++) {
                    int x1 = margin + (int) (i * xScale);
                    int y1 = height - margin - (int) (fitnessValues.get(i) * yScale);
                    int x2 = margin + (int) ((i + 1) * xScale);
                    int y2 = height - margin - (int) (fitnessValues.get(i + 1) * yScale);

                    g.drawLine(x1, y1, x2, y2);
                }
            }
        };

        frame.add(mapPanel);
        frame.add(graphPanel);

        frame.setSize(1300, 667); // Ajuster la largeur pour inclure les deux panneaux
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}