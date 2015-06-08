package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaScialuppa;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaScialuppa;

public class PescaScialuppa extends Azione{
	
	private CartaScialuppa carta;
	private SettoreScialuppa scialuppa;
	
	public PescaScialuppa(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
		this.scialuppa=(SettoreScialuppa)giocatore.getPosizione();
	}
	
	public RispostaController esegui(){
		if(this.controlli()){
			carta=(CartaScialuppa) model.getMazzoScialuppe().pescaCarta();
			if(carta.getTipo()!=TipoCartaScialuppa.ROSSA){
				scialuppa.setScoperta();
				giocatore.setSituazione(Situazione.VINTO);
				return new RispostaController("Hai vinto","Il giocatore "+giocatore.getID()+"ha vinto!");
			}
			scialuppa.setScoperta();
			giocatore.setStato(Stato.CARTASCIALUPPA);
			return new RispostaController("Scialuppa bloccata!","Il giocatore "+giocatore.getID()+"ha pescato una carta scialuppa, ma la scialuppa è bloccata!");
		}
		return new RispostaController("Mossa non valida",null);
	}

	protected boolean controlli() {
		if(giocatore.getStato()==Stato.SCIALUPPA){
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
