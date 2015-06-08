package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;

public class UsaDifesa extends Azione{
	
	private CartaOggetto carta;
	
	
	public UsaDifesa(Giocatore g, StatoDiGioco p){
		super(g,p);
	}
	
	public RispostaController esegui(){
		carta=giocatore.getCarta(TipoCartaOggetto.DIFESA);
		giocatore.getCarte().remove(carta);
		model.getMazzoOggetti().getMazzoScarti().add(carta);
		return new RispostaController(null,null);
	}

	
	protected boolean controlli() {
		return true;
	}
	
}
