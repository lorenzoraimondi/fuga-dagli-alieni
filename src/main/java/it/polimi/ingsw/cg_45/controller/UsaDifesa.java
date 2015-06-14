package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

public class UsaDifesa extends Azione{
	
	private CartaOggetto carta;
	
	
	public UsaDifesa(Giocatore g, StatoDiGioco p){
		super(g,p);
	}
	
	@Override
	public RispostaController esegui(){
		if(controlli()){
			carta=giocatore.getCarta(TipoCartaOggetto.DIFESA);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
			return new RispostaController(null,null);	
		}
		return null;		
	}

	@Override
	protected boolean controlli() {
		if(giocatore instanceof Umano)
			return true;
		return false;
	}
	
}
