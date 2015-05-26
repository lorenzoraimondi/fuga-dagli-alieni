package it.polimi.ingsw.cg_45;

import java.util.*;


public abstract class Giocatore {
			protected int id;
			protected int ordine;
			protected int portata;
			protected Settore posizione;
			//Aggiunto stato con getter/setter
			protected Stato stato;
			protected Mappa mappa;
			protected Situazione situazione;
			protected List<CartaOggetto> carte = new ArrayList<CartaOggetto>();
			//protected CartaOggetto[] oggetti= new CartaOggetto[3];
			
			protected abstract void setPosizioneIniziale(Mappa mappa);
			
			public Giocatore(int id, int ordine){
				this.id=id;
				this.ordine=ordine;
				this.situazione=Situazione.ATTIVONASCOSTO;
				this.stato=Stato.INIZIO;
			}
			
			public Giocatore(int id, int ordine, Mappa mappa){
				this.id=id;
				this.ordine=ordine;
				this.situazione=Situazione.ATTIVONASCOSTO;
				this.stato=Stato.INIZIO;
			}
			
			public Situazione getSituazione() {
				return situazione;
			}

			public void setSituazione(Situazione situazione) {
				this.situazione = situazione;
			}


			public Stato getStato() {
				return stato;
			}

			public void setStato(Stato stato) {
				this.stato = stato;
			}
			
			public List<CartaOggetto> getCarte() {
				return carte;
			}
			
			//Metodo utile quando bisogna spostare una carta dalla mano del giocatore
			//al mazzo scarti
			public CartaOggetto getCarta(TipoCartaOggetto tipo){
				for(CartaOggetto c: carte){
					if(c.getTipo()==tipo){
						return c;
					}
				}
				return null;
			}
			
			//Utile per aggiungere direttamente la carta senza dover far get
			
			public void setCarta(CartaOggetto c){
				carte.add(c);
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
			
			public Settore getPosizione() {
				return posizione;
			}
			
			public void setPosizione(Settore posizione) {
				this.posizione = posizione;
			}
			
			/*public CartaOggetto[] getOggetti() {
				return oggetti;
			}
			
			public void setOggetti(CartaOggetto[] oggetti) {
				this.oggetti = oggetti;
			}*/
			
			
}
