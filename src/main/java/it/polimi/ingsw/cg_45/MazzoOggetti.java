package it.polimi.ingsw.cg_45;

import java.util.Collections;

/**Represents the Item Card's deck used to play.
 * 
 * @author Andrea Turconi
 *
 */
public class MazzoOggetti extends Mazzo {
	/**
	 * @param Number of Attack Cards contained in the initial deck.
	 */
	private final static int NUMATTACCO=2;
	/**
	 * @param Number of Teleport Cards contained in the initial deck.
	 */
	private final static int NUMTELETRASPORTO=2;
	/**
	 * @param Number of Sedatives Cards contained in the initial deck.
	 */
	private final static int NUMSEDATIVI=3;
	/**
	 * @param Number of Spotlight Cards contained in the initial deck.
	 */
	private final static int NUMLUCI=2;
	/**
	 * @param Number of Defense Cards contained in the initial deck.
	 */
	private final static int NUMDIFESA=1;
	/**
	 * @param Number of Adrenaline Cards contained in the deck.
	 */
	private final static int NUMADRENALINA=2;
	
	/**Create an Item Card's deck adding the correct number of cards for each card type and shuffles it.
	 * 
	 */
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

	//LO USIAMO???
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "MazzoOggetti [MazzoIniziale=" + mazzoIniziale + "]\n";
	}
	
	@Override
	public Carta pescaCarta() {
		if(mazzoIniziale.isEmpty()){
			if(!mazzoScarti.isEmpty()){
				this.getMazzoIniziale().addAll(this.getMazzoScarti());
				Collections.shuffle(this.getMazzoIniziale());
				this.getMazzoScarti().removeAll(this.getMazzoScarti());
			} else {
				return null;
			}
		}
		return this.getMazzoIniziale().remove(0);
	}

}
