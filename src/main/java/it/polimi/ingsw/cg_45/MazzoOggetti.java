package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoOggetti extends Mazzo {
	private final int numAttacco=2;
	private final int numTeletrasporto=2;
	private final int numSedativi=3;
	private final int numLuci=2;
	private final int numDifesa=1;
	private final int numAdrenalina=2;
	
	public MazzoOggetti() {
		for(int i=numAttacco;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		}
		for(int i=numTeletrasporto;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO));
		}
		for(int i=numSedativi;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		}
		for(int i=numLuci;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.LUCI));
		}
		for(int i=numDifesa;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.DIFESA));
		}
		for(int i=numAdrenalina;i>0;i--){
			MazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		}
		Collections.shuffle(MazzoIniziale);
		numCarte=12;
	}

	@Override
	public String toString() {
		return "MazzoOggetti [MazzoIniziale=" + MazzoIniziale + "]\n";
	}

}
