package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

public class UsaTeletrasporto extends Azione {
	
	private Mappa mappa; 
	private MazzoOggetti mazzo;
	private CartaOggetto carta;
	
	public UsaTeletrasporto(Giocatore g, StatoDiGioco p){
		super(g,p);
		this.mappa=p.getMappa();
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
		
	}
	
	@Override
	public RispostaController esegui(){
		if(controlli()){
			Umano giocatoreUmano=(Umano)giocatore;
			giocatoreUmano.setPosizioneIniziale(mappa);
			carta=giocatore.getCarta(TipoCartaOggetto.TELETRASPORTO);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			return new RispostaController("Sei stato teletrasportato nella tua posizione iniziale",giocatore.getNome()+"è stato teletrasportato nella tua posizione iniziale");
		}
		return new RispostaController("Mossa non valida",null);
		
		
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO))){
				return true;
			}
			return false;
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
