package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Fermi;
import it.polimi.ingsw.cg_45.Galvani;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.netCommons.Accettazione;
import it.polimi.ingsw.cg_45.netCommons.Sala;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**Represents a room where are stored socket-connected player's subscriptions and where are
 * created new games when the requirements are satisfied.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class SalaSocket extends Sala{
	
	private Communicator client;

	/**Creates the room for RMI clients, by creating three waiting lists of {@code Accettazione} objects,
	 * one for each map.
	 * 
	 */
	public SalaSocket(){
		giocatoriFermi=new ArrayList<Accettazione>();
		giocatoriGalilei=new ArrayList<Accettazione>();
		giocatoriGalvani=new ArrayList<Accettazione>();
		
	}
	
	/**This method adds a player in the waiting list relative to his map choice. After this
	 * is verified if there are the requirements to create a new game or to start the countdown for the 
	 * game's creation. If possible, the game gets created. 
	 * 
	 * @param scelta the chosen map's name.
	 * @param client the communication interface representing the client, used to communicate with him.
	 * @param server the server that hosts the games.
	 * @param nome player's nickname.
	 * @return player's position in the waiting list.
	 */
	public int aggiungiGiocatore(String scelta,Communicator client, Server server, String nome){
		this.scelta=scelta;
		this.server=server;
		this.nomeGiocatore=nome;
		this.client=client;
		
		return this.esegui();
	}
	
	@Override
	protected int esegui(){
		BrokerThread brokerThread = new BrokerThread(client);
		brokerThread.start();
		//
		int posizione=0;
		//
		//server.getSubscribers().add(brokerThread);
		//System.out.println("added new subscribers");
		if(scelta.contentEquals("fermi")){
			//
			AccettazioneSocket accettazione=new AccettazioneSocket(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriFermi.add(accettazione);
			//giocatoriFermi.add(new Accettazione(brokerThread,server.getCounter()));
			//
			posizione=giocatoriFermi.indexOf(accettazione);
			//
			if(giocatoriFermi.size()==2){
				fermi=new Timer();
				System.out.println("creo timerFermi");
				fermi.schedule(new CreaPartitaSocket(giocatoriFermi,server,scelta,this),(long)seconds*1000);
				}
			if(giocatoriFermi.size()==8){
				//Da sistemare???
				fermi.schedule(new CreaPartitaSocket(giocatoriFermi,server,scelta,this),0);
				
			}
		}
		else if(scelta.contentEquals("galilei")){
			AccettazioneSocket accettazione=new AccettazioneSocket(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriGalilei.add(accettazione);
			posizione=giocatoriGalilei.indexOf(accettazione);
			if(giocatoriGalilei.size()==2){
				galilei=new Timer();
				System.out.println("creo timerGalilei");
				galilei.schedule(new CreaPartitaSocket(giocatoriGalilei,server,scelta,this),(long)seconds*1000);}
			if(giocatoriGalilei.size()==8){
				galilei.schedule(new CreaPartitaSocket(giocatoriGalilei,server,scelta,this),0);
			}
		}
		else if(scelta.contentEquals("galvani")){
			AccettazioneSocket accettazione=new AccettazioneSocket(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriGalvani.add(accettazione);
			posizione=giocatoriGalvani.indexOf(accettazione);
			if(giocatoriGalvani.size()==2){
				galvani=new Timer();
				System.out.println("creo timerGalvani");
				galvani.schedule(new CreaPartitaSocket(giocatoriGalvani,server,scelta,this),(long)seconds*1000);}
			if(giocatoriGalvani.size()==8){
				galvani.schedule(new CreaPartitaSocket(giocatoriGalvani,server,scelta,this),0);
			}
		}
		else
			System.out.println("scelta Sbagliata");
		
		return posizione;
	}

	/**Sends a message to all the players waiting for playing in a specified waiting list.
	 * From the {@code id} of one player is found the list in which the message must 
	 * be sent.
	 * 
	 * @param msg the message to send.
	 * @param id the id number of the player subscribed in the desired waiting list.  
	 */
	@Override
	public void publish(Messaggio msg, int id){
		
		List<BrokerThread> threadSubsFermi=new ArrayList<BrokerThread>();
		List<BrokerThread> threadSubsGalvani=new ArrayList<BrokerThread>();
		List<BrokerThread> threadSubsGalilei=new ArrayList<BrokerThread>();
		
		int s=0;
				
		for(Accettazione a : giocatoriFermi){
			if(a.getId()==id){
				s=1;
				for(Accettazione a2: giocatoriFermi){
					threadSubsFermi.add(((AccettazioneSocket) a2).getbt());
				}
			}
		}
		
		for(Accettazione a : giocatoriGalvani){
			
			if(a.getId()==id){
				s=2;
				for(Accettazione a2: giocatoriGalvani){
					threadSubsGalvani.add(((AccettazioneSocket) a2).getbt());
				}
			}
		}
		
		for(Accettazione a : giocatoriGalilei){
				threadSubsGalilei.add(((AccettazioneSocket) a).getbt());
		}
		if(s==0){
			for (BrokerThread sub : threadSubsGalilei) {
				sub.dispatchMessage(msg);
			}
		}
		else if(s==1){
			for (BrokerThread sub : threadSubsFermi) {
				sub.dispatchMessage(msg);
			}
		}
		else
			for(BrokerThread sub : threadSubsGalvani){
				sub.dispatchMessage(msg);
			}
			
		}

	

	
}
