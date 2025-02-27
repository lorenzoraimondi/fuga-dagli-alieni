package it.polimi.ingsw.cg_45.model;

/**Represents the instance of an alien player extending {@code Giocatore} class.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Alieno extends Giocatore {
				
		/**Create a new alien player setting his position in the Alien Sector relative to the game map 
		 * in which the player will play. The method also sets the alien's{@code portata} to {@value 2} 
		 * and the {@value boolean} attribute {@code haUcciso} to {@value false}
 		 * 
		 * @param id the unique value that identifies a client for the server  
		 * @param ordine the player's sequence number into the game turn
		 * @param mappa the map in which the player has decided to play
		 * @param nome the nickname chosen by the player
		 */
		public Alieno(int id, int ordine, Mappa mappa, String nome) {
			super(id, ordine, nome);
			this.portata=2;
			this.setPosizioneIniziale(mappa);
		}

		/**Sets the alien in the Alien Sector according to the map in which he plays.
		 * 
		 * @param mappa the map in which the player plays.
		 */
		@Override
		public void setPosizioneIniziale(Mappa mappa) {
			if(mappa instanceof Fermi)	
				posizione=mappa.settori.get(new Coordinate("L09"));
			else if(mappa instanceof Galilei)
				posizione=mappa.settori.get(new Coordinate("L06"));
			else if(mappa instanceof Galvani)
				posizione=mappa.settori.get(new Coordinate("L06"));
		}
}
