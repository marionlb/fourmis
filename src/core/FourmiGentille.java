package core;

public class FourmiGentille extends FourmiIntelligente {

	public FourmiGentille(int posi, int posj) {
		super(posi, posj);
	}

	@Override
	public void deplacement() {
		
		if (this.revenirMaison) {
			if (Grille.getInstance().isFourmillere(posi, posj))
				this.revenirMaison = false;
			else {
				this.posi = this.chemin.itineraire.get(this.chemin.itineraire.size()-2)[0];
				this.posj = this.chemin.itineraire.get(this.chemin.itineraire.size()-2)[1];
				this.deposerPheromone(this.Q/this.chemin.longueur);
				this.chemin.itineraire.removeElementAt(this.chemin.itineraire.size()-1);
			}
		}
		else {
			if (Grille.getInstance().isSourceNourriture(posi, posj)) {
				this.revenirMaison = true;
				this.chemin.calculerLongueur();
			}
			else {
				if (this.voitNourriture()==null) {
					double rand = Math.random();
					if (rand < this.qo) {
						double p1, p2, p3, max;
						if (this.chemin.itineraire.size()>1) {
							int[] dernierePos = this.chemin.itineraire.lastElement();
							int[] avantDerniere = this.chemin.itineraire.elementAt(chemin.itineraire.size()-2);
							if (dernierePos[0]-avantDerniere[0]==0){  //déplacement précédent horizontal
								if(dernierePos[1]-avantDerniere[1]<0){	//de la droite vers la gauche
									p1 = Grille.getInstance().pheromone(Math.abs(this.posi - 1), posj, null);
									p2 = Grille.getInstance().pheromone(this.posi, Math.abs(this.posj - 1), null);
									p3 = Grille.getInstance().pheromone(-Math.abs(this.posi+1 -49) +49, posj, null);
									
									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posi = Math.abs(this.posi - 1);
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posj = Math.abs(this.posj - 1);
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = -Math.abs(this.posi+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									}
								}
								else{	//de la gauche vers la droite
									p1 = Grille.getInstance().pheromone(-Math.abs(this.posi+1 -49) +49, posj, null);
									p2 = Grille.getInstance().pheromone(this.posi, -Math.abs(this.posj+1 -49) +49, null);
									p3 = Grille.getInstance().pheromone( Math.abs(this.posi - 1), posj, null);
									
									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posi = -Math.abs(this.posi+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posj = -Math.abs(this.posj+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posi = Math.abs(this.posi - 1);
										this.chemin.ajouterPos(posi, posj);
									}
								}
							}
							else{	//déplacement précédent vertical
								if(dernierePos[0]-avantDerniere[0]<0){	//du bas vers le haut
									p1 = Grille.getInstance().pheromone(this.posi, Math.abs(this.posj - 1), null);
									p2 = Grille.getInstance().pheromone(Math.abs(this.posi - 1), this.posj, null);
									p3 = Grille.getInstance().pheromone(this.posi, -Math.abs(this.posj+1 -49) +49, null);
									
									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posj =  Math.abs(this.posj - 1);
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posi = Math.abs(this.posi - 1);
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = -Math.abs(this.posj+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									} 
								}
								else{	//du haut vers le bas
									p1 = Grille.getInstance().pheromone(this.posi, Math.abs(this.posj - 1), null);
									p2 = Grille.getInstance().pheromone(-Math.abs(this.posi+1 -49) +49, this.posj, null);
									p3 = Grille.getInstance().pheromone(this.posi, -Math.abs(this.posj+1 -49) +49, null);
									
									max = Math.max(p1, Math.max(p2, p3));
									if (max == p1) {
										this.posj = Math.abs(this.posj - 1);
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p2) {
										this.posi = -Math.abs(this.posi+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									}
									if (max == p3) {
										this.posj = -Math.abs(this.posj+1 -49) +49;
										this.chemin.ajouterPos(posi, posj);
									} 
								}
							}	
						}
						else {
							
						}
					}
					else {
						this.deplacementAleatoire();
					}
				}	
				else {
					int[] res = this.voitNourriture();
					if (res[2]==1) {
						this.posi = res[0];
						this.posj = res[1];
					}
					else {
						if (this.posi - res[0] == 0) {
							if (this.posj - res[1] > 0) 
								this.posj = Math.abs(this.posj - 1);
							else
								this.posj = -Math.abs(this.posj+1 -49) +49;
						}
						else if (this.posi - res[1] == 0) {
							if (this.posi - res[0] > 0)
								this.posi = Math.abs(this.posi - 1);
							else
								this.posi = -Math.abs(this.posi+1 -49) +49; 
						}
					}
				}
			}
		}
	}
}
