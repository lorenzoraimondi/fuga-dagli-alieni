package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;


public class PescaOggetto extends Azione{

	private CartaOggetto carta;
	public PescaOggetto(Giocatore gioc, StatoDiGioco model){
		super(gioc,model);
	}
	
	@Override
	public RispostaController esegui(){
		if(this.controlli()){
			carta=(CartaOggetto) model.getMazzoOggetti().pescaCarta();
			if(carta!=null){
				giocatore.setCarta(carta);
				giocatore.setStato(Stato.EFFETTOCONCLUSO);
				return new RispostaController("Hai pescato una carta "+carta.getTipo().toString(),"Il giocatore "+giocatore.getID()+"ha pescato una carta oggetto");	
			} else {
				giocatore.setStato(Stato.EFFETTOCONCLUSO);
				return new RispostaController("Non ci sono carte oggetto disponibili, continua il turno.",null);
				
			}
			
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if((stato==Stato.BLUFFATO)||(stato==Stato.RIVELATO))
					return true;
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
