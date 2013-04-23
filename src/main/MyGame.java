package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import core.Grille;

public class MyGame {
	private static final int FRAME_DELAY = 200; // 20ms. implies 50fps (1000/20) = 50
	private static Colonie colonie = new Colonie();

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fourmis-Agents");
		Canvas gui = new Canvas();
		frame.getContentPane().add(gui);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // start AWT painting.
		Thread gameThread = new Thread(new GameLoop(gui));
		gameThread.setPriority(Thread.MIN_PRIORITY);
		gameThread.start(); // start Game processing.
	}

	private static class GameLoop implements Runnable {

		boolean isRunning;
		Canvas gui;
		long cycleTime;
		private final Dimension offset = new Dimension(100, 100);
		private final int taille = colonie.nbfourmis;

		public GameLoop(Canvas canvas) {
			gui = canvas;
			isRunning = true;
		}

		public void run() {
			cycleTime = System.currentTimeMillis();
			gui.createBufferStrategy(2);
			BufferStrategy strategy = gui.getBufferStrategy();

			// Game Loop
			while (isRunning) {

				updateGameState();

				updateGUI(strategy);

				synchFramerate();
			}
		}

		private void updateGameState() {
			for (int k=0; k<colonie.nbfourmis; k++) {
				colonie.listeFourmis[k].deplacement();
			}
		}

		private void updateGUI(BufferStrategy strategy) {
			Graphics g = strategy.getDrawGraphics();

			///////////////
			double[][] tableau = Grille.grille;
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
			
			////////
			g.dispose();
			strategy.show();
		}

		private void synchFramerate() {
			cycleTime = cycleTime + FRAME_DELAY;
			long difference = cycleTime - System.currentTimeMillis();
			try {
				Thread.sleep(Math.max(0, difference));
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		/**
		 * Fonction qui associe une couleur à un caractère.
		 * @param c Caractère à représenter.
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
	}
}