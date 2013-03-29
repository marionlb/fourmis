package core;

public abstract class FourmiAbstraite implements Runnable {
	int posi, posj;
	Chemin chemin;

	public abstract void deplacement();

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

abstract class FourmiIntelligente extends FourmiAbstraite {

	Pheromone pheromone;

	public void deposerPheromone(double quantite) {
		Grille.getInstance().deposerPheromone(posi, posj, pheromone, quantite);
	}

}

enum Pheromone {
	Phe_A, Phe_B;
}
