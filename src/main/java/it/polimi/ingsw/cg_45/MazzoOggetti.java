package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoOggetti extends Mazzo {
	private final static int NUMATTACCO=2;
	private final static int NUMTELETRASPORTO=2;
	private final static int NUMSEDATIVI=3;
	private final static int NUMLUCI=2;
	private final static int NUMDIFESA=1;
	private final static int NUMADRENALINA=2;
	
	public MazzoOggetti() {
		numCarte=0;
		for(int i=NUMATTACCO;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		}
		for(int i=NUMTELETRASPORTO;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO));
		}
		for(int i=NUMSEDATIVI;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		}
		for(int i=NUMLUCI;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.LUCI));
		}
		for(int i=NUMDIFESA;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.DIFESA));
		}
		for(int i=NUMADRENALINA;i>0;i--,numCarte++){
			mazzoIniziale.add(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		}
		Collections.shuffle(mazzoIniziale);
	}

	@Override
	public String toString() {
		return "MazzoOggetti [MazzoIniziale=" + mazzoIniziale + "]\n";
	}
	
	

}
