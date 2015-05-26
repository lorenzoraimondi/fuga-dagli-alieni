package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaScialuppa;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaScialuppa;

public class PescaScialuppa extends Azione{
	
	private CartaScialuppa carta;
	
	public PescaScialuppa(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaScialuppa) model.getMazzoScialuppe().pescaCarta();
			if(carta.getTipo()==TipoCartaScialuppa.ROSSA)
				giocatore.setSituazione(Situazione.VINTO);
			giocatore.setStato(Stato.CARTASCIALUPPA);
		}
	}

	protected boolean controlli() {
		if(giocatore.getStato()==Stato.SCIALUPPA){
			SettoreScialuppa scialuppa=(SettoreScialuppa) model.getMappa().getMappa().get(giocatore.getPosizione().getCoordinate());
			if(!scialuppa.isScoperta())
				return true;
		}
		return false;
	}
}
