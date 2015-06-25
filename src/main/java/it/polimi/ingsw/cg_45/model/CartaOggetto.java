package it.polimi.ingsw.cg_45.model;

/**Represent an Item Card, with its own type.
 * 
 * @author Andrea Turconi
 *
 */
public class CartaOggetto implements Carta{
	
	private TipoCartaOggetto tipo;
	
	/**Create a new Item card of the specified type.
	 * 
	 * @param tipo the type of which will be created the card.
	 */
	public CartaOggetto(TipoCartaOggetto tipo){
		this.tipo=tipo;
	}

	/**
	 * 
	 * @return the type of this Item Card.
	 */
	public TipoCartaOggetto getTipo() {
		return tipo;
	}
	
	/**Prints the Item card's type in the form <i>" type "</i>.
	 * <p>
	 * For example, an Adrenaline card will be printed as <i>" ADRENALINE "</i>.
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return " "+tipo+" ";
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaOggetto other = (CartaOggetto) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}
		
		
		
		}
