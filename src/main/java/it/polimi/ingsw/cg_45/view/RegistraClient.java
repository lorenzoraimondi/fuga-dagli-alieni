package it.polimi.ingsw.cg_45.view;


import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;

import java.io.IOException;
import java.util.StringTokenizer;

//public class RegistraClient extends Azione {

/**Represents the subscribing action. This class provide the possibility to 
 * add a new client on the server, adding him to the desired waiting list, and setting
 * his attributes. 
 * 
 * @author Lorenzo
 *
 */
public class RegistraClient {
	
	private Server server;
	private Communicator client;
	private int id;

	private String sceltaMappa;
	private String nome;

	/**Create a new subscribing action with the given inputs.
	 * 
	 * @param server the {@code Server} in which connect the new client.
	 * @param client the client's {@code Communicator} to permit communication between server and client.
	 * @param pacchetto {@code PacchettoAzione} object containing clients preferences about map and nickname
	 */
	public RegistraClient(Server server, Communicator client, PacchettoAzione pacchetto){
		this.server=server;
		this.client=client;
		//
		StringTokenizer s=new StringTokenizer(pacchetto.getComando());
		this.sceltaMappa=s.nextToken();
		this.nome=s.nextToken();
		//
	}
	
	/**This method subscribe the client to the server, adding him to the waiting list 
	 * and returning him the confirm, containing his race and id as mentioned in {@link Client}
	 * and notifies the newly added client to the others client connected for the same game.
	 * 
	 * @return
	 * @throws IOException
	 */
	//@Override
	public RispostaController esegui() throws IOException{
	
		int posizione;
		
		synchronized(server){
			posizione=server.getSala().aggiungiGiocatore(sceltaMappa, client, server, nome);
			
			this.id=server.getCounter();
			server.incCounter();
			
		}		
		
		String razza;
		if(posizione%2==0){
			razza=new String("alieno");
		} else razza=new String("umano");

		return new RispostaController(id+"-Iscritto alla sala di attesa.-"+razza,"Si è aggiunto un nuovo giocatore");
	}

	/**
	 * 
	 * @return client's integer identification number.
	 */
	public int getId() {
		return id;
	}

}
