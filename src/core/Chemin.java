package core;

import java.util.Vector;

public class Chemin {
	
	Vector<int[]> itineraire;
	float longueur;
	
	public Chemin() {
		super();
		itineraire = new Vector<int[]>();
		longueur = 0;
	}

	public void calculerLongueur(){
		float l = 0;
		for (int i=0; i<itineraire.size()-1; i++)
		{
			l +=Math.sqrt( (itineraire.get(i)[0]-itineraire.get(i+1)[0])*(itineraire.get(i)[0]-itineraire.get(i+1)[0]) + (itineraire.get(i)[1]-itineraire.get(i+1)[1])*(itineraire.get(i)[1]-itineraire.get(i+1)[1]) );
		}
		
		longueur = l;
	}
	
	public void ajouterPos(int i, int j){
		int[] pos = {i,j};
		itineraire.add(pos);
	}
}
