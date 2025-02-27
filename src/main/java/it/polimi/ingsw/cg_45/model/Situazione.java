package it.polimi.ingsw.cg_45.model;

/**Represents the different status that a player can assume during the game.  
 * <p>
 * A player is {@code ATTIVO} during his turn, and {@code INATTIVO} during other player's turns; 
 * {@code MORTO} if the player has been killed by someone else; {@code DISCONNESSO} if the player lost connection 
 * with server or if he didn't play during his turn; {@code VINTO} if a player has won the game.
 *  
 * @author Andrea Turconi 
 *
 */
public enum Situazione {
	/**
	 * If the player is the playing one. 
	 */
	ATTIVO,
	/**
	 * If the player isn't the playing one.
	 */
	INATTIVO,
	/**
	 * If the player has been killed.
	 */
	MORTO,
	/**
	 * If the player has disconnected from the server or if has been kicked out from it.
	 */
	DISCONNESSO,
	/**
	 * If the player has won the game.
	 */
	VINTO;
}
