package it.polimi.ingsw.cg_45.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**Contains the methods that a RMI-connected client must implement and 
 * provide to the server.
 * 
 * @author Lorenzo Raimondi
 *
 */
public interface RMIClientInterface extends Remote {
	
	/**Send a string message to the client
	 * 
	 * @param messaggio the message to send.
	 * @throws RemoteException
	 */
	public void inviaMessaggio(String messaggio) throws RemoteException;
}
