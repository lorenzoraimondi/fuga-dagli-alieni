package it.polimi.ingsw.cg_45.controller;

import java.io.IOException;



import it.polimi.ingsw.cg_45.view.*;

public class RegistraClient extends Azione {
	Server server;
	Communicator client;
	
	public RegistraClient(Server server, Communicator client){
		this.server=server;
		this.client=client;
	}
	
	@Override
	public RispostaController esegui() throws IOException{
		/*
		BrokerThread brokerThread = new BrokerThread(client);
		server.getSubscribers().add(brokerThread);
		brokerThread.start();
		System.out.println("Added new subscriber");
		
		
		*/
		int id=server.getCounter();
		server.incCounter();
		return new RispostaController(id+"-Iscritto alla sala di attesa.","Si è aggiunto un nuovo giocatore con ID "+id);
	}
	
	/*public void esegui() throws IOException{
		client.send(server.getCounter());
		server.incCounter();
		client.close();
	}*/

	@Override
	protected boolean controlli() {
		// TODO Auto-generated method stub
		return false;
	}
}
