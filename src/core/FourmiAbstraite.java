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
		double posidem = 0.7;

		if (this.revenirMaison)
			this.revenirMaison();

		else {
			double tirage = Math.random();

			int[] dernierePos;
			if (chemin.itineraire.size() < 2)
				dernierePos = this.chemin.itineraire.lastElement();
			else
				dernierePos = this.chemin.itineraire.elementAt(chemin.itineraire.size() - 2);

			int oldi = dernierePos[0], oldj = dernierePos[1];

			// on vient de la gauche
			if (oldi == posi && oldj < posj)
				if (tirage < posidem)
					// vers la droite
					prendreDroite();
				else if (tirage < posidem+(1 - posidem) / 2)
					// vers le bas
					prendreBas();
				else
					// vers le haut
					prendreHaut();

			// on vient de la droite
			else if (oldi == posi && oldj > posj)
				if (tirage < posidem)
					// vers la gauche
					prendreGauche();
				else if (tirage < posidem+(1 - posidem) / 2)
					// vers le bas
					prendreBas();
				else
					// vers le haut
					prendreHaut();

			// on vient du haut
			else if (oldi < posi && oldj == posj)
				if (tirage < posidem)
					// vers le bas
					prendreBas();
				else if (tirage < posidem+(1 - posidem) / 2)
					// vers la droite
					prendreDroite();
				else
					// vers la gauche
					prendreGauche();

			// on vient du bas
			else if (oldi > posi && oldj == posj)
				if (tirage < posidem)
					// vers le haut
					prendreHaut();
				else if (tirage < posidem+(1 - posidem) / 2)
					// vers la droite
					prendreDroite();
				else
					// vers la gauche
					prendreGauche();

			else

			if (tirage < 0.25)
				// vers le bas
				prendreBas();
			else if (tirage < 0.5)
				// vers le haut
				prendreHaut();
			else if (tirage < 0.75)
				// vers la droite
				prendreDroite();
			else
				// vers la gauche
				prendreGauche();

			this.chemin.ajouterPos(posi, posj);
		}
	}

	public void revenirMaison() {
		if (Grille.getInstance().isFourmillere(posi, posj)) {
			this.revenirMaison = false;
			this.chemin.itineraire.clear();
			this.chemin.longueur=1;
			this.chemin.ajouterPos(posi, posj);
		} else {
			this.posi = this.chemin.itineraire.get(this.chemin.itineraire.size() - 2)[0];
			this.posj = this.chemin.itineraire.get(this.chemin.itineraire.size() - 2)[1];
			this.chemin.itineraire.removeElementAt(this.chemin.itineraire.size() - 1);
		}

	}

	public int[] voitNourriture() {
		int DISTANCE = 3;
		int i = posi, j = posj;

		List<int[]> biglist = new ArrayList<int[]>();
		biglist.addAll(Arrays.asList(
				// DISTANCE 1
				new int[] { i, j - 1, 1 }, new int[] { i, j + 1, 1 },
				new int[] { i - 1, j, 1 },
				new int[] { i + 1, j, 1 },
				// DISTANCE 2
				new int[] { i, j - 2, 2 }, new int[] { i, j + 2, 2 }, new int[] { i - 2, j, 2 }, new int[] {
						i + 2, j, 2 }, new int[] { i - 1, j - 1, 2 }, new int[] { i - 1, j + 1, 2 },
				new int[] { i + 1, j - 1, 2 },
				new int[] { i + 1, j + 1, 2 },
				// DISTANCE 3
				new int[] { i, j - 3, 3 }, new int[] { i, j + 3, 3 }, new int[] { i - 3, j, 3 }, new int[] {
						i + 3, j, 3 }, new int[] { i - 2, j - 1, 3 }, new int[] { i - 2, j + 1, 3 },
				new int[] { i + 2, j - 1, 3 }, new int[] { i + 2, j + 1, 3 }, new int[] { i + 1, j - 2, 3 },
				new int[] { i + 1, j + 2, 3 }, new int[] { i - 2, j + 1, 3 }, new int[] { i + 2, j + 1, 3 }));

		for (int[] pos : biglist) {
			try {
				if (Grille.getInstance().isSourceNourriture(pos[0], pos[1]))
					return pos;
			} catch (IndexOutOfBoundsException e) {
			}
		}
		return null;
	}

	protected void prendreGauche() {
		this.posj = Math.abs(this.posj - 1);
	}

	protected void prendreDroite() {
		int taille = Grille.N;
		this.posj = -Math.abs(this.posj + 1 - (taille - 1)) + taille - 1;
	}

	protected void prendreBas() {
		int taille = Grille.N;
		this.posi = -Math.abs(this.posi + 1 - (taille - 1)) + (taille - 1);
	}

	protected void prendreHaut() {
		this.posi = Math.abs(this.posi - 1);
	}

	public void avancerVers(int i, int j) {
		int di = posi - i, dj = posj - j;

		if (i < posi)
			// on veut aller vers le haut (diminuer posi)
			prendreHaut();
		else if (i > posi)
			// on veut aller vers le bas
			prendreBas();
		else if (j < posj)
			// on veut aller vers la gauche (diminuer posj)
			prendreGauche();
		else if (j > posj)
			// on veut aller vers la droite (augmenter posj)
			prendreDroite();

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
