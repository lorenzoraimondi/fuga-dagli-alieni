package it.polimi.ingsw.cg_45.rmi;

import java.rmi.RemoteException;

/**Represents the implementation of the relative interface, setting up
 * all the methods that a client will offer to the server throughout the 
 * remote methods invocation. 
 * 
 * @author Lorenzo Raimondi
 *
 */
public class RMIClient implements RMIClientInterface {

	private int id=0;
	
	/*public RMIClient(String string, int i) {
		// TODO Auto-generated constructor stub
	}*/
	
	/**Creates a new RMI Client so it can be exported to the server and get's its class remote methods used.
	 * 
	 */
	public RMIClient() {
	}
	

	/**Grant to the server the possibility to send messages to the client. Calling this method
	 * from the server, the client will receive the message and will display it. 
	 * 
	 * @param messaggio the message String to send to the client.
	 */
	@Override
	public void inviaMessaggio(String messaggio) throws RemoteException{
		if(messaggio!=null)
			
			System.out.println(messaggio);
		
	}
	
	/**
	 * 
	 * @return client's integer identification number.
	 */
	public int getId() {
		return id;
		
	}

	/**From server's confirm of client's subscription is obtained the unique id number
	 * that will identify the client. It is the first characters sequence in the received
	 * String, directly followed by a "-". For example this method can receive the message
	 * <i>13-Iscritto alla partita come alieno</i> and will set 13 as client's id.
	 * 
	 * @param msg the confirm String for client's subscription.
	 */
	public void setId(String msg) {
		String a=msg.split("-")[0];
		int numeroId=Integer.parseInt(a);
		this.id=numeroId;
	}
	
}
