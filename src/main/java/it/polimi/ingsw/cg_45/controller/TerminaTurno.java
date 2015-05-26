package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

public class TerminaTurno extends Azione {

	public TerminaTurno(Giocatore gioc, StatoDiGioco model) {
		super(gioc, model);
	}

	@Override
	public void esegui() {
		if(this.controlli()){
			giocatore.setStato(Stato.INIZIO);
			model.incrementCurrentPlayer();
			if(giocatore.getID()==model.getGiocatori().size())
				model.incrementTurno();
			//if(model.getTurno()==40)
				//Partita.set("FINITA");
		}
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getStato()==Stato.ATTACCATO||giocatore.getStato()==Stato.SCIALUPPA||giocatore.getStato()==Stato.EFFETTOCONCLUSO||giocatore.getStato()==Stato.SICURO)
			return true;
		return false;
	}

}
