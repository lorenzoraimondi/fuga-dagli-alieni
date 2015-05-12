package it.polimi.ingsw.cg_45.progetto;

public class CartaSettore extends Carta{
		
		private TipoCartaSettore tipo;
		private boolean oggetto;
		
		public CartaSettore(TipoCartaSettore tipo, boolean ogg){
			this.tipo=tipo;
			this.oggetto=ogg;
		}

		public boolean isOggetto() {
			return oggetto;
		}

		public TipoCartaSettore getTipo() {
			return tipo;
		}
		
		@Override
		public String toString() {
			return "\nCartaSettore [tipo=" + tipo + ", oggetto=" + oggetto + "]";
		}
		
}
