package core;

import java.util.Vector;

public class Chemin {

	Vector<int[]> itineraire;
	float longueur;

	public Chemin() {
		super();
		itineraire = new Vector<int[]>();
		longueur = 0;
	}

	public void calculerLongueur() {
		longueur = itineraire.size();
	}

	public void ajouterPos(int i, int j) {
		int[] pos = { i, j };
		itineraire.add(pos);
	}
}
