package main;

import core.*;

public class Colonie {
	public FourmiAbstraite listeFourmis[];
	Grille terrain;
	
	public Colonie() {
		this.terrain = new Grille();
		Grille.placerFourmillere();
		Grille.placerSourcesNourriture(3);
		listeFourmis = new FourmiAbstraite[Grille.nbfourmis];
		
		for (int i=0; i<Grille.nbfourmis; i++){
			int x = (int) (Math.random() * 50);
			int y = (int) (Math.random() * 50);
			listeFourmis[i] = new FourmiSimple(x,y);
		}
	} 

}
