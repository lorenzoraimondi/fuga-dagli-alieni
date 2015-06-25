package it.polimi.ingsw.cg_45;

import java.util.Collections;

/**Represents the Escape Hatch Card's deck used to play.
 * 
 * @author Andrea Turconi
 *
 */
public class MazzoScialuppe extends Mazzo {
	
		/**
		 * Number of cards contained in the deck.
		 */
		private static final int NUM=6;
		/**
		 * Number of Green Escape Cards contained in the deck. The green cards permit the player to escape
		 * the spaceship. 
		 */
		private static final int NUMVERDE=3;
		
		/**Create an Escape Hatch Card Deck adding the correct number of cards for each colors and shuffles it.
		 * 
		 */
		public MazzoScialuppe(){
			numCarte=NUM;
			for(int i=NUM;i>0;i--){
				if(i>NUMVERDE){
					mazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.VERDE));
				}
				else
					mazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.ROSSA));
			}
			Collections.shuffle(mazzoIniziale);
		}

		/**Prints all the decks, with the cards that compose it at the moment.
		 * 
		 */
		@Override
		public String toString() {
			return "MazzoScialuppe [MazzoIniziale=" + mazzoIniziale + "]\n";
		}
		
		/**Used to draw an Escape Hatch card. 
		 * <p>
		 * This method simply give to the player the first card of the deck. Because there are only four
		 * Escape Hatch Sectors per map, there is no shuffling system, most four cards will be drawn and so there's
		 * no need to recreate the initial deck. 
		 * 
		 * @return the card on the deck's top.
		 */
		@Override
		public Carta pescaCarta() {
			Carta pescata;
			pescata=this.mazzoIniziale.remove(0);
			this.mazzoScarti.add(pescata);
			return pescata;		
		}
		
		
}
