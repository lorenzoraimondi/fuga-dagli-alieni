package it.polimi.ingsw.cg_45.rmi;

import it.polimi.ingsw.cg_45.netCommons.Accettazione;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.Sala;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**Represents a room where are stored RMI-connected player's subscriptions and where are
 * created new games when the requirements are satisfied.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class SalaRMI extends Sala {
	
	private RMIClientInterface client;
	
	/**Creates the room for RMI clients, by creating three waiting lists of {@code Accettazione} objects,
	 * one for each map.
	 * 
	 */
	public SalaRMI(){
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
	public int aggiungiGiocatore(String scelta,RMIClientInterface client, ServerInterface server, String nome){
		this.scelta=scelta;
		this.client=client;
		this.server=server;
		this.nomeGiocatore=nome;
		return this.esegui();
	}
	
	@Override
	protected int esegui(){
		
		//
		int posizione=0;
		//
		//server.getSubscribers().add(brokerThread);
		//System.out.println("added new subscribers");
		if(scelta.contentEquals("fermi")){
			//
			AccettazioneRMI accettazione=new AccettazioneRMI(client,server.getCounter(),nomeGiocatore);
			giocatoriFermi.add(accettazione);
			//giocatoriFermi.add(new Accettazione(brokerThread,server.getCounter()));
			//
			posizione=giocatoriFermi.indexOf(accettazione);
			//
			if(giocatoriFermi.size()==2){
				fermi=new Timer();
				System.out.println("creo timerFermi");
				fermi.schedule(new CreaPartitaRMI(giocatoriFermi,server,scelta,this),(long)seconds*1000);
				}
			if(giocatoriFermi.size()==8){
				//Da sistemare???
				fermi.schedule(new CreaPartitaRMI(giocatoriFermi,server,scelta,this),0);
				
			}
		}
		else if(scelta.contentEquals("galilei")){
			AccettazioneRMI accettazione=new AccettazioneRMI(client,server.getCounter(),nomeGiocatore);
			giocatoriGalilei.add(accettazione);
			posizione=giocatoriGalilei.indexOf(accettazione);
			if(giocatoriGalilei.size()==2){
				galilei=new Timer();
				System.out.println("creo timerGalilei");
				galilei.schedule(new CreaPartitaRMI(giocatoriGalilei,server,scelta,this),(long)seconds*1000);}
			if(giocatoriGalilei.size()==8){
				galilei.schedule(new CreaPartitaRMI(giocatoriGalilei,server,scelta,this),0);
			}
		}
		else if(scelta.contentEquals("galvani")){
			AccettazioneRMI accettazione=new AccettazioneRMI(client,server.getCounter(),nomeGiocatore);
			giocatoriGalvani.add(accettazione);
			posizione=giocatoriGalvani.indexOf(accettazione);
			if(giocatoriGalvani.size()==2){
				galvani=new Timer();
				System.out.println("creo timerGalvani");
				galvani.schedule(new CreaPartitaRMI(giocatoriGalvani,server,scelta,this),(long)seconds*1000);}
			if(giocatoriGalvani.size()==8){
				galvani.schedule(new CreaPartitaRMI(giocatoriGalvani,server,scelta,this),0);
			}
		}
		else
			System.out.println("scelta Sbagliata");
		
		return posizione;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void publish(Messaggio msg, int id) throws RemoteException{
	
		List<RMIClientInterface> threadSubsFermi=new ArrayList<RMIClientInterface>();
		List<RMIClientInterface> threadSubsGalvani=new ArrayList<RMIClientInterface>();
		List<RMIClientInterface> threadSubsGalilei=new ArrayList<RMIClientInterface>();
		
		int s=0;
		
		for(Accettazione a : giocatoriFermi){
			if(a.getId()==id){
				s=1;
				for(Accettazione a2: giocatoriFermi){
					threadSubsFermi.add(((AccettazioneRMI) a2).getClient());
				}
			}
		}
		
		for(Accettazione a : giocatoriGalvani){
			
			if(a.getId()==id){
				s=2;
				for(Accettazione a2: giocatoriGalvani){
					threadSubsGalvani.add(((AccettazioneRMI) a2).getClient());
				}
			}
		}
		
		System.out.println(threadSubsFermi);
		System.out.println(threadSubsGalvani);
		System.out.println(threadSubsGalilei);
		
		for(Accettazione a : giocatoriGalilei){
				threadSubsGalilei.add(((AccettazioneRMI) a).getClient());
		}
		if(s==0){
			for (RMIClientInterface sub : threadSubsGalilei) {
				sub.inviaMessaggio(msg.getMessaggio());
			}
		}
		else if(s==1){
			for (RMIClientInterface sub : threadSubsFermi) {
				sub.inviaMessaggio(msg.getMessaggio());
			}
		}
		else
			for(RMIClientInterface sub : threadSubsGalvani){
				sub.inviaMessaggio(msg.getMessaggio());
			}
			
		}


	
	
}
