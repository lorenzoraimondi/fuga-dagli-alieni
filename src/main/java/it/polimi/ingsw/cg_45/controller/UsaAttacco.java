package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Carta;
import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;

public class UsaAttacco extends Azione {
	
	Settore settore;
	Attacco attacco;
	Carta carta;
	
	public UsaAttacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		attacco=new Attacco(p,g,s);
		this.settore=s;
		this.model=p;
		this.giocatore=g;
	}
	
	public void esegui(){
		if(controlli()){
			attacco.esegui();
			carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
		}
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
			return true;
		} 
		return false;
	}
	
}
