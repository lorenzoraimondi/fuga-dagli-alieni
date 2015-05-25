package it.polimi.ingsw.cg_45;

public class Alieno extends Giocatore {
		
		private boolean haUcciso;
		
		public Alieno(int id, int ordine){
			super(id,ordine);
			this.setPosizioneIniziale();
			
		}
		public Alieno(int id, int ordine, Mappa mappa) {
			super(id, ordine, mappa);
			this.portata=2;
			this.haUcciso=false;
			this.setPosizioneIniziale();
		}

		public boolean isHaUcciso() {
			return haUcciso;
		}

		public void setHaUcciso(boolean ucciso) {
			this.haUcciso = ucciso;
		}

		@Override
		protected void setPosizioneIniziale() {
			if(mappa instanceof Fermi)	
				posizione=mappa.mappa.get(new Coordinate("L09"));
			else if(mappa instanceof Galilei)
				posizione=mappa.mappa.get(new Coordinate("L06"));
			else if(mappa instanceof Galvani)
				posizione=mappa.mappa.get(new Coordinate("L06"));
		}
}
