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
	
	@Override
	public RispostaController esegui(){
		if(this.controlli()){
			carta=(CartaSettore) model.getMazzoSettori().pescaCarta();
			if(carta.getTipo()==TipoCartaSettore.RUMOREQUALUNQUESETTORE){
				if(!carta.isOggetto()){
					giocatore.setStato(Stato.CARTABLUFF);
					return new RispostaController("Hai pescato una carta 'Rumore in qualunque settore'. Annuncia il settore.",giocatore.getNome()+"ha pescato una carta settore");
				}
				else
					giocatore.setStato(Stato.CARTABLUFFOGGETTO);
				return new RispostaController("Hai pescato una carta 'Rumore in qualunque settore' con oggetto. Annuncia il settore e pesca una carta.",giocatore.getNome()+"ha pescato una carta settore");
			}
			else if(carta.getTipo()==TipoCartaSettore.RUMORETUOSETTORE){
				if(!carta.isOggetto()){
					giocatore.setStato(Stato.CARTARIVELA);
				return new RispostaController("Hai pescato una carta 'Rumore nel tuo settore'. Annuncia il tuo settore.",giocatore.getNome()+"ha pescato una carta settore");
				}
				else
					giocatore.setStato(Stato.CARTARIVELAOGGETTO);
				return new RispostaController("Hai pescato una carta 'Rumore nel tuo settore' con oggetto. Annuncia il tuo settore e pesca una carta.",giocatore.getNome()+"ha pescato una carta settore");
			}
			else
				giocatore.setStato(Stato.SILENZIO);
				return new RispostaController("Hai pescato una carta 'Silenzio'. Annuncia il silenzio.",giocatore.getNome()+"ha pescato una carta settore");
		}
		return new RispostaController("Mossa non valida",null);	
	}

	@Override
	protected boolean controlli() {
		if(stato==Stato.PERICOLO)
			return true;
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
