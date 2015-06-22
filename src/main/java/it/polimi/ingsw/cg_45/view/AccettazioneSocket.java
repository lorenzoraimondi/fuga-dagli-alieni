package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.netCommons.Accettazione;

/**Represents a marker that store a socket-connected client's  subscription to a game in
 * a specific map by getting his {@code id} and {@code nome}.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class AccettazioneSocket extends Accettazione{

	private BrokerThread bt;
	
	/**Create a new subscription class for a socket-connected client, in way to store
	 * him and his information and then use them for create a game's player. 
	 * 
	 * @param client the communication interface representing the client, used to communicate with him. 
	 * @param id player's unique identification number.
	 * @param nomeGiocatore player's nickname.
	 */
	public AccettazioneSocket(BrokerThread bt, Integer id, String nomeGiocatore){
		this.bt=bt;
		this.id=id;
		this.nomeGiocatore=nomeGiocatore;
	}
	
	/**
	 * 
	 * @return the object representing the client linked to this subscription, used to communicate with him. 
	 */
	public BrokerThread getbt(){
		return bt;
	}
}
