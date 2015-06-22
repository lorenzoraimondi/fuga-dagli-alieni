package it.polimi.ingsw.cg_45;

import java.util.Collections;

/**Represents the Dangerous Sector Cards' deck used to play.
 * 
 * @author Andrea Turconi
 *
 */
public class MazzoSettori extends Mazzo {
		
		/**
		 * @param Number of <i>Noise in your Sector</i> cards contained in the initial deck. 
		 */
		private final static int NUMRUMORESETTORE=10;
		
		/**
		 * @param Number of <i>Noise in any Sector</i> cards contained in the initial deck. 
		 */
		private final static int NUMRUMOREQUALUNQUE=10;
		
		/**
		 * @param Number of <i>Silence</i> cards contained in the initial deck. 
		 */
		private final static int NUMSILENZIO=5;
		
		/**
		 * @param Number of <i>Noise in your Sector</i> cards with Item contained in the initial deck. 
		 */
		private final static int NUMSETTOGG=4;
		
		/**
		 * @param Number of <i>Noise in any Sector</i> cards with Item contained in the initial deck. 
		 */
		private final static int NUMQUALOGG=4;
		
		/**Create a Dangerous Sector Card's deck adding the correct number of cards for each card type and shuffles it.
		 * 
		 */
		public MazzoSettori(){
			numCarte=0;
			for(int i=NUMRUMORESETTORE;i>0;i--,numCarte++){
				if(i<NUMSETTOGG){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,true));
				}
				else
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,false));
			}
			for(int i=NUMRUMOREQUALUNQUE;i>0;i--,numCarte++){
				if(i<NUMQUALOGG){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,true));
				}
				else
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,false));
			}
			for(int i=NUMSILENZIO;i>0;i--,numCarte++){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.SILENZIO,false));
			}
			Collections.shuffle(mazzoIniziale);
		}

		//LO USIAMO?
		/**
		 * 
		 */
		@Override
		public String toString() {
			return "MazzoSettori [ MazzoIniziale=" + mazzoIniziale + "]\n";
		}

		/**Used to draw a Dangerous Sector card. 
		 * <p>
		 * If the deck is empty, the method move discarded cards from the second deck to the first one, 
		 * shuffles them, and return the first card. 
		 * 
		 * @return the card on the deck's top.
		 */
		@Override
		public Carta pescaCarta() {
			if(mazzoIniziale.isEmpty()){
				if(!mazzoScarti.isEmpty()){
					this.getMazzoIniziale().addAll(this.getMazzoScarti());
					Collections.shuffle(this.getMazzoIniziale());
					this.getMazzoScarti().removeAll(this.getMazzoScarti());
				}
			}
			Carta pescata;
			pescata=this.mazzoIniziale.remove(0);
			this.mazzoScarti.add(pescata);
			return pescata;		
		}
		
		
		
}
