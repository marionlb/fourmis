package core;

public class FourmiSimple extends FourmiAbstraite{
	
	public FourmiSimple(int posi, int posj) {
		super(posi, posj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacement() {
		
		super.deplacementAleatoire();
	}
}
