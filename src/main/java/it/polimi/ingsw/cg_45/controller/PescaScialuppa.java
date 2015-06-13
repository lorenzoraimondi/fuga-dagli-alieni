package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaScialuppa;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaScialuppa;

import java.io.IOException;

public class PescaScialuppa extends Azione{
	
	private CartaScialuppa carta;
	private SettoreScialuppa scialuppa;
	
	public PescaScialuppa(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
		this.scialuppa=(SettoreScialuppa)giocatore.getPosizione();
	}
	
	@Override
	public RispostaController esegui() throws IOException{
		//IOEx lanciata per terminaPartita
		if(this.controlli()){
			carta=(CartaScialuppa) model.getMazzoScialuppe().pescaCarta();
			if(carta.getTipo()!=TipoCartaScialuppa.ROSSA){
				scialuppa.setScoperta();
				giocatore.setSituazione(Situazione.VINTO);
				//In questo stato può solo usare/scartare carta, che sono vietati da controlli
				giocatore.setStato(Stato.EFFETTOCONCLUSO);				
				RispostaController terminaPartita=new TerminaPartita(giocatore,model).esegui();
				if(terminaPartita!=null)
					return terminaPartita;
				
				return new RispostaController("Ci sei riuscito!",giocatore.getNome()+" è scappato dall'astronave!");
			}
			scialuppa.setScoperta();
			giocatore.setStato(Stato.CARTASCIALUPPA);
			return new RispostaController("Scialuppa bloccata!",giocatore.getNome()+"ha pescato una carta scialuppa, ma la scialuppa è bloccata!");
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getStato()==Stato.SCIALUPPA && giocatore.getSituazione()==Situazione.ATTIVO){
			SettoreScialuppa scialuppa=(SettoreScialuppa) model.getMappa().getMappa().get(giocatore.getPosizione().getCoordinate());
			if(!scialuppa.isScoperta())
				return true;
		}
		return false;
	}
	//Per test

			public Giocatore getGiocatore() {
				return giocatore;
			}
			public StatoDiGioco getPartita() {
				return model;
			}
			
			//
}
