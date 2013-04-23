package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FourmiAbstraite implements Runnable {
	int posi, posj;
	Chemin chemin;
	boolean revenirMaison;

	public abstract void deplacement();

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public int getPosi() {
		return posi;
	}

	public int getPosj() {
		return posj;
	}

	public FourmiAbstraite(int posi, int posj) {
		super();
		this.posi = posi;
		this.posj = posj;
		this.chemin = new Chemin();
		this.chemin.ajouterPos(posi, posj);
		this.revenirMaison = false;
	}

	public void deplacementAleatoire() {
		double tirage = Math.random();	
	
		int[] dernierePos = this.chemin.itineraire.lastElement();
		int oldi=dernierePos[0],oldj=dernierePos[1];
	
		//on vient de la gauche
		if(oldi==posi && oldj<posj)
			if(tirage<0.5)
				//vers la droite
				prendreDroite();
			else if(tirage<2./3)
				//vers le bas
				prendreBas();
			else
				//vers le haut
				prendreHaut();
	
		//on vient de la droite
		else if(oldi==posi && oldj>posj)
			if(tirage<0.5)
				//vers la gauche
				prendreGauche();
			else if(tirage<2./3)
				//vers le bas
				prendreBas();
			else
				//vers le haut
				prendreHaut();
	
		//on vient du haut
		else if(oldi<posi && oldj==posj)
			if(tirage<0.5)
				//vers le bas
				prendreBas();
			else if(tirage<2./3)
				//vers la droite
				prendreDroite();
			else
				//vers la gauche
				prendreGauche();
	
		//on vient du bas
		else if(oldi>posi && oldj==posj)
			if(tirage<0.5)
				//vers le haut
				prendreHaut();
			else if(tirage<2./3)
				//vers la droite
				prendreDroite();
			else
				//vers la gauche
				prendreGauche();
	
		else 
	
			if(tirage<0.25) 
				//vers le bas
				prendreBas();
			else if(tirage<0.5) 
				//vers le haut
				prendreHaut();
			else if(tirage<0.75)
				//vers la droite
				prendreDroite();
			else 
				//vers la gauche
				prendreGauche();
	
		this.chemin.ajouterPos(posi, posj);
	}

	public void revenirMaison() {
	
	}

	public int[] voitNourriture() {
		int DISTANCE=3;
		int i=posi, j=posj;
	
		List<int[]> biglist = new ArrayList<int[]>();
		biglist.addAll(Arrays.asList(
				//DISTANCE 1
				new int[] {i,j-1,1},
				new int[] {i,j+1,1},
				new int[] {i-1,j,1},
				new int[] {i+1,j,1},
				//DISTANCE 2
				new int[] {i,j-2,2},
				new int[] {i,j+2,2},
				new int[] {i-2,j,2},
				new int[] {i+2,j,2},
				new int[] {i-1,j-1,2},
				new int[] {i-1,j+1,2},
				new int[] {i+1,j-1,2},
				new int[] {i+1,j+1,2},
				//DISTANCE 3
				new int[] {i,j-3,3},
				new int[] {i,j+3,3},
				new int[] {i-3,j,3},
				new int[] {i+3,j,3},
				new int[] {i-2,j-1,3},
				new int[] {i-2,j+1,3},
				new int[] {i+2,j-1,3},
				new int[] {i+2,j+1,3},
				new int[] {i+1,j-2,3},
				new int[] {i+1,j+2,3},
				new int[] {i-2,j+1,3},
				new int[] {i+2,j+1,3}
		));
	
		for (int[] pos : biglist) {
			try {
				if(Grille.getInstance().isSourceNourriture(pos[0], pos[1]))
					return pos;
			} catch (IndexOutOfBoundsException e) {	}
		}	
		return null;
	}

	private void prendreGauche() {
		this.posj = Math.abs(this.posj - 1);
	}

	private void prendreDroite() {
		int taille = Grille.N;
		this.posj = -Math.abs(this.posj+1 -(taille-1)) + taille-1;
	}

	private void prendreBas() {
		int taille = Grille.N;
		this.posi = -Math.abs(this.posi+1 -(taille-1)) +(taille-1);
	}

	private void prendreHaut() {
		this.posi = Math.abs(this.posi - 1);
	}
}

abstract class FourmiIntelligente extends FourmiAbstraite {

	Pheromone pheromone;

	double qo = 0.8;
	double Q = 1;

	public FourmiIntelligente(int posi, int posj) {
		super(posi, posj);
		// TODO Auto-generated constructor stub
	}

	public void deposerPheromone(double quantite) {
		Grille.getInstance().deposerPheromone(posi, posj, pheromone, quantite);
	}

}

enum Pheromone {
	Phe_A, Phe_B;
}
