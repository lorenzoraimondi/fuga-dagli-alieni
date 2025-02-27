package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;

import java.rmi.RemoteException;
import java.util.Map;

/**Represents a generic server, the system that hosts all the games and 
 * answers to all players requests. 
 * 
 * @author Lorenzo Raimondi
 *
 */
public interface ServerInterface {

	/**
	 * 
	 * @return the map containing all the current games.
	 */
	public Map<Integer, StatoDiGioco> getPartite();
	
	/**
	 * 
	 * @return the progressive number to be assigned to a new client connected.
	 */
	public int getCounter();
	
	/**Increments the progressive id number counter in way to assign it to the next client connected.
	 * 
	 */
	public void incCounter();

	/**Send a message to all the players involved in a certain game. Given the id of the player from
	 * which an action starts, is found the relative game and the message published to all of its
	 * players.
	 * 
	 * @param messaggio the message to send.
	 * @param id the id number of the player from which the action starts.
	 * @throws RemoteException
	 */
	public void publish(Messaggio messaggio, int id) throws RemoteException;

	/**This method create, saves and starts a {@code Timer}. It's created by the relative game and player
	 * attributes and in this way stored in the timers map. Then it's started to count turn duration.
	 * 
	 * @param partita the {@code StatoDiGioco} game relative to the timer.
	 * @param giocatore the current player of which count turn time.
	 */
	public void startTimer(StatoDiGioco partita, Giocatore giocatore);
	
	
}
