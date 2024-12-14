package fr.traveler.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class FitnessGraph extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Double> fitnessValues;

	public FitnessGraph(List<Double> fitnessValues) {
		this.fitnessValues = fitnessValues;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		int margin = 50;
		int graphWidth = width - 2 * margin;
		int graphHeight = height - 2 * margin;

		g.drawLine(margin, height - margin, width - margin, height - margin); // Axe X
		g.drawLine(margin, height - margin, margin, margin); // Axe Y

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
