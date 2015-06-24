package it.polimi.ingsw.cg_45;

/**Represent a Dangerous Sector Card, with its own type and optional Item icon.
 * 
 * @author Andrea Turconi
 *
 */
public class CartaSettore implements Carta{
		
		private TipoCartaSettore tipo;
		private boolean oggetto;
		
		/**Create a new Dangerous Sector card of the specified type and Item icon.
		 * 
		 * @param tipo the type of which will be created the card.
		 * @param ogg a boolean value that specifies if there is the Item icon or not.
		 */
		public CartaSettore(TipoCartaSettore tipo, boolean ogg){
			this.tipo=tipo;
			this.oggetto=ogg;
		}

		/**
		 * 
		 * @return {@code true} if the card has the Item icon, {@code false} otherwise
		 */
		public boolean isOggetto() {
			return oggetto;
		}

		/**
		 * 
		 * @return the type of this Dangerous Sector Card.
		 */
		public TipoCartaSettore getTipo() {
			return tipo;
		}
		
		/**Prints the Escape Hatch card's type in the form <i>"CartaSettore [tipo= TYPE , oggetto= isOggetto ]"</i>.
		 * <p>
		 * For example, a <i>Noise in any sector</i> card with the Item icon will be printed as 
		 * <i>CartaScialuppa [tipo= RUMOREQUALUNQUESETTORE , oggetto= false ]</i>.
		 */
		@Override
		public String toString() {
			return "\nCartaSettore [tipo=" + tipo + ", oggetto=" + oggetto + "]";
		}
		
}
