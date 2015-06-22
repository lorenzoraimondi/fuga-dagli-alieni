package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.net.UnknownHostException;

public class SubThread extends Thread {
	//private Socket subSocket;
	private Communicator sc;
	//private ObjectInputStream in;
	//private final String address = "localhost";
	//private final int port = 1337;

	
	/**
	 * Non appena il thread viene instanziato, ci si sottoscrive al broker.
	 * NB. Non Ã© stato implementato il concetto di topic; questo viene lasciato come 
	 * compito agli studenti.
	 * @param id L'id assegnato manualmente al thread.
	 */
	/*public SubThread(Socket subSocket){
		
		this.subSocket=subSocket;
		
		try {
			in = new ObjectInputStream(subSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public SubThread(Communicator sc){
		
		this.sc=sc;
		
	}
	
	/**
	 * Dopo aver effettuato la sottoscrizione, questo metodo
	 * rimane in ascolto di messaggi da parte del publisher.
	 */
	@Override
	public void run() {
		while(true){
			try {
				receive();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				//aspetta 5ms per ridurre i cicli di clock
				//soprattutto nel caso in cui il publisher vada in crash
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Metodo che riceve eventuali messaggi di testo dal publisher
	 * @return
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
			e.printStackTrace();
		}
		
	}
	
	/*
	private Messaggio receive() throws ClassNotFoundException {
		Messaggio msg = null;
		try {
			msg = (Messaggio)in.readObject();
			if(msg!=null){
				System.out.println("Received message: "+msg.getMessaggio());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}*/
	
	/**
	 * Effettua la sottoscrizione al solo ed unico topic, 
	 * i.e., crea la socket verso il subscriber e apre uno stream in ingresso per ricevere
	 * i messaggi del publisher.
	 * NB. Non Ã© necessario creare uno stream in uscita, in ottemperanza al pattern.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	/*private void close(){
		try{
			sc.close();
		}catch(Exception e){
		} finally {
			//in=null;
			sc=null;
			//System.gc();
		}
		
	}*/
	
	/*private void close(){
		try{
			subSocket.close();
		}catch(Exception e){
		} finally {
			in=null;
			subSocket=null;
			System.gc();
		}
		
	}*/
}
