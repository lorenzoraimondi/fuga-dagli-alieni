package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class PescaOggetto extends Azione{

	private CartaOggetto carta;
	public PescaOggetto(Giocatore gioc, StatoDiGioco model){
		super(gioc,model);
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaOggetto) model.getMazzoOggetti().mazzoIniziale.remove(0);
			giocatore.setCarta(carta);
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
		}
	}
	
	protected boolean controlli(){
		if((stato==Stato.BLUFFATO)||(stato==Stato.RIVELATO)){
			if(model.getMazzoOggetti().mazzoIniziale.isEmpty()){
				if(!model.getMazzoOggetti().mazzoScarti.isEmpty()){
					model.getMazzoOggetti().mazzoIniziale.addAll(model.getMazzoOggetti().mazzoScarti);
					model.getMazzoOggetti().mazzoScarti.removeAll(model.getMazzoOggetti().mazzoScarti);
					Collections.shuffle(model.getMazzoOggetti().mazzoIniziale);
					return true;}
			}
		}
		return false;
	}
}
