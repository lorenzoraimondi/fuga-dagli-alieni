package it.polimi.ingsw.cg_45.socket;

import it.polimi.ingsw.cg_45.netCommons.Messaggio;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**A thread that represent the specified connection between server and client, on the server side. In this way
 * the server will be able to contact a client not only when directly asked from him,
 * granting a one-way communication system starting from the server.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class BrokerThread extends Thread {

	private Communicator client;
	

	private Queue<Messaggio> buffer;
	
	/**Creates a new thread that will be used to contact the specified client in way to
	 * send him messages.
	 * 
	 * @param client the client's {@code Communicator} used to reach the client.
	 */
	public BrokerThread(Communicator client) {
		this.client = client;
		buffer = new ConcurrentLinkedQueue<Messaggio>();
	}
	
	/**This method permit a communication system between server and client.
	 * it continuously accept new incoming messages, that will directly be sent
	 * to the client. For avoid a needless CPU load the thread waits over a 
	 * message queue, and will be wakened up when there is a new incoming
	 * message to send.  
	 *  
	 */
	@Override
	public void run() {
		while(true){
			Messaggio msg = buffer.poll();
			if(msg != null)
				try {
					send(msg);
				} catch (IOException e1) {
				}
			else{
				try {
					synchronized(buffer){
						buffer.wait();
					}
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	
	/**Add a new message to the send queue and notifies the thread of its presence.
	 * 
	 * @param msg the message to send to the client.
	 */
	public void dispatchMessage(Messaggio msg){
		buffer.add(msg);
		synchronized(buffer){
			buffer.notify();
		}
	}
	
	/**This method sends the message from the server to the client.
	 * 
	 * @param msg the message to be sent to client.
	 * @throws IOException 
	 */
	private void send(Messaggio msg) throws IOException{
		client.send(msg);
	}
	
	/**Closes the communicator in way to block communication between the server
	 * and the communicator relative client. After this method call
	 * the client attribute is {@code null}.
	 * 
	 */
	public void close(){
			client.close();
			client = null;
	}

}
