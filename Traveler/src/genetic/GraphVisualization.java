package genetic;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GraphVisualization extends JPanel {
    private int[][] edges; // Liste des arêtes (ex: {{0, 1}, {1, 2}})
    private Point[] vertices; // Positions des sommets (ex: { (x1, y1), (x2, y2) })
    private List<int[]> redPath; // Liste des arêtes du chemin en rouge

    public GraphVisualization(int[][] edges, Point[] vertices, List<int[]> redPath) {
        this.edges = edges;
        this.vertices = vertices;
        this.redPath = redPath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner toutes les arêtes
        g2d.setColor(Color.BLACK);
        for (int[] edge : edges) {
            Point p1 = vertices[edge[0]];
            Point p2 = vertices[edge[1]];
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Dessiner le chemin particulier en rouge
        g2d.setColor(Color.RED);
        for (int[] edge : redPath) {
            Point p1 = vertices[edge[0]];
            Point p2 = vertices[edge[1]];
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Dessiner les sommets
        g2d.setColor(Color.BLUE);
        for (int i = 0; i < vertices.length; i++) {
            Point p = vertices[i];
            g2d.fillOval(p.x - 5, p.y - 5, 10, 10);
            g2d.drawString("S" + i, p.x + 5, p.y - 5);
        }
    }

    public static void main(String[] args) {
        // Définir les sommets
        Point[] vertices = {
                new Point(100, 100),
                new Point(200, 100),
                new Point(150, 200),
                new Point(300, 200)
        };

        // Définir les arêtes
        int[][] edges = {
                {0, 1}, {1, 2}, {2, 0}, {1, 3}
        };

        // Définir le chemin particulier en rouge
        List<int[]> redPath = new ArrayList<>();
        redPath.add(new int[]{0, 1});
        redPath.add(new int[]{1, 3});

        // Créer et afficher le graphe
        JFrame frame = new JFrame("Graph Visualization");
        GraphVisualization graphPanel = new GraphVisualization(edges, vertices, redPath);
        frame.add(graphPanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}