package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Carta;
import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

import java.io.IOException;

public class UsaAttacco extends Azione {
	
	Settore settore;
	Attacco attacco;
	Carta carta;
	
	public UsaAttacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		attacco=new Attacco(p,g,s);
		this.settore=s;
		this.model=p;
		this.giocatore=g;
	}
	
	@Override
	public RispostaController esegui() throws IOException{
		//IOEx per terminaPartita in attacco
		if(controlli()){
			RispostaController rispostaAttacco;
			rispostaAttacco=attacco.esegui();
			if(!(rispostaAttacco.getMessaggioBroadcast()==null)){
				carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
				giocatore.getCarte().remove(carta);
				model.getMazzoOggetti().getMazzoScarti().add(carta);
			}
	
			return rispostaAttacco;
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && (giocatore.getStato()==Stato.PERICOLO || giocatore.getStato()==Stato.SICURO) && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
				return true;
			} 
			return false;	
		}
		return false;
		
	}
	//Per test
			public Settore getSettore() {
				return settore;
			}
			public Giocatore getGiocatore() {
				return giocatore;
			}
			public StatoDiGioco getPartita() {
				return model;
			}
			
			//
}
