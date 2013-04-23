package core;

import core.*;

public class Colonie {
	public FourmiAbstraite listeFourmis[];

	public int nbfourmis = 10;
	Grille terrain;
	
	public Colonie() {
		this.terrain = Grille.getInstance();
		int[] couple = Grille.placerFourmillere();
		Grille.placerSourcesNourriture(3);
		listeFourmis = new FourmiAbstraite[nbfourmis];
		
		for (int i=0; i<nbfourmis; i++){
			/*
			int x = (int) (Math.random() * 50);
			int y = (int) (Math.random() * 50);
			*/
			listeFourmis[i] = new FourmiGentille(couple[0],couple[1]);
		}
//		System.out.println(listeFourmis[0].voitNourriture());
	} 

}
