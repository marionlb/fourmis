package core;

public class FourmiSimple extends FourmiAbstraite{
	
	public FourmiSimple(int posi, int posj) {
		super(posi, posj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacement() {
		
		double tirage = Math.random();
		
		if (this.chemin.itineraire.size()>1) {
			int[] dernierePos = this.chemin.itineraire.lastElement();
			int[] avantDerniere = this.chemin.itineraire.elementAt(chemin.itineraire.size()-2);

			
			if (dernierePos[0]-avantDerniere[0]==0){  //déplacement précédent horizontal
				if(dernierePos[1]-avantDerniere[1]<0){	//de la droite vers la gauche
					if (tirage<0.3){
						this.posi = Math.abs(this.posi - 1);	//monter
					}
					else if (tirage>0.3){
						this.posi = -Math.abs(this.posi+1 -49) +49; 	//descendre
					}
					else{
						this.posj = Math.abs(this.posj - 1);	//aller à gauche
					}
				}
				else{	//de la gauche vers la droite
					if (tirage<0.3){
						this.posi = Math.abs(this.posi - 1);	//monter
					}
					else if (tirage>0.3){
						this.posi = -Math.abs(this.posi+1 -49) +49; 	//descendre
					}
					else{
						this.posj = -Math.abs(this.posj+1 -50) +50; 	//aller à droite
					}
				}
			}
			else{	//déplacement précédent vertical
				if(dernierePos[0]-avantDerniere[0]<0){	//du bas vers le haut
					if (tirage<0.3){
						this.posj = Math.abs(this.posj - 1);	//aller à gauche
					}
					else if (tirage>0.3){
						this.posj = -Math.abs(this.posj+1 -49) +49; 	//aller à droite
					}
					else{
						this.posi = Math.abs(this.posi - 1);	//monter  
					}
				}
				else{	//du haut vers le bas
					if (tirage<0.3){
						this.posj = Math.abs(this.posj - 1);	//aller à gauche
					}
					else if (tirage>0.3){
						this.posj = -Math.abs(this.posj+1 -49) +49; 	//aller à droite
					}
					else{
						this.posi = -Math.abs(this.posi+1 -49) +49; 	//descendre
					}
				}
			}
		}
		else {
			if (tirage<0.25) {
				this.posi = -Math.abs(this.posi+1 -49) +49; 
			}
			else if (tirage<0.50) {
				this.posj = Math.abs(this.posj - 1);
			}
			else if (tirage<0.75) {
				this.posi = Math.abs(this.posi - 1);
			}
			else {
				this.posj = -Math.abs(this.posj+1 -49) +49;
			}
		}
		this.chemin.ajouterPos(posi, posj);
	}
}
