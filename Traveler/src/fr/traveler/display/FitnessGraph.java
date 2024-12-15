package fr.traveler.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

/**
 * Représente un graphique affichant l'évolution des valeurs de fitness. Ce
 * composant graphique dessine un graphique 2D à partir d'une liste de valeurs
 * de fitness.
 * 
 * @author Adrien Riffaut
 */
public class FitnessGraph extends JPanel {

	/**
	 * Variable pour enlever les erreurs de éclipse
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Liste des valeurs de fitness à afficher sur le graphique.
	 */
	private List<Double> fitnessValues;

	/**
	 * Construit un graphique de fitness avec les valeurs spécifiées.
	 * 
	 * @param fitnessValues une liste de valeurs de fitness
	 */
	public FitnessGraph(List<Double> fitnessValues) {
		this.fitnessValues = fitnessValues;
	}

	/**
	 * Dessine le graphique des valeurs de fitness. Cette méthode dessine les axes X
	 * et Y, puis trace les valeurs de fitness sous forme d'une courbe.
	 * 
	 * @param g l'objet {@link Graphics} utilisé pour dessiner les composants
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		int margin = 60;
		int graphWidth = width - 2 * margin;
		int graphHeight = height - 2 * margin;

		g.drawLine(margin, height - margin, width - margin, height - margin);
		g.drawLine(margin, height - margin, margin, margin);

		g.setColor(Color.BLACK);
		g.drawString("Iterations", width / 2 - 30, height - 30);
		g.drawString("Fitness", margin - 40, 40);

		Double maxFitness = fitnessValues.stream().max(Double::compare).orElse((double) 1);
		int points = fitnessValues.size();
		double xScale = (double) graphWidth / (points - 1);
		double yScale = (double) graphHeight / maxFitness;

		g.setColor(Color.BLUE);
		for (int i = 0; i < points - 1; i++) {
			int x1 = margin + (int) (i * xScale);
			int y1 = height - margin - (int) (fitnessValues.get(i) * yScale);
			int x2 = margin + (int) ((i + 1) * xScale);
			int y2 = height - margin - (int) (fitnessValues.get(i + 1) * yScale);

			g.drawLine(x1, y1, x2, y2);
		}
	}

}
