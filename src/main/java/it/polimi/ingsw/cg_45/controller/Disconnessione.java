package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;

public class Disconnessione extends Azione {
	
	ServerInterface server;

	public Disconnessione(Giocatore giocatore, StatoDiGioco partita, ServerInterface server) {
		// TODO Auto-generated constructor stub
		super(giocatore,partita);
		this.server=server;
	}

	@Override
	public RispostaController esegui() throws IOException {
		// TODO Auto-generated method stub
		giocatore.setStato(Stato.EFFETTOCONCLUSO);
		new TerminaTurno(giocatore,model,server).esegui();
		giocatore.setSituazione(Situazione.DISCONNESSO);
		giocatore.setStato(Stato.TURNOTERMINATO);
		return new RispostaController("ti sei disconnesso con successo",giocatore.getNome()+" si Ã¨ disconnesso.Tocca a "+model.getGiocatori().get(0).getNome());
	}

	@Override
	protected boolean controlli() {
		// TODO Auto-generated method stub
		return true;
	}

}
