package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;


public class PescaOggetto extends Azione{

	private CartaOggetto carta;
	public PescaOggetto(Giocatore gioc, StatoDiGioco model){
		super(gioc,model);
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaOggetto) model.getMazzoOggetti().pescaCarta();
			giocatore.setCarta(carta);
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
		}
	}
	
	protected boolean controlli(){
		if((stato==Stato.BLUFFATO)||(stato==Stato.RIVELATO))
					return true;
		return false;
	}
}
