package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
//progetto
public class BrokerThread extends Thread {
	/*La nuova socket, verso uno specifico subscriber, creata dalla ServerSocket*/
	//private Socket socket;
	private Communicator client;
	/* Abbiamo soltanto bisogno di recapitare il messaggio. 
	 * Il pattern non prevede la ricezione, da parte del publisher, di alcun messaggio (
	 * se non la richiesta di sottoscrizione che tuttavia viene catturata dalla accept nella ServerSocket)*/
	//private PrintWriter out;
	//private ObjectOutputStream out;
	//private BufferedReader in;
	
	//Una coda che contiene i messaggi, specifici per ciascun subscriber
	//private ConcurrentLinkedQueue<String> buffer;
	private ConcurrentLinkedQueue<Messaggio> buffer;
	
	/**
	 * Quando un client esterno si sottoscrive viene creato un nuovo thread
	 * che rappresenterá la specifica connessione allo specifico client/subscriber. 
	 * @param socket La nuova socket, verso uno specifico subscriber, creata dalla ServerSocket
	 */
	/*public BrokerThread(Socket socket) {
		this.socket = socket;
		//buffer = new ConcurrentLinkedQueue<String>();
		buffer = new ConcurrentLinkedQueue<Messaggio>();
		
		try{
			this.out = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}*/
	
	public BrokerThread(Communicator client) {
		this.client = client;
		//buffer = new ConcurrentLinkedQueue<String>();
		buffer = new ConcurrentLinkedQueue<Messaggio>();
		
		/*try{
			this.out = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Questo metodo contiene, di fatto, la logica della comunicazione dal publisher
	 * allo specifico subscriber. 
	 */
	@Override
	public void run() {
		while(true){
			//Si prova ad estrarre un messaggio dalla coda...
			//String msg = buffer.poll();
			Messaggio msg = buffer.poll();
			//... se c'é lo si invia
			if(msg != null)
				try {
					send(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else{
				//... altrimenti, per evitare cicli inutili di CPU
				//che possono portare ad utilizzarla inutilmente...
				try {
					//... si aspetta fin quando la coda non conterrá qualcosa
					//é necessario sincronizzarsi sull'oggetto monitorato, in modo tale
					//che il thread corrente possieda il monitor sull'oggetto.
					synchronized(buffer){
						buffer.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Questo metodo inserisce un messaggio nella coda
	 * e notifica ai thread in attesa (in questo caso a se stesso) la presenza di un messaggio.
	 * @param msg Il messaggio da inserire.
	 */
	public void dispatchMessage(Messaggio msg){
		buffer.add(msg);
		//é necessario sincronizzarsi sull'oggetto monitorato
		synchronized(buffer){
			buffer.notify();
		}
	}
	
	/**
	 * Questo metodo invia il messaggio al subscriber tramite la rete
	 * @param msg
	 * @throws IOException 
	 */
	/*private void send(String msg){
		out.println(msg);
		out.flush();
	}*/
	
	/*private void send(Messaggio msg) throws IOException{
		out.writeObject(msg);
		//out.flush();
	}*/
	
	private void send(Messaggio msg) throws IOException{
		client.send(msg);
		//out.flush();
	}
	
	/*public void close(){
		try {
			socket.close();
		} catch (IOException e) {
		} finally {
			out = null;
			socket = null;
			System.gc();
		}
	}*/
	
	public void close(){
		
			client.close();
		
			//out = null;
			client = null;
			System.gc();
		
	}

}
