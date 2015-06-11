package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TerminaPartita extends Azione{

	public TerminaPartita(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
	}
	
	@Override
	public RispostaController esegui() throws IOException {
		if(controlli()){
			int umaniVincitori=0;
			List<String> risposte=new ArrayList<String>();
	
			for(Giocatore g : model.getGiocatori()){
				if(g instanceof Umano && g.getSituazione()==Situazione.VINTO){
					umaniVincitori++;
					risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", ha vinto!\n");
				}
			}
			
			if(umaniVincitori!=(model.getGiocatori().size()/2)){
				for(Giocatore g : model.getGiocatori()){
					if(g instanceof Alieno){
						risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", ha vinto!\n");
					}
				}	
			}
			
			String risposta=new String("\nPartita terminata!\n");
			
			for(String s : risposte){
				risposta=risposta.concat(s);
			}
			
			for(Giocatore g : model.getGiocatori()){
				//Pensare se cambiare stato?
				g.setSituazione(Situazione.DISCONNESSO);
				g.setStato(Stato.TURNOTERMINATO);
			}
			
			return new RispostaController("",risposta);
	
		}
		
		return null;
				
	}

	@Override
	protected boolean controlli() {
		if(model.numeroUmaniInGioco()==0 || model.getTurno()==40)
			return true;
		return false;
		
	}
	
}
