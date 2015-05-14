package it.polimi.ingsw.cg_45;


public abstract class Giocatore {
			protected int id;
			protected int ordine;
			protected int portata;
			//protected Settore posizione;//
			protected CartaOggetto[] oggetti= new CartaOggetto[3];
			
			public Giocatore(int id, int ordine){
				this.id=id;
				this.ordine=ordine;
			}
			public int getID() {
				return id;
			}
			
			public int getOrdine() {
				return ordine;
			}
			
			public int getPortata() {
				return portata;
			}
			public void setPortata(int portata) {
				this.portata = portata;
			}
			
			/*public Settore getPosizione() {
				return posizione;
			}
			
			public void setPosizione(Settore posizione) {
				this.posizione = posizione;
			}*/
			
			public CartaOggetto[] getOggetti() {
				return oggetti;
			}
			
			public void setOggetti(CartaOggetto[] oggetti) {
				this.oggetti = oggetti;
			}
			
			
}
