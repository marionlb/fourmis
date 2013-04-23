package core;

import java.util.Arrays;

public class FourmiGentille extends FourmiIntelligente {

	public FourmiGentille(int posi, int posj) {
		super(posi, posj);
	}

	public void deplacement() {
		int[] tab;

		if (revenirMaison) {
			revenirMaison();
			deposerPheromone(Q/chemin.longueur);
		} else if (Grille.getInstance().isSourceNourriture(posi, posj)) {
			revenirMaison = true;
			Colonie.pillerSN((int) -Grille.grille[posi][posj], 10);
		} else if ((tab = voitNourriture()) != null) {
			int i = tab[0], j = tab[1], dist = tab[2];
			avancerVers(i, j);
			this.chemin.ajouterPos(posi, posj);
		} else
			deplacementPheromone();
	}

	public void deplacementPheromone() {
		double rand = Math.random();

		if(rand<this.qo) {
			double p1,p2,p3,p4=0;

			int[] dernierePos;
			if (chemin.itineraire.size() < 2)
				dernierePos = this.chemin.itineraire.lastElement();
			else
				dernierePos = this.chemin.itineraire.elementAt(chemin.itineraire.size() - 2);

			int oldi = dernierePos[0], oldj = dernierePos[1];

			// on vient de la gauche
			if (oldi == posi && oldj < posj) {
				p1 = -1;
				p2 = Grille.getInstance().pheromone(caseEnHaut(), this.posj,
						null);
				p3 = Grille.getInstance().pheromone(this.posi,
						caseADroite(), null);
				p4=Grille.getInstance().pheromone(caseEnBas(),
						this.posj, null);
			}

			// on vient de la droite
			else if (oldi == posi && oldj > posj) {
				p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
						null);
				p2 = Grille.getInstance().pheromone(caseEnHaut(), this.posj,
						null);
				p3 = -1;
				p4=Grille.getInstance().pheromone(caseEnBas(),
						this.posj, null);
			}


			// on vient du haut
			else if (oldi < posi && oldj == posj){
				p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
						null);
				p2 = -1;
				p3 = Grille.getInstance().pheromone(this.posi,
						caseADroite(), null);
				p4=Grille.getInstance().pheromone(caseEnBas(),
						this.posj, null);
			}

