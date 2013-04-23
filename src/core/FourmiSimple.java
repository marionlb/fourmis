package core;

public class FourmiSimple extends FourmiAbstraite {

	public FourmiSimple(int posi, int posj) {
		super(posi, posj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacement() {
		int[] tab;

		if (revenirMaison) {
			revenirMaison();
		} else if (Grille.getInstance().isSourceNourriture(posi, posj)) {
			revenirMaison = true;
			Colonie.pillerSN((int) -Grille.grille[posi][posj], 10);
			revenirMaison();
		} else if ((tab = voitNourriture()) != null) {
			int i = tab[0], j = tab[1], dist = tab[2];
			avancerVers(i, j);
			this.chemin.ajouterPos(posi, posj);
		} else
			super.deplacementAleatoire();
	}
}
