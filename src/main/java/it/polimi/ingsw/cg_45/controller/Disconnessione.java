package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

public class Disconnessione extends Azione {

	public Disconnessione(Giocatore giocatore, StatoDiGioco partita) {
		// TODO Auto-generated constructor stub
		this.giocatore=giocatore;
		this.model=partita;
	}

	@Override
	public RispostaController esegui() throws IOException {
		// TODO Auto-generated method stub
		giocatore.setSituazione(Situazione.DISCONNESSO);
		return new RispostaController("ti sei disconnesso con successo","giocatore "+giocatore.getID()+" si è disconnesso");
	}

	@Override
	protected boolean controlli() {
		// TODO Auto-generated method stub
		return true;
	}

}