			// on vient du bas
			else if (oldi > posi && oldj == posj){
				p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
						null);
				p2 = Grille.getInstance().pheromone(caseEnHaut(), this.posj,
						null);
				p3 = Grille.getInstance().pheromone(this.posi,
						caseADroite(), null);
				p4 = -1;
			}

			else {
				p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
						null);
				p2 = Grille.getInstance().pheromone(caseEnHaut(), this.posj,
						null);
				p3 = Grille.getInstance().pheromone(this.posi,
						caseADroite(), null);
				p4=Grille.getInstance().pheromone(caseEnBas(),
						this.posj, null);
			}

			double[] phers = {p1,p2,p3,p4};
			Arrays.sort(phers);

			//seuil de phéromones
			if(phers[3]<2*Grille.t_init)
				super.deplacementAleatoire();
			
			else {
				
				double somme = 0;
				for (int i = phers.length-1; i >=0; i--) {
					if(phers[i]!=-1)
						somme+=phers[i];
				}
				double tir = Math.random()*somme;
				
				if(tir<phers[3])
					trouverPher(phers[3],p1, p2, p3, p4);
				else if (tir<phers[3]+phers[2])
					trouverPher(phers[2],p1, p2, p3, p4);
				else if(tir<phers[3]+phers[2]+phers[1])
					trouverPher(phers[1],p1, p2, p3, p4);
				else
					trouverPher(phers[0],p1, p2, p3, p4);
				//on cherche phers[0]


				chemin.ajouterPos(posi, posj);	
			}

		} else {
			super.deplacementAleatoire();

		}

		chemin.longueur++;

	}

	private void trouverPher(double pher, double p1, double p2, double p3,double p4) {
		if(pher==p1) {
			prendreGauche();
		} else if(pher==p2) {
			prendreHaut();
		} else if(pher==p3) {
			prendreDroite();
		} else {
			prendreBas();
		}
	}

	public void deplacement2() {

		System.out.println("taille chemin" + this.chemin.itineraire.size());
		System.out.println("revenir maison" + this.revenirMaison);
		if (this.revenirMaison) {
			this.revenirMaison();
			this.deposerPheromone(this.Q / this.chemin.longueur);
		} else {
			if (Grille.getInstance().isSourceNourriture(posi, posj)) {
				this.revenirMaison = true;
				this.chemin.calculerLongueur();
			} else {
				if (this.voitNourriture() == null) {
					double rand = Math.random();
					if (rand < this.qo) {
						double p1, p2, p3, p4, max;
						if (this.chemin.itineraire.size() > 1) {
							int[] dernierePos = this.chemin.itineraire.lastElement();
							int[] avantDerniere = this.chemin.itineraire
							.elementAt(chemin.itineraire.size() - 2);
							if (dernierePos[0] - avantDerniere[0] == 0) { // déplacement
								// précédent
								// horizontal
								if (dernierePos[1] - avantDerniere[1] < 0) { // de
									// la
									// droite
									// vers
									// la
									// gauche
									p1 = Grille.getInstance().pheromone(caseEnHaut(), posj, null);
									p2 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
											null);
									p3 = Grille.getInstance().pheromone(caseEnBas(),
											posj, null);

									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posi = caseEnHaut();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posj = caseAGauche();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = caseEnBas();
										this.chemin.ajouterPos(posi, posj);
									}
								} else { // de la gauche vers la droite
									p1 = Grille.getInstance().pheromone(caseEnBas(),
											posj, null);
									p2 = Grille.getInstance().pheromone(this.posi,
											caseADroite(), null);
									p3 = Grille.getInstance().pheromone(caseEnHaut(), posj, null);

									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posi = caseEnBas();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posj = caseADroite();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posi = caseEnHaut();
										this.chemin.ajouterPos(posi, posj);
									}
								}
							} else { // déplacement précédent vertical
								if (dernierePos[0] - avantDerniere[0] < 0) { // du
									// bas
									// vers
									// le
									// haut
									p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
											null);
									p2 = Grille.getInstance().pheromone(caseEnHaut(), this.posj,
											null);
									p3 = Grille.getInstance().pheromone(this.posi,
											caseADroite(), null);

									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posj = caseAGauche();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posi = caseEnHaut();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = caseADroite();
										this.chemin.ajouterPos(posi, posj);
									}
								} else { // du haut vers le bas
									p1 = Grille.getInstance().pheromone(this.posi, caseAGauche(),
											null);
									p2 = Grille.getInstance().pheromone(caseEnBas(),
											this.posj, null);
									p3 = Grille.getInstance().pheromone(this.posi,
											caseADroite(), null);

									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posj = caseAGauche();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posi = caseEnBas();
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = caseADroite();
										this.chemin.ajouterPos(posi, posj);
									}
								}
							}
						} else {
							p1 = Grille.getInstance().pheromone(caseEnHaut(), posj, null);
							p2 = Grille.getInstance().pheromone(this.posi, caseAGauche(), null);
							p3 = Grille.getInstance().pheromone(caseEnBas(), posj,
									null);
							p4 = Grille.getInstance().pheromone(this.posi,
									caseEnBas(), null);

							max = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
							if (max == p1) {
								this.posi = caseAGauche();
								this.chemin.ajouterPos(posi, posj);
							}
							if (max == p2) {
								this.posj = caseAGauche();
								this.chemin.ajouterPos(posi, posj);
							}
							if (max == p3) {
								this.posi = caseEnBas();
								this.chemin.ajouterPos(posi, posj);
							}
							if (max == p4) {
								this.posj = caseADroite();
								this.chemin.ajouterPos(posi, posj);
							}
						}
					} else {
						this.deplacementAleatoire();
					}
				} else {
					int[] res = this.voitNourriture();
					if (res[2] == 1) {
						this.posi = res[0];
						this.posj = res[1];
					} else {
						if (this.posi - res[0] == 0) {
							if (this.posj - res[1] > 0)
								this.posj = caseAGauche();
							else
								this.posj = caseADroite();
						} else if (this.posi - res[1] == 0) {
							if (this.posi - res[0] > 0)
								this.posi = caseEnHaut();
							else
								this.posi = caseEnBas();
						}
					}
				}
			}
		}
	}

	private int caseADroite() {
		return -Math.abs(this.posj + 1 - 49) + 49;
	}

	private int caseEnBas() {
		return -Math.abs(this.posi + 1 - 49) + 49;
	}

	private int caseAGauche() {
		return Math.abs(this.posj - 1);
	}

	private int caseEnHaut() {
		return Math.abs(this.posi - 1);
	}
}
