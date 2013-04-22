package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class TestFrame extends JFrame {

	/* Données */
	final int width, height;
	
	Colonie colonie;

	/*
	 * Paramètres graphiques.
	 * 
	 * offset: Position de la grille affichée.
	 * 
	 * taille: Taille d'une case à l'affichage.
	 */
	private final Dimension offset = new Dimension(100, 100);
	private final int taille = 10;

	public TestFrame(int width, int height) {
		super("Test affichage");
		this.width = width;
		this.height = height;
		this.colonie = new Colonie();
	}

	/**
	 * Redessine le contenu de la fenètre.
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;

		double[][] tableau = colonie.terrain.grille;
		// On va dessiner case par case.
		int i, j, x, y;
		for (i = 0, x = offset.width; i < tableau.length; i++, x += taille) {
			for (j = 0, y = offset.height; j < tableau[i].length; j++, y += taille) {
				// Récupération de la couleur associée
				g.setColor(couleur(tableau[i][j]));
				//
				g.fillRect(x, y, taille, taille);
			}
		}
		
		g.setColor(Color.blue);
		for (int k=0; k<colonie.listeFourmis.length; k++) {
			g.fillRect(offset.width+colonie.listeFourmis[k].getPosi()*taille, offset.height+colonie.listeFourmis[k].getPosj()*taille, taille, taille);
		}
	}

	/**
	 * Fonction qui associe une couleur à  un caractère.
	 * @param c Caractère à représenter.
	 * @return Une couleur bidon.
	 */
	public Color couleur(double d) {
		
		if (d < -1){ //nourriture
			return Color.green;
		}
		
		if (d == -1){ //fourmilière
			return Color.orange;
		}
		
		return Color.gray; //rien
	}
	
	

	public static void main(String[] args) {
		int width = 50;
		int height = 50;

		// Creation de la fenètre.
		JFrame frame = new TestFrame(width, height);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}

	/* On s'en fiche */
	private static final long serialVersionUID = -4373623834478094568L;
}
