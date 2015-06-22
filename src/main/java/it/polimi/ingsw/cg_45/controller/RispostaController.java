package it.polimi.ingsw.cg_45.controller;

import java.io.Serializable;

/**Represents a server response for the client and all the others players
 * that is sent after a client request.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class RispostaController implements Serializable{

	private final String messaggioClient;
	private final String messaggioBroadcast;
	
	/**Create a server response, containing two messages, one only for the requesting client, and
	 * the other for all the players in the game.
	 * 
	 * @param c the message for the requesting client.
	 * @param b the message for all the game's players.
	 */
	public RispostaController(String c, String b){
		this.messaggioClient=c;
		this.messaggioBroadcast=b;
	}

	/**
	 * 
	 * @return the message for the requesting client.
	 */
	public String getMessaggioClient() {
		return messaggioClient;
	}

	/**
	 * 
	 * @return the message for all the game's players.
	 */
	public String getMessaggioBroadcast() {
		return messaggioBroadcast;
	}


}
