package it.polimi.ingsw.cg_45;

public class Alieno extends Giocatore {
		
		private boolean haUcciso;
		
		/*public Alieno(int id, int ordine){
			super(id,ordine);
			this.portata=2;
			this.haUcciso=false;
			//this.setPosizioneIniziale(mappa);
			
		}*/
		public Alieno(int id, int ordine, Mappa mappa, String nome) {
			super(id, ordine, mappa, nome);
			this.portata=2;
			this.haUcciso=false;
			this.setPosizioneIniziale(mappa);
		}

		public boolean isHaUcciso() {
			return haUcciso;
		}

		public void setHaUcciso(boolean ucciso) {
			this.haUcciso = ucciso;
		}

		@Override
		protected void setPosizioneIniziale(Mappa mappa) {
			if(mappa instanceof Fermi)	
				posizione=mappa.mappa.get(new Coordinate("L09"));
			else if(mappa instanceof Galilei)
				posizione=mappa.mappa.get(new Coordinate("L06"));
			else if(mappa instanceof Galvani)
				posizione=mappa.mappa.get(new Coordinate("L06"));
		}
}
