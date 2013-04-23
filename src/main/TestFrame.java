package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class TestFrame extends JFrame {

	/* Donn�es */
	final int width, height;
	
	public Colonie colonie;

	/*
	 * Param�tres graphiques.
	 * 
	 * offset: Position de la grille affich�e.
	 * 
	 * taille: Taille d'une case � l'affichage.
	 */
	private final Dimension offset = new Dimension(100, 100);
	private final int taille = 10;

	public TestFrame(int width, int height, Colonie colonie) {
		super("Test affichage");
		this.width = width;
		this.height = height;
		this.colonie = colonie;
	}

	/**
	 * Redessine le contenu de la fen�tre.
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
				// R�cup�ration de la couleur associ�e
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
	 * Fonction qui associe une couleur � un caract�re.
	 * @param c Caract�re � repr�senter.
	 * @return Une couleur bidon.
	 */
	public Color couleur(double d) {
		
		if (d < -1){ //nourriture
			return Color.green;
		}
		
		if (d == -1){ //fourmili�re
			return Color.orange;
		}
		
		return Color.gray; //rien
	}
	
	

	public static void main(String[] args) {
		int width = 50;
		int height = 50;

		Colonie colonie = new Colonie();
		// Creation de la fen�tre.
		final TestFrame frame = new TestFrame(width, height, colonie);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
		
		int TIMER_DELAY = 200;
		new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int k=0; k<frame.colonie.nbfourmis; k++) {
					frame.colonie.listeFourmis[k].deplacement();
				}
				frame.repaint();
			}
		}).start();
		
	}

	/* On s'en fiche */
	private static final long serialVersionUID = -4373623834478094568L;
}
