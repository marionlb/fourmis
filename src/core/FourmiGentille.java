package core;

public class FourmiGentille extends FourmiIntelligente {

	public FourmiGentille(int posi, int posj) {
		super(posi, posj);
	}

	@Override
	public void deplacement() {
		
		if (this.revenirMaison) {
			
		}
		
		else {
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
				if (Grille.getInstance().isSourceNourriture(this.posi, this.posj)) {
					this.revenirMaison = true;
				}
			}
			else {
				this.deplacementAleatoire();
				if (Grille.getInstance().isSourceNourriture(this.posi, this.posj)) {
					this.revenirMaison = true;
				}
			}
		}
	}
}
