package it.polimi.ingsw.cg_45.controller;

import java.util.ArrayList;


import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

public class Attacco extends Azione{
	private Settore settore;
	
	
	public Attacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		this.settore=s;
	}
	
	public RispostaController esegui(){
		ArrayList<String> risposte=new ArrayList<String>();
		if(controlli()){
			for(Giocatore g : model.getGiocatori()){
				if(g!=giocatore && g.getPosizione()==settore){
					if(g instanceof Umano && (g.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)))){
						new UsaDifesa(g,model).esegui();
						risposte.add("Il giocatore "+g.getID()+" ha usato la carta Difesa");
						//return new RispostaController("Attacco fallito!","Il giocatore "+g.getID()+" ha usato la carta Difesa");
					} else {
						g.setSituazione(Situazione.MORTO);
						g.setStato(Stato.TURNOTERMINATO);
						if(g instanceof Umano && giocatore instanceof Alieno){
							giocatore.setPortata(3);
						}
						if(!g.getCarte().isEmpty()){
							model.getMazzoOggetti().getMazzoScarti().addAll(g.getCarte());
							g.getCarte().removeAll(g.getCarte());							
						}
						risposte.add("Il giocatore "+g.getID()+", "+g.getClass().getSimpleName()+", è morto!");
						//return new RispostaController("Attacco riuscito!","Il giocatore "+g.getID()+","+g.getClass().getName()+", è morto!");
					}
					}
			}
			giocatore.setStato(Stato.ATTACCATO);
			if(risposte.isEmpty())
				return new RispostaController("","Attacco fallito!");
			String risposta=new String("");
			for(String s : risposte){
				risposta=risposta.concat(s+"\n");
			}
			return new RispostaController("",risposta);
		}
		
		return new RispostaController("Mossa non valida",null);
	}
	
	protected boolean controlli(){
		if(giocatore.getPosizione().getCoordinate().equals(settore.getCoordinate())){
			if(giocatore instanceof Alieno){
				if(stato==Stato.PERICOLO || stato==Stato.SICURO){
					System.out.println("1");
					return true;
				}
				System.out.println("2");
				return false;
			}
			else {
				if(stato!=Stato.INIZIO && stato!=Stato.SCIALUPPA && stato!=Stato.CARTASCIALUPPA){
					System.out.println("3");
					return true;
				}
				System.out.println("4");					
				return false;
			}	
		}
		System.out.println("5");
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
