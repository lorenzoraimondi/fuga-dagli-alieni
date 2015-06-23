package it.polimi.ingsw.cg_45.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**Contains the methods that a RMI server must implement and 
 * provide to the client.
 * 
 * @author Lorenzo Raimondi
 *
 */
public interface RMIServerInterface extends Remote {
	
	/**Grant to the client the possibility to execute actions. Calling this method
	 * from the client, the server will receive client's command and will create the 
	 * concrete action to execute. Then the results are published to all players and
	 * a response directly sent to the requesting client.
	 * 
	 * @param id requesting client's integer id.
	 * @param comando the command String input.
	 * @return the String response to the client for the desired action. 
	 * @throws RemoteException
	 * @throws IOException
	 */
	public String svolgiAzione(int id, String comando) throws RemoteException, IOException;
	
	/**Grant to the client the possibility to subscribe to the server. Calling this method
	 * from the client, the server will receive client's subscription request and will add the
	 * player to the desired map's waiting list. The subscription event is notified to all the players
	 * in the waiting list, instead to the requesting client is sent a confirm,
	 * containing his personal id as also his character
	 * race in the form <i>id-Iscritto alla sala di attesa.-race</i>.
	 * 
	 * @param client client's RMIClientInterface from which call client's methods.
	 * @param comando client's String command, in the form <i>map's name-player's nickname</i>.
	 * @return the server confirm for the client.
	 * @throws RemoteException
	 */
	public String registrazione(RMIClientInterface client, String comando) throws RemoteException;
}
