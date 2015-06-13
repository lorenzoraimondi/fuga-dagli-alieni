package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Alieno;
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
import java.util.ArrayList;
import java.util.List;

public class Attacco extends Azione{
	
	private Settore settore;
	Carta carta;
	
	
	public Attacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		this.settore=s;
	}
	
	@Override
	public RispostaController esegui() throws IOException{
		//IOEx aggiunta per terminaPartita
		List<String> risposte=new ArrayList<String>();
		
		if(controlli()){
			String rispostaCarta=new String();
			if(giocatore instanceof Umano){
				carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
				giocatore.getCarte().remove(carta);
				model.getMazzoOggetti().getMazzoScarti().add(carta);
				rispostaCarta=new String(giocatore.getNome()+" usa carta Attacco\n");
			}
			for(Giocatore g : model.getGiocatori()){
				if(g!=giocatore && g.getPosizione()==settore){
					if(g instanceof Umano && (g.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)))){
						new UsaDifesa(g,model).esegui();
						risposte.add(g.getNome()+" ha usato la carta Difesa");
						//return new RispostaController("Attacco fallito!","Il giocatore "+g.getID()+" ha usato la carta Difesa");
					} else if(g.getSituazione()!=Situazione.MORTO){
						g.setSituazione(Situazione.MORTO);
						g.setStato(Stato.TURNOTERMINATO);
						if(g instanceof Umano && giocatore instanceof Alieno){
							giocatore.setPortata(3);
						}
						if(!g.getCarte().isEmpty()){
							model.getMazzoOggetti().getMazzoScarti().addAll(g.getCarte());
							g.getCarte().removeAll(g.getCarte());							
						}
						risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", è morto!");
						//return new RispostaController("Attacco riuscito!","Il giocatore "+g.getID()+","+g.getClass().getName()+", è morto!");
					}
					}
			}
			giocatore.setStato(Stato.ATTACCATO);
			
			if(risposte.isEmpty())
				return new RispostaController(null,rispostaCarta+"Attacco in "+settore.getCoordinate().toString()+" fallito!");
			String risposta=new String(rispostaCarta+"Attacco in "+settore.getCoordinate().toString()+"\n");
			for(String s : risposte){
				risposta=risposta.concat(s+"\n");
			}
			//Giocatore inutile, riesco a overrideare costruttore?
			RispostaController terminaPartita=new TerminaPartita(giocatore,model).esegui();
			if(terminaPartita!=null){
				return new RispostaController("",risposta+terminaPartita.getMessaggioBroadcast());
			}
			return new RispostaController("",risposta);
		}
		
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getPosizione().getCoordinate().equals(settore.getCoordinate()) && (stato==Stato.PERICOLO || stato==Stato.SICURO) && giocatore.getSituazione()==Situazione.ATTIVO){
					if(giocatore instanceof Alieno){
						return true;
					} else {
						if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
							return true;
						} 
					}
				}
				return false;
			/*}
			else {
				if(stato!=Stato.INIZIO && stato!=Stato.SCIALUPPA && stato!=Stato.CARTASCIALUPPA){
					
					return true;
				}
									
				return false;
			}	*/	
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
