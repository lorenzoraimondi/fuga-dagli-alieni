package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

public class UsaSedativi extends Azione{

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	public UsaSedativi(Giocatore g, StatoDiGioco p){
		super(g,p);
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
	}
	
	@Override
	public RispostaController esegui(){
		if(controlli()){
			Umano g=(Umano)giocatore;
			g.setSedato(true);
			carta=giocatore.getCarta(TipoCartaOggetto.SEDATIVI);
			if(giocatore.getStato()==Stato.PERICOLO)
				giocatore.setStato(Stato.SICURO);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			return new RispostaController("Hai usato la carta Sedativi",giocatore.getNome()+"ha usato una carta Sedativi");
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.SEDATIVI))){
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
