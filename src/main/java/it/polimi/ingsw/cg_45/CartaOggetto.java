package it.polimi.ingsw.cg_45;

public class CartaOggetto extends Carta{

		private TipoCartaOggetto tipo;
		
		public CartaOggetto(TipoCartaOggetto tipo){
			this.tipo=tipo;
		}

		public TipoCartaOggetto getTipo() {
			return tipo;
		}
		
		@Override
		public String toString() {
			return "\nCartaOggetto [tipo=" + tipo + "]";
		}
}
