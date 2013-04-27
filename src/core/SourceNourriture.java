package core;

public class SourceNourriture {

	int i, j;
	int id;
	public int ressources;

	public SourceNourriture(int i, int j, int id) {
		// ressources initiales entre 100 et 300
		int r = (int) (Math.random() * 2 + 1);
		this.ressources = r * 100;
		this.id = id;
	}

	public void estPille(int quantite) {
		ressources -= quantite;
	}
}
