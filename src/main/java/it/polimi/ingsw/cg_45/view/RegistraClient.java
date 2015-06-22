package it.polimi.ingsw.cg_45.view;

//import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;

import java.io.IOException;
import java.util.StringTokenizer;

//public class RegistraClient extends Azione {
public class RegistraClient {
	private Server server;
	private Communicator client;
	private int id;
	//
	private String sceltaMappa;
	private String nome;
	//
	
	public RegistraClient(Server server, Communicator client, PacchettoAzione pacchetto){
		this.server=server;
		this.client=client;
		//
		StringTokenizer s=new StringTokenizer(pacchetto.getComando());
		this.sceltaMappa=s.nextToken();
		this.nome=s.nextToken();
		//
	}
	
	/*
	public RegistraClient(Server server, Communicator client, String scelta, String nome){
		this.server=server;
		this.client=client;
		//
		this.sceltaMappa=scelta;
		this.nome=nome;
		//
	}
	*/
	//@Override
	public RispostaController esegui() throws IOException{
		/*
		BrokerThread brokerThread = new BrokerThread(client);
		server.getSubscribers().add(brokerThread);
		brokerThread.start();
		System.out.println("Added new subscriber");
		
		
		*/
		int posizione;
		
		synchronized(server){
			posizione=server.getSala().aggiungiGiocatore(sceltaMappa, client, server, nome);
			
			this.id=server.getCounter();
			server.incCounter();
			//Queste tre istruzioni vanno sicuramente sincronizzate
		}
		
		
		
		String razza;
		if(posizione%2==0){
			razza=new String("alieno");
		} else razza=new String("umano");
		//return new RispostaController(id+"-Iscritto alla sala di attesa.","Si è aggiunto un nuovo giocatore con ID "+id);
		return new RispostaController(id+"-Iscritto alla sala di attesa.-"+razza,"Si è aggiunto un nuovo giocatore");
	}
	
	/*public void esegui() throws IOException{
		client.send(server.getCounter());
		server.incCounter();
		client.close();
	}*/

	public int getId() {
		return id;
	}

	/*@Override
	protected boolean controlli() {
		// TODO Auto-generated method stub
		return false;
	}*/
}
