package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Coordinate;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

public class AnnunciaRumore extends Azione{
	String coordinateSettore;
	
	public AnnunciaRumore(StatoDiGioco p, Giocatore g, String cs){
		super(g,p);
		this.coordinateSettore=cs;
	}
	
	@Override
	public RispostaController esegui() throws IOException {
		if(controlli()){
		switch(giocatore.getStato()){
		case SILENZIO:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Silenzio nell'astronave.");
		case CARTABLUFFOGGETTO:
			giocatore.setStato(Stato.BLUFFATO);
			System.out.println(giocatore.getStato());
			return new RispostaController("Pesca un oggetto","Rumore nel settore "+coordinateSettore+".");
		case CARTABLUFF:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Rumore nel settore "+coordinateSettore+".");
		case CARTARIVELAOGGETTO:
			giocatore.setStato(Stato.RIVELATO);
			System.out.println(giocatore.getStato());
			return new RispostaController("Pesca un oggetto","Rumore nel settore "+coordinateSettore+".");
		case CARTARIVELA:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Rumore nel settore "+coordinateSettore+".");
		default:
			break;
		
		}
		}
		return new RispostaController("Mossa non valida",null);
		
	}

	@Override
	protected boolean controlli() {
			if(giocatore.getStato()==Stato.CARTABLUFF || giocatore.getStato()==Stato.CARTABLUFFOGGETTO || giocatore.getStato()==Stato.SILENZIO)
				return true;
			else if(giocatore.getStato()==Stato.CARTARIVELA || giocatore.getStato()==Stato.CARTARIVELAOGGETTO){
				if(giocatore.getPosizione().getCoordinate().equals(new Coordinate(coordinateSettore))){
					return true;
				}
				return false;
			}
			return false;	
	}

}
