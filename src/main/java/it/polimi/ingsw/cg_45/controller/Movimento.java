package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.SettorePericoloso;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.SettoreSicuro;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;

public class Movimento extends Azione{
	private Settore settorePartenza,settoreArrivo;
	private Mappa mappa;
	private int portata;
	
	
	
	public Movimento(StatoDiGioco p,Giocatore g, Settore s){
		super(g,p);
		
		this.mappa=p.getMappa();
		this.settoreArrivo=s;
		//this.settoreArrivo=p.getMappa().getMappa().get(s.getCoordinate());
		this.portata=g.getPortata();
		this.settorePartenza=g.getPosizione();
		
		
		}
	
	@Override
	public RispostaController esegui(){
		if(this.controlli()){
			giocatore.setPosizione(settoreArrivo);
			if(settoreArrivo instanceof SettoreSicuro){
				giocatore.setStato(Stato.SICURO);
				return new RispostaController("Movimento effettuato in settore sicuro",null);
			} else if(settoreArrivo instanceof SettorePericoloso){
				if(giocatore instanceof Umano){
					Umano umano=(Umano)giocatore;
					if(umano.isSedato()){
						giocatore.setStato(Stato.SICURO);
						return new RispostaController("Movimento effettuato in settore pericoloso",null);
					}
				}
				giocatore.setStato(Stato.PERICOLO);
				//Cambiato messaggio al giocatore
				return new RispostaController("Movimento effettuato in settore pericoloso",null);
			} else {
				giocatore.setStato(Stato.SCIALUPPA);
				//SettoreScialuppa arrivo=(SettoreScialuppa) mappa.getMappa().get(settoreArrivo.getCoordinate());
				//diventa impossibile pescare altrimenti
				//arrivo.setScoperta();
				return new RispostaController("Movimento effettuato, pesca una carta Scialuppa",giocatore.getNome()+" è nel settore scialuppa "+settoreArrivo.getCoordinate().toString());
				}
		}
		return new RispostaController("Mossa non valida",null);
		
			
	}
		
	@Override
	protected boolean controlli(){
		System.out.println(settorePartenza);
		System.out.println(settoreArrivo);
		System.out.println(portata);
		if(giocatore.getStato()!=Stato.INIZIO && giocatore.getSituazione()==Situazione.ATTIVO){
			return false;
		} else if(mappa.mossaValida(settorePartenza, settoreArrivo, portata)){
			
			if(settoreArrivo instanceof SettoreScialuppa && giocatore instanceof Alieno){
				return false;
			}
			return true;
		}
		return false;
	}
	//Per test

	public Settore getSettoreArrivo() {
		return settoreArrivo;
	}
	public Giocatore getGiocatore() {
		return giocatore;
	}
	public StatoDiGioco getPartita() {
		return model;
	}
	
	//
}

