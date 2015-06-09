package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;

public class TerminaTurno extends Azione {

	public TerminaTurno(Giocatore gioc, StatoDiGioco model) {
		super(gioc, model);
	}

	@Override
	public RispostaController esegui() {
		if(this.controlli()){
			Giocatore giocatorePassato,giocatoreProssimo,giocatoreSwap;
			//giocatore.setStato(Stato.INIZIO);
			giocatore.setStato(Stato.TURNOTERMINATO);
			if(giocatore instanceof Umano){
				Umano giocatoreUmano=(Umano)giocatore;
				giocatoreUmano.setSedato(false);
				giocatoreUmano.setPortata(1);
			}
			//model.incrementCurrentPlayer();
			giocatorePassato=model.getGiocatori().remove(0);
			model.getGiocatori().add(giocatorePassato);
			giocatoreProssimo=model.getGiocatori().get(0);
			
			while(giocatoreProssimo.getSituazione()==Situazione.MORTO){
				giocatoreSwap=model.getGiocatori().remove(0);
				model.getGiocatori().add(giocatoreSwap);
				giocatoreProssimo=model.getGiocatori().get(0);
			
			}
			giocatoreProssimo.setStato(Stato.INIZIO);
			
			//if(giocatore.getOrdine()==1){
			if(giocatore.getOrdine()==model.getGiocatori().size()){
				model.incrementTurno();
				return new RispostaController("Turno terminato", "Il giocatore "+giocatore.getID()+" ha passato. "
						+ "Turno "+model.getTurno()+"."
						+ "Tocca al giocatore "+giocatoreProssimo.getID()+".");
			}
				return new RispostaController("Turno terminato", "Il giocatore "+giocatore.getID()+" ha passato. "
					+ "Tocca al giocatore "+giocatoreProssimo.getID()+".");
			//if(model.getTurno()==40)
				//Partita.set("FINITA");
			/*
			if(giocatore.getID()==model.getGiocatori().size())
				model.incrementTurno();
			//if(model.getTurno()==40)
				//Partita.set("FINITA");
				 
				 */
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getStato()==Stato.ATTACCATO||giocatore.getStato()==Stato.CARTASCIALUPPA||giocatore.getStato()==Stato.EFFETTOCONCLUSO||giocatore.getStato()==Stato.SICURO){
			if(giocatore.getCarte().size()>=4){
				System.out.println("1");
				return false;
			}
				
			return true;
		}
		System.out.println("2");
		return false;
	}

}
