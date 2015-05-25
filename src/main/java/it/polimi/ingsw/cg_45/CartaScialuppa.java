package it.polimi.ingsw.cg_45;

public class CartaScialuppa extends Carta{
		private TipoCartaScialuppa tipo;
		private boolean isBlocked;
		
		public CartaScialuppa(TipoCartaScialuppa tipo){
			this.tipo=tipo;
			this.isBlocked=false;
		}

		public boolean isBlocked() {
			return isBlocked;
		}
		
		public void blocca(){
			this.isBlocked=true;
		}
		
		public TipoCartaScialuppa getTipo() {
			return tipo;
		}

		@Override
		public String toString() {
			return "\nCartaScialuppa [tipo=" + tipo + "]";
		}
}
