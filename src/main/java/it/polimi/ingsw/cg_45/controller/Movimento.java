package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.SettorePericoloso;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.SettoreSicuro;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

public class Movimento extends Azione{
	private Settore settorePartenza,settoreArrivo;
	private Mappa mappa;
	private int portata;
	
	
	
	public Movimento(StatoDiGioco p,Giocatore g, Settore s){
		super(g,p);
		this.settoreArrivo=s;
		this.mappa=p.getMappa();
		this.portata=g.getPortata();
		this.settorePartenza=g.getPosizione();
		
		
		}
	
	public void esegui(){
		if(this.controlli()){
			giocatore.setPosizione(settoreArrivo);
			if(settoreArrivo instanceof SettoreSicuro){
				giocatore.setStato(Stato.SICURO);
			} else if(settoreArrivo instanceof SettorePericoloso){
				giocatore.setStato(Stato.PERICOLO);
			} else {
				giocatore.setStato(Stato.SCIALUPPA);
				SettoreScialuppa arrivo=(SettoreScialuppa) mappa.getMappa().get(settoreArrivo.getCoordinate());
				arrivo.setScoperta();}
		}
			
	}
		
	protected boolean controlli(){
		if(giocatore.getStato()!=Stato.INIZIO){
			return false;
		} else if(mappa.mossaValida(settorePartenza, settoreArrivo, portata)){
			return true;
		}
		return false;
	}
	
}

