package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;
import it.polimi.ingsw.cg_45.netCommons.TraduttoreComandi;

import java.io.IOException;

//
public class ClientHandler extends Thread {
	
	private Communicator client;
	private Server server;
	private int idClient;
	
	public ClientHandler(Server server, Communicator client) {
		this.server = server;
		this.client = client;
	}
	
	@Override
	public void run(){
	
		PacchettoAzione pacchetto = null;
		RispostaController risposta = null;

		try {
			pacchetto=(PacchettoAzione)client.receiveO();
			idClient=pacchetto.getId();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
				
			if(idClient==0){
				RegistraClient registrazione=new RegistraClient(server,client,pacchetto);
				try {
					risposta=registrazione.esegui();
					idClient=registrazione.getId();
					
					client.send(new Messaggio(risposta.getMessaggioClient()));
					//
					client.getSocket().shutdownInput();
					//
					server.getSala().publish(new Messaggio(risposta.getMessaggioBroadcast()),idClient);
					
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			} else {
				TraduttoreComandi tc=new TraduttoreComandi(pacchetto,server);
		        try{
					Azione azione=(Azione)tc.traduci();			
						
					synchronized(server.getPartite()){
					risposta=azione.esegui();
					}
		        
					try{
						client.send(new Messaggio(risposta.getMessaggioClient()));
						if(risposta.getMessaggioBroadcast()!=null)
							server.publish(new Messaggio(risposta.getMessaggioBroadcast()),idClient);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(azione instanceof TerminaTurno ){
						synchronized(server){
							server.getTimers().get(idClient).setFlag();
							server.startTimer(server.getPartite().get(idClient), server.getPartite().get(idClient).getGiocatori().get(0));	
						}
						
					}
		        	} catch (ClassCastException e){
		        		try {
		        			client.send(new Messaggio("Comando non valido"));
		        			//	e.printStackTrace();
		        			return;
		        		} catch (IOException e1) {
		        			// TODO Auto-generated catch block
		        			e1.printStackTrace();
		        		}
		        	} catch (IOException e1) {
		        		// TODO Auto-generated catch block
		        		e1.printStackTrace();
		        	}
			}
            
    }
	
}


