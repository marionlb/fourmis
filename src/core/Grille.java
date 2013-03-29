package core;

public class Grille {

	int N;
	double grille[][];
	
	private static Grille instance = null;


	private Grille() {

	}

	public boolean isFourmillere(int i, int j) {
		return false;
	}

	public boolean isSourceNourriture(int i, int j) {
		return false;
	}
	
	public double pheromone(Pheromone p) {
		
		return 0;
	}
	
	public void evaporation() {
		
	}

	public static Grille getInstance() {
		if (instance == null) {
			synchronized (Grille.class){
				if (instance == null) {
					instance = new Grille();
				}
			}
		}
		return instance;
	}
}
