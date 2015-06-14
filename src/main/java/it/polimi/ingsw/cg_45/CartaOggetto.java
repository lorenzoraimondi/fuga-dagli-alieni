package it.polimi.ingsw.cg_45;

public class CartaOggetto extends Carta{

		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

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

		private TipoCartaOggetto tipo;
		
		public CartaOggetto(TipoCartaOggetto tipo){
			this.tipo=tipo;
		}

		public TipoCartaOggetto getTipo() {
			return tipo;
		}
		
		@Override
		public String toString() {
			return " +tipo+ ";
		}
}
