package it.polimi.ingsw.cg_45;

/**Represents the instance of a human player extending {@code Giocatore} class.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Umano extends Giocatore {

	private boolean sedato;
	
	/**Create a new human player setting his position in the Human Sector relative to the game map 
	 * in which the player will play. The method also sets the human's{@code portata} to {@value 1} 
	 * and the {@value boolean} attribute {@code sedato} to {@value false}
	 * 
	 * @param id the unique value that identifies a client for the server  
	 * @param ordine the player's sequence number into the game turn
	 * @param mappa the map in which the player has decided to play
	 * @param nome the nickname chosen by the player
	 */
	public Umano(int id, int ordine, Mappa mappa, String nome) {
		super(id, ordine, nome);
		this.portata=1;
		this.setPosizioneIniziale(mappa);
		this.sedato=false;
	}

	/**Sets the human in the Human Sector according to the map in which he plays.
	 * 
	 * @param mappa the map in which the player plays.
	 */
	@Override
	public void setPosizioneIniziale(Mappa mappa) {
		if(mappa instanceof Fermi)
			posizione=mappa.settori.get(new Coordinate("L10"));
		else if(mappa instanceof Galilei)
			posizione=mappa.settori.get(new Coordinate("L08"));
		else if(mappa instanceof Galvani)
			posizione=mappa.settori.get(new Coordinate("L08"));
	}

	/**Tells if the human player is under sedatives or not.
	 * 
	 * @return {@value false} if the player isn't sedated, {@value true} otherwise. 
	 */
	public boolean isSedato() {
		return sedato;
	}

	/**
	 * 
	 * @param sedato the sedated value to set.  
	 */
	public void setSedato(boolean sedato) {
		this.sedato = sedato;
	}

}
