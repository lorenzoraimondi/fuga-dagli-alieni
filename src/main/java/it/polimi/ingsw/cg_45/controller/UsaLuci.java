package it.polimi.ingsw.cg_45.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;

public class UsaLuci extends Azione {
	
	private Settore settore;
	private Settore[] vicini;
	
	
	public UsaLuci(StatoDiGioco p,Giocatore g,Settore s){
		super(g,p);
		this.settore=s;		
	}
	
	@Override
	public RispostaController esegui(){
		if(controlli()){
			
			List<String> risposte=new ArrayList<String>();
			vicini=model.getMappa().getMappa().get(settore.getCoordinate()).getVicini();
			List<Settore> viciniList=new ArrayList<Settore>(Arrays.asList(vicini));
			viciniList.add(settore);
			
			for(Giocatore g :  model.getGiocatori()){
				System.out.println("Giocatore "+g.getID());
				for(Settore s : viciniList){
					if(g.getPosizione()==s && g!=giocatore){
						System.out.println("Settore "+s.getCoordinate().toString());
						risposte.add("Il giocatore "+g.getID()+" si trova nel settore "+g.getPosizione().getCoordinate().toString());
					}	
				}
			}
		
			if(risposte.isEmpty())
				return new RispostaController("","Nessun giocatore presente nelle vicinanze");
			String risposta=new String("");
			for(String s : risposte){
				risposta=risposta.concat(s+"\n");
			}
			return new RispostaController("",risposta);
			
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.LUCI))){
			return true;
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
