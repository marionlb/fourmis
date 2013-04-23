package core;

import java.util.ArrayList;
import java.util.List;

public class Colonie {
	public FourmiAbstraite listeFourmis[];

	public int nbfourmis = 10;
	static Grille terrain;
	static List<SourceNourriture> listeSN=new ArrayList<SourceNourriture>();

	public Colonie() {
		Colonie.terrain = Grille.getInstance();
		int[] couple = Grille.placerFourmillere();
		placerSourcesNourriture(3);
		listeFourmis = new FourmiAbstraite[nbfourmis];
		
		for (int i = 0; i < nbfourmis; i++) {
			/*
			 * int x = (int) (Math.random() * 50); int y = (int) (Math.random()
			 * * 50);
			 */
			listeFourmis[i] = new FourmiSimple(couple[0], couple[1]);
		}
		System.out.println(listeFourmis[0].voitNourriture());
	}

	public static void placerSourcesNourriture(int nb) {
		int N = Grille.N;
		for (int k = 0; k < nb; k++) {
			int i, j;
			// i et j entre 0 et N-1
			i = (int) (Math.random() * N);
			j = (int) (Math.random() * N);

			Grille.grille[i][j] = -(k + 2);

			listeSN.add(new SourceNourriture(i, j, k + 2));
		}
	}

	public static void pillerSN(int id, int quantite) {
		for (SourceNourriture sn : listeSN)
			if (sn.id == id) {
				sn.estPille(quantite);
				break;
			}
	}
}
