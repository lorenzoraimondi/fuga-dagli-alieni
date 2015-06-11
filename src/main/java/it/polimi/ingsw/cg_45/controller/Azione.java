package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

public abstract class Azione {

	protected Giocatore giocatore;
	protected StatoDiGioco model;
	protected Stato stato;
	
	public Azione(Giocatore gioc,StatoDiGioco model){
		this.giocatore=gioc;
		this.model=model;
		this.stato=giocatore.getStato();
	}
	
	public Azione(){};
	
	//public abstract void esegui() throws IOException;
	public abstract RispostaController esegui() throws IOException;
	
	protected abstract boolean controlli();
}
