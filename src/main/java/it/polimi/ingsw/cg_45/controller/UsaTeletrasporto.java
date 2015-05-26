package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;

public class UsaTeletrasporto extends Azione {
	
	//private Mappa mappa; 
	private MazzoOggetti mazzo;
	private CartaOggetto carta;
	
	public UsaTeletrasporto(Giocatore g, StatoDiGioco p){
		super(g,p);
		//this.mappa=p.getMappa();
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
		
	}
	
	public void esegui(){
		if(controlli()){
			//giocatore.setPosizione(SettorePartenzaUmani);
			carta=giocatore.getCarta(TipoCartaOggetto.TELETRASPORTO);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
		}
		
	}
	
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVONASCOSTO){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO))){
				return true;
			} 
		} return false;
	}
}
