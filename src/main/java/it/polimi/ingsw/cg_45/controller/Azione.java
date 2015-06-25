package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

/**Represent a generic action performing, for all player's action during the game.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public abstract class Azione {

	protected Giocatore giocatore;
	protected StatoDiGioco model;
	protected Stato stato;
	
	/**Create the action to perform, associating it to a game and to a player, and storing
	 * his turn status.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 */
	public Azione(Giocatore giocatore,StatoDiGioco model){
		this.giocatore=giocatore;
		this.model=model;
		this.stato=giocatore.getStato();
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the action 
	 * making the relatives updates to the game and communicating what happened
	 * to the players.
	 * 
	 * @return the {@code RispostaController} object with the responses for the players.
	 * @throws IOException
	 */
	public abstract RispostaController esegui() throws IOException;
	
	protected abstract boolean controlli();
}
