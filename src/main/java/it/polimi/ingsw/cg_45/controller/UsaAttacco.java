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
	
	public RispostaController esegui(){
		if(controlli()){
			RispostaController rispostaAttacco;
			rispostaAttacco=attacco.esegui();
			carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
			return rispostaAttacco;
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
			return true;
		} 
		return false;
	}
	//Per test
			public Settore getSettore() {
				return settore;
			}
			public Giocatore getGiocatore() {
				return giocatore;
			}
			public StatoDiGioco getPartita() {
				return model;
			}
			
			//
}
