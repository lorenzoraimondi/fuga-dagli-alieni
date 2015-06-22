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
	
	public String svolgiAzione(int id, String comando) throws RemoteException, IOException;
	public String registrazione(RMIClientInterface client, String comando) throws RemoteException;
}
