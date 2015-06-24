package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.netCommons.Messaggio;

import java.io.IOException;

/**A thread that represent the specified connection between server and client, on the client side. In this way
 * the client will be able to receive information from the server not only when directly requesting him something,
 * but everytime the server has the need to do this.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class SubThread extends Thread {
	
	private Communicator sc;
		
	/**Creates a new thread that will be used to retrieve messages from the server. 
	 * 
	 * @param sc the {@code communicator} that provides communications functions between server and clients.
	 */
	public SubThread(Communicator sc){
		this.sc=sc;	
	}
	
	/**This methods continuously accept for received messages and print them.
	 * For avoid a needless CPU load the thread sleeps for 5 ms every receive cycle and then
	 * turns accepting new messages. 
	 * 
	 */
	@Override
	public void run() {
		while(true){
			try {
				receive();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				break;
			}
			
		}
	}
	
	/**This method receive new messages sent by the server and print them on the client.
	 * 
	 * @throws ClassNotFoundException 
	 */
	private void receive() throws ClassNotFoundException {
		Messaggio msg = null;
		try {
			msg = (Messaggio)sc.receiveO();
			if(msg!=null){
				System.out.println(msg.getMessaggio());
			}
		} catch (IOException e) {
			System.out.println("Sei stato disconnesso");
			if(sc.getSocket()!=null)
				sc.close();
				this.interrupt();				
		}
		
	}
	
	/**
	 * Effettua la sottoscrizione al solo ed unico topic, 
	 * i.e., crea la socket verso il subscriber e apre uno stream in ingresso per ricevere
	 * i messaggi del publisher.
	 * NB. Non Ã© necessario creare uno stream in uscita, in ottemperanza al pattern.
	 * @throws UnknownHostException
	 * @throws IOException
	 */	
	private void close(){
		try{
			sc.getSocket().close();
		} catch(IOException e) {
		} 		
	}
}
