package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

public class UsaSedativi extends Azione{

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	public UsaSedativi(Giocatore g, StatoDiGioco p){
		super(g,p);
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
	}
	
	public void esegui(){
		if(controlli()){
			Umano g=(Umano)giocatore;
			g.setSedato(true);
			carta=giocatore.getCarta(TipoCartaOggetto.SEDATIVI);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
		}
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.SEDATIVI))){
				return true;
		} 
		return false;
	}
	
	
}
