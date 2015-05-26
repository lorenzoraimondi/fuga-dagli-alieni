package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaSettore;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaSettore;

public class PescaSettore extends Azione{

	private CartaSettore carta;
	
	public PescaSettore(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
		this.stato=giocatore.getStato();
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaSettore) model.getMazzoSettori().pescaCarta();
			if(carta.getTipo()==TipoCartaSettore.RUMOREQUALUNQUESETTORE){
				if(!carta.isOggetto())
					giocatore.setStato(Stato.CARTABLUFF);
				else
					giocatore.setStato(Stato.CARTABLUFFOGGETTO);
			}
			else if(carta.getTipo()==TipoCartaSettore.RUMORETUOSETTORE){
				if(!carta.isOggetto())
					giocatore.setStato(Stato.CARTARIVELA);
				else
					giocatore.setStato(Stato.CARTARIVELAOGGETTO);
			}
			else
				giocatore.setStato(Stato.SILENZIO);
		}	
	}

	protected boolean controlli() {
		if(stato==Stato.PERICOLO)
			return true;
		return false;
		
	}
}
