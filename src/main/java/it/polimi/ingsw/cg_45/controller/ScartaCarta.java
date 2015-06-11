package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Carta;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;

public class ScartaCarta extends Azione{
	
	private TipoCartaOggetto tipoCarta;
	private Carta carta;
	
	public ScartaCarta(Giocatore gioc, StatoDiGioco model, TipoCartaOggetto tipoCarta) {
		super(gioc, model);
		this.tipoCarta=tipoCarta;
	}	

	@Override
	public RispostaController esegui() {
		if(controlli()){
			carta=giocatore.getCarta(tipoCarta);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
			return new RispostaController("Carta "+tipoCarta.toString()+" scartata.",giocatore.getNome()+" ha scartato una carta.");
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getCarte().size()>=4){
				return true;
		}
		return false;
	}
}