package it.polimi.ingsw.cg_45;

/**Represent an Item Card, with its own color.
 * 
 * @author Andrea Turconi
 *
 */
public class CartaScialuppa implements Carta{
	
		private TipoCartaScialuppa tipo;
		
		/**Create a new Escape Hatch card of the specified type.
		 * 
		 * @param tipo the type of which will be created the card, {@code red} or {@code green}.
		 */
		public CartaScialuppa(TipoCartaScialuppa tipo){
			this.tipo=tipo;
		}
		
		/**
		 * 
		 * @return the type of this Escape Hatch Card.
		 */
		public TipoCartaScialuppa getTipo() {
			return tipo;
		}

		/**Prints the Escape Hatch card's type in the form <i>"CartaScialuppa [tipo= TYPE ]"</i>.
		 * <p>
		 * For example, a red card will be printed as <i>CartaScialuppa [tipo= ROSSA ]</i>.
		 * 
		 * 
		 */
		@Override
		public String toString() {
			return "\nCartaScialuppa [tipo=" + tipo + "]";
		}
}
