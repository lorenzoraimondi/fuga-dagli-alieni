package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;

import java.io.IOException;

public class TerminaTurno extends Azione {

	public TerminaTurno(Giocatore gioc, StatoDiGioco model) {
		super(gioc, model);
	}

	@Override
	public RispostaController esegui() throws IOException {
		if(this.controlli()){
			Giocatore giocatorePassato,giocatoreProssimo,giocatoreSwap;

			giocatore.setSituazione(Situazione.INATTIVO);
			giocatore.setStato(Stato.TURNOTERMINATO);
			if(giocatore instanceof Umano){
				Umano giocatoreUmano=(Umano)giocatore;
				giocatoreUmano.setSedato(false);
				giocatoreUmano.setPortata(1);
			}
			//model.incrementCurrentPlayer();
			/*	Meglio se messo in Attacco e PescaScialuppa
			//Giocatore inutile, riesco a overrideare costruttore?
			RispostaController terminaPartita=new TerminaPartita(giocatore,model).esegui();
			if(terminaPartita!=null)
				return terminaPartita;*/
			
			giocatorePassato=model.getGiocatori().remove(0);
			model.getGiocatori().add(giocatorePassato);
			giocatoreProssimo=model.getGiocatori().get(0);
			
			int giocatoriSaltati=0;
			
			while(giocatoreProssimo.getSituazione()==Situazione.DISCONNESSO || giocatoreProssimo.getSituazione()==Situazione.MORTO || giocatoreProssimo.getSituazione()==Situazione.VINTO){
				giocatoreSwap=model.getGiocatori().remove(0);
				model.getGiocatori().add(giocatoreSwap);
				giocatoreProssimo=model.getGiocatori().get(0);
				
				giocatoriSaltati++;
			
			}
			giocatoreProssimo.setSituazione(Situazione.ATTIVO);
			giocatoreProssimo.setStato(Stato.INIZIO);
			
			//if(giocatore.getOrdine()==1){
			System.out.println("ordine "+giocatore.getOrdine());
			System.out.println("somma "+giocatoriSaltati);
			if(giocatore.getOrdine()+giocatoriSaltati>=model.getGiocatori().size()-1){
				model.incrementTurno();
				System.out.println("turno dopo: "+model.getTurno());
				if(model.getTurno()==40)
					return new TerminaPartita(giocatore,model).esegui();
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
						+ "Turno "+model.getTurno()+". "
						+ "Tocca a "+giocatoreProssimo.getNome()+".");
			}
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
					+ "Tocca a "+giocatoreProssimo.getNome()+".");
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
		if((giocatore.getStato()==Stato.ATTACCATO||giocatore.getStato()==Stato.CARTASCIALUPPA||giocatore.getStato()==Stato.EFFETTOCONCLUSO||giocatore.getStato()==Stato.SICURO) && giocatore.getSituazione()==Situazione.ATTIVO){
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
