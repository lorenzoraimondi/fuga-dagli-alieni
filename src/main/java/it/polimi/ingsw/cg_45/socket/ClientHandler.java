package it.polimi.ingsw.cg_45.socket;

import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;
import it.polimi.ingsw.cg_45.netCommons.TraduttoreComandi;

import java.io.IOException;

/**Represent the server component that manage client's connections
 * and requests in way to satisfy them and give response to the client about
 * his request result. 
 * 
 * @author Lorenzo Raimondi
 *
 */
public class ClientHandler extends Thread {
	
	private Communicator client;
	private SocketServer server;
	private int idClient;
	
	/**Create a handler capable to fulfill clients requests, linked to the proper client and the 
	 * server that hosts the games.
	 * 
	 * @param server the socket {@code Server} that provide services to the client.
	 * @param client the client's {@code Communicator} used to communicate with him. 
	 */
	public ClientHandler(SocketServer server, Communicator client) {
		this.server = server;
		this.client = client;
	}
	
	/**This method starts the handler action: after the receive of a {@code PacchettoAzione} from the
	 * client, the handler obtains the id of the client and the desired command to execute,
	 * than translate this two informations into a concrete action throughout the {@code TraduttoreComandi}
	 * class, execute the brand new action and warn all the players about game updates and changes.
	 * If is the first connection to the server, the handler permit the subscription of the client and
	 * add him to the desired waiting list.
	 * 
	 */
	@Override
	public void run(){
	
		PacchettoAzione pacchetto = null;
		RispostaController risposta = null;

		try {
			pacchetto=(PacchettoAzione)client.receiveO();
			idClient=pacchetto.getId();
			} catch (ClassNotFoundException e) {
				return;
			} catch (IOException e) {
				return;
			}       
				
			if(idClient==0){
				try {
					RegistraClient registrazione=new RegistraClient(server,client,pacchetto);
					risposta=registrazione.esegui();
					idClient=registrazione.getId();
					
					client.send(new Messaggio(risposta.getMessaggioClient()));
					client.getSocket().shutdownInput();
					server.getSala().publish(new Messaggio(risposta.getMessaggioBroadcast()),idClient);
					
					return;
					
				} catch (IOException e) {
					return;
				} catch (NullPointerException e){
					return;
				}
					
			} else {
				TraduttoreComandi tc=new TraduttoreComandi(pacchetto,server);
		        try{
					Azione azione=(Azione)tc.traduci();			
						
					synchronized(server.getPartite()){
					risposta=azione.esegui();
					}
		        
					
					client.send(new Messaggio(risposta.getMessaggioClient()));
					if(risposta.getMessaggioBroadcast()!=null)
					server.publish(new Messaggio(risposta.getMessaggioBroadcast()),idClient);
					
					
					if(azione instanceof TerminaTurno ){
						synchronized(server){
							server.getTimers().get(idClient).interrupt();
							server.startTimer(server.getPartite().get(idClient), server.getPartite().get(idClient).getGiocatori().get(0));	
						}	
					}
		        } catch (ClassCastException e){
		        	try {
		        		client.send(new Messaggio("Comando non valido"));
		        		return;
		        	} catch (IOException e1) {
		        		return;
		        	}
		        } catch (IOException e1) {
		        	return;
		        }
			}
            
    }
	
}


