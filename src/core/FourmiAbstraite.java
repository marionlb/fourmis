package core;

public abstract class FourmiAbstraite implements Runnable {
	int posi, posj;
	Chemin chemin;

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
