package it.polimi.ingsw.cg_45.model;

import java.util.Collections;

/**Represents the Item Card's deck used to play.
 * 
 * @author Andrea Turconi
 *
 */
public class MazzoOggetti extends Mazzo {
	/**
	 * Number of Attack Cards contained in the initial deck.
	 */
	private static final int NUMATTACCO=2;
	/**
	 * Number of Teleport Cards contained in the initial deck.
	 */
	private static final int NUMTELETRASPORTO=2;
	/**
	 * Number of Sedatives Cards contained in the initial deck.
	 */
	private static final int NUMSEDATIVI=3;
	/**
	 * Number of Spotlight Cards contained in the initial deck.
	 */
	private static final int NUMLUCI=2;
	/**
	 * Number of Defense Cards contained in the initial deck.
	 */
	private static final int NUMDIFESA=1;
	/**
	 * Number of Adrenaline Cards contained in the deck.
	 */
	private static final int NUMADRENALINA=2;
	
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "MazzoOggetti [MazzoIniziale=" + mazzoIniziale + "]\n";
	}
	
	/**
	 * {@inheritDoc}
	 */
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
