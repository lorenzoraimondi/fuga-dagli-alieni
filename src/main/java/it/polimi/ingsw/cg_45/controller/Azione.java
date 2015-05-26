package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

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
