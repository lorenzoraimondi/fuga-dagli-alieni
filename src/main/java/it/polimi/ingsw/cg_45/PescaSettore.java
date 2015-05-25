package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class PescaSettore extends Azione{

	private CartaSettore carta;
	
	public PescaSettore(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
		this.stato=giocatore.getStato();
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaSettore) model.getMazzoSettori().mazzoIniziale.get(0);
			model.getMazzoSettori().mazzoScarti.add(model.getMazzoSettori().mazzoIniziale.remove(0));
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
		if(stato==Stato.PERICOLO){
			if(model.getMazzoSettori().mazzoIniziale.isEmpty()){
			model.getMazzoSettori().mazzoIniziale.addAll(model.getMazzoSettori().mazzoScarti);
			Collections.shuffle(model.getMazzoSettori().mazzoIniziale);
			model.getMazzoSettori().mazzoScarti.removeAll(model.getMazzoSettori().mazzoScarti);
			}
		}
		return true;
		
		
	}
}
