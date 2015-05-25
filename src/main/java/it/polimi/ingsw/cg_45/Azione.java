package it.polimi.ingsw.cg_45;

public abstract class Azione {

	protected Giocatore giocatore;
	protected StatoDiGioco model;
	protected Stato stato;
	
	public Azione(Giocatore gioc,StatoDiGioco model){
		this.giocatore=gioc;
		this.model=model;
		this.stato=giocatore.getStato();
	}
	
	public abstract void esegui();
	
	protected abstract boolean controlli();
}
