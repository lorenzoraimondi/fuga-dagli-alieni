package it.polimi.ingsw.cg_45.rmi;

import it.polimi.ingsw.cg_45.netCommons.Accettazione;

/**Represents a marker that store a RMI-connected client's subscription to a game in
 * a specific map by getting his {@code id} and {@code nome}.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class AccettazioneRMI extends Accettazione {

	private RMIClientInterface client;
	
	/**Create a new subscription class for a RMI-connected client, in way to store
	 * him and his information and then use them for create a game's player. 
	 * 
	 * @param client the communication interface representing the client, used to communicate with him. 
	 * @param id player's unique identification number.
	 * @param nomeGiocatore player's nickname.
	 */
	public AccettazioneRMI(RMIClientInterface client, Integer id, String nomeGiocatore){
		this.client=client;
		this.id=id;
		this.nomeGiocatore=nomeGiocatore;
	}
	
	/**
	 * 
	 * @return the communication interface representing the client linked to this subscription, 
	 * used to communicate with him. 
	 */
	public RMIClientInterface getClient(){
		return client;
	}
}
