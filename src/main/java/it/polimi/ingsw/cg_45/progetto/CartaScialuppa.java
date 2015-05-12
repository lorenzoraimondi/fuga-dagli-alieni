package it.polimi.ingsw.cg_45.progetto;

public class CartaScialuppa extends Carta{
		private TipoCartaScialuppa tipo;
		
		public CartaScialuppa(TipoCartaScialuppa tipo){
			this.tipo=tipo;
		}

		public TipoCartaScialuppa getTipo() {
			return tipo;
		}

		@Override
		public String toString() {
			return "\nCartaScialuppa [tipo=" + tipo + "]";
		}
}
