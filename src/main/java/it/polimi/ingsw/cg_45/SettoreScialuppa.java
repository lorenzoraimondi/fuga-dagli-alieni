package it.polimi.ingsw.cg_45;

public class SettoreScialuppa extends Settore{

	private boolean scoperta;
	
	public SettoreScialuppa(int x, int y, int z) {
		super(x,y,z);
		this.scoperta=false;
	}

	public boolean isScoperta() {
		return scoperta;
	}

	public void setScoperta() {
		this.scoperta = true;
	}
	
	

}
