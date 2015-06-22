package it.polimi.ingsw.cg_45;

import java.util.ArrayList;
import java.util.List;

/**Represents the instance of an generic player.
 * 
 * @author Lorenzo Raimondi
 *
 */
public abstract class Giocatore {
			protected int id;
			protected int ordine;
			protected int portata;
			protected String nome;
			protected Settore posizione;
			
			protected Stato stato;
			protected Mappa mappa;
	
			protected Situazione situazione;
			protected List<CartaOggetto> carte = new ArrayList<CartaOggetto>();
			
			/**Create a new player by setting his {@code id} and {@code ordine}. This method
			 * also assigns player's state attributes, setting him active and at the beginning of his turn.
			 * 
			 * @param id the unique value that identifies a client for the server
			 * @param ordine the player's sequence number into the game turn
			 */
			/*public Giocatore(int id, int ordine){
				this.id=id;
				this.ordine=ordine;
				this.situazione=Situazione.ATTIVO;
				this.stato=Stato.INIZIO;
			}*/
			
			/**Create a new player by setting his {@code id} and {@code ordine}. This method
			 * also assigns player's state attributes, setting him active and at the beginning of his turn.
			 * 
			 * @param id the unique value that identifies a client for the server
			 * @param ordine the player's sequence number into the game turn
			 * @param mappa the map in which the player has decided to play
			 * @param nome the nickname chosen by the player
			 */
			public Giocatore(int id, int ordine, String nome){
			//Mappa inutile?
			//public Giocatore(int id, int ordine, Mappa mappa, String nome){
				this.id=id;
				this.ordine=ordine;
				this.nome=nome;
				this.situazione=Situazione.ATTIVO;
				this.stato=Stato.INIZIO;
			}
			
			protected abstract void setPosizioneIniziale(Mappa mappa);
			
			/**
			 * 
			 * @return player's state during the game. 
			 */
			public Situazione getSituazione() {
				return situazione;
			}

			/**
			 * 
			 * @param situazione player's game state to set.
			 */
			public void setSituazione(Situazione situazione) {
				this.situazione = situazione;
			}

			/**
			 * 
			 * @return player's state regard his turn.
			 */
			public Stato getStato() {
				return stato;
			}

			/**
			 * 
			 * @param stato player's turn state to set.
			 */
			public void setStato(Stato stato) {
				this.stato = stato;
			}
			
			/**
			 * 
			 * @return the cards gathered by the player during the game.
			 */
			public List<CartaOggetto> getCarte() {
				return carte;
			}
			
			//Metodo utile quando bisogna spostare una carta dalla mano del giocatore
			//al mazzo scarti
			/**
			 * 
			 * @param tipo the type of the seeked card
			 * @return a card of the seeked type from the player's hand
			 */
			public CartaOggetto getCarta(TipoCartaOggetto tipo){
				for(CartaOggetto c: carte){
					if(c.getTipo()==tipo){
						return c;
					}
				}
				return null;
			}
			
			//Utile per aggiungere direttamente la carta senza dover far get
			
			/**
			 * 
			 * @param c the card to add in the player's hand.
			 */
			public void setCarta(CartaOggetto c){
				carte.add(c);
			}
			
			/**
			 * 
			 * @return the unique value that identifies this player for the server
			 */
			public int getID() {
				return id;
			}
			
			/**
			 * 
			 * @return the player's nickname
			 */
			public String getNome() {
				return nome;
			}

			/**
			 * 
			 * @return the player's sequence number into the game turn
			 */
			public int getOrdine() {
				return ordine;
			}
			
			/**
			 * 
			 * @return how many sectors can the player's movement cover. 
			 */
			public int getPortata() {
				return portata;
			}
			
			/**
			 * 
			 * @param portata the number of sectors that the player's movement can cover.
			 */
			public void setPortata(int portata) {
				this.portata = portata;
			}
			
			/**
			 * 
			 * @param ordine the player's sequence number into the game turn to set.
			 */
			public void setOrdine(int ordine) {
				this.ordine = ordine;
			}
			
			/**
			 * 
			 * @return the player's current position in the game map.
			 */
			public Settore getPosizione() {
				return posizione;
			}
			
			/**
			 * 
			 * @param posizione player's new position to set.
			 */
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
