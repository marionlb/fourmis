package core;

public class Grille {

	public static double grille[][];

	public static int N = 50;
	public static double persistance = 0.8;
	public static double t_init = 0.001;

	private static Grille instance = null;
	private static int decalage = 10000;

	public Grille() {
		Grille.N = 50;
		Grille.grille = new double[N][N];

		// initialisation de la trace initiale
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				grille[i][j] = t_init;
			}
		}
	}

	public boolean isFourmillere(int i, int j) {
		if (grille[i][j] == -1)
			return true;
		return false;
	}

	public boolean isSourceNourriture(int i, int j) {
		if (grille[i][j] < -1)
			return true;
		return false;
	}

	public double pheromone(int i, int j, Pheromone p) {

		// Cas qui devrait pas arriver
		if (grille[i][j] < 0) {
			return grille[i][j];
		}

		double pA = grille[i][j] % decalage;
		double pB = grille[i][j] - decalage;

		// Si on ne précise pas de phéromones, on retourne la somme de Pher_A et
		// Pher_B
		if (p == null)
			return pA + pB;

		// Sinon on retourne la pheromone demandée
		if (p == Pheromone.Phe_A) {
			return pA;
		} else if (p == Pheromone.Phe_B) {
			return pB;
		}

		return 0;
	}

	public void evaporation() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				grille[i][j] -= persistance * grille[i][j];
			}
		}
	}

	public static Grille getInstance() {
		if (instance == null) {
			synchronized (Grille.class) {
				if (instance == null) {
					instance = new Grille();
				}
			}
		}
		return instance;
	}

	public static void placerFourmillere() {
		int i, j;
		// i et j entre N/4 et 3N/4
		i = (int) (Math.random() * N / 2 + N / 4);
		j = (int) (Math.random() * N / 2 + N / 4);
		grille[i][j] = -1;
	}

	public static void placerSourcesNourriture(int nb) {
		for (int k = 0; k < nb; k++) {
			int i, j;
			// i et j entre 0 et N-1
			i = (int) (Math.random() * N);
			j = (int) (Math.random() * N);

			grille[i][j] = -(k + 2);
		}
	}

	public void deposerPheromone(int i, int j, Pheromone pheromone, double quantite) {

		if (pheromone == Pheromone.Phe_A) {
			grille[i][j] += quantite;
		} else if (pheromone == Pheromone.Phe_B) {
			grille[i][j] += quantite * decalage;
		}

	}
}
