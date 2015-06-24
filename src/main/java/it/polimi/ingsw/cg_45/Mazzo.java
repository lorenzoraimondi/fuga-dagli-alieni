package it.polimi.ingsw.cg_45;
import java.util.ArrayList;
import java.util.List;

/**Represents a generic card deck used to play.
 * 
 * @author Andrea Turconi
 *
 */
public abstract class Mazzo {
		
		/**
		 * @param Initial deck's number of card 
		 */
		protected int numCarte;
		/**
		 * @param Card's deck from which cards can be drawn.
		 */
		List<Carta> mazzoIniziale=new ArrayList<Carta>();
		/**
		 * @param Discarded card's deck, where are put used or discarded cards. 
		 */
		List<Carta> mazzoScarti=new ArrayList<Carta>();
		
		/**
		 * @return the number of cards of a new deck, from which no one has drawn yet. 
		 */
		public int getNumCarte() {
			return numCarte;
		}

		/**
		 * 
		 * @return the card's deck, from which a player can draw.
		 */
		public List<Carta> getMazzoIniziale() {
			return mazzoIniziale;
		}

		/**
		 * 
		 * @return the discarded card's deck. 
		 */
		public List<Carta> getMazzoScarti() {
			return mazzoScarti;
		}
		
		/**Used to draw a card. 
		 * <p>
		 * If the deck is empty and the discarded card deck is not, the method move
		 * discarded cards from the second deck to the first one, shuffles them, and return the first card.
		 * If both the decks are empty means that there are no cards to draw and the game can move one regularly. 
		 * 
		 * @return the card on the deck's top.
		 */
		public abstract Carta pescaCarta();
}
