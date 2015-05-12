package it.polimi.ingsw.cg_45;

public class Alieno extends Giocatore {
		
		private boolean haUcciso;
		
		public Alieno(int id, int ordine) {
			super(id, ordine);
			this.portata=2;
			this.haUcciso=false;
		}

		public boolean isHaUcciso() {
			return haUcciso;
		}

		public void setHaUcciso(boolean Ucciso) {
			this.haUcciso = Ucciso;
		}
}
