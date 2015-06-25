package it.polimi.ingsw.cg_45.socket;

import java.io.IOException;
import java.net.Socket;

/**This interface establishes the communication methods that must be implemented
 * for grant a socket net communication between client and server.
 * 
 * @author Lorenzo Raimondi
 *
 */
public interface Communicator {
	
	/**Closes the net communication by closing the {@code Socket}. After this call
	 * will not be possible to send or receive objects from the relative client.
	 * 
	 */
	public void close();
	
	/**This method send a Serializable object on the net to the relative client}. 
	 * 
	 * @param msg the {@code Object} message to send.
	 * @throws IOException
	 */
	public void send(Object msg) throws IOException;
	
	/**This method receive a Serializable object sent on the net, from the relative client.
	 * 
	 * @return the {@code Object} received from the net.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object receiveO() throws IOException, ClassNotFoundException;
	/**
	 * 
	 * @return the {@code Socket} used for net communication.
	 */
	public Socket getSocket();
	
}
