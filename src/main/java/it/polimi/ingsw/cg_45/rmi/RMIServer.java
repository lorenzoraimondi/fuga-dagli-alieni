package it.polimi.ingsw.cg_45.rmi;

import it.polimi.ingsw.cg_45.*;
import it.polimi.ingsw.cg_45.controller.*;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.netCommons.TraduttoreComandi;
import it.polimi.ingsw.cg_45.view.Messaggio;
import it.polimi.ingsw.cg_45.view.PacchettoAzione;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Represents a RMI server, the system that hosts all the games and 
 * answers to all players requests. It stores all the games and the associations between
 * them and their players.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class RMIServer implements RMIServerInterface,ServerInterface {


	private Map<Integer, StatoDiGioco> Partite = new HashMap<Integer, StatoDiGioco>();
	private Map<Integer, ArrayList<RMIClientInterface>> idSub = new HashMap<Integer, ArrayList<RMIClientInterface>>();
	
	private SalaRMI sala=new SalaRMI();
	
	private int counter=1;
	private int port;
		
	/**Create a game RMI server on the specified port.
	 * 
	 * @param port the port on which open the server.
	 */
	public RMIServer(int port) {
		this.port = port;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCounter() {
		return counter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void incCounter() {
		this.counter++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Integer, StatoDiGioco> getPartite() {
		return Partite;
	}
	
	@Override
	public String svolgiAzione(int id, String comando) throws IOException {
		TraduttoreComandi tc=new TraduttoreComandi(new PacchettoAzione(id,comando),this);
		/*System.out.println("Posizione iniziale: "+partitaProva.getGiocatore(id).getPosizione());
		System.out.println("Stato iniziale: "+partitaProva.getGiocatore(id).getStato());
		System.out.println("Situa iniziale: "+partitaProva.getGiocatore(id).getSituazione());*/
		try{
			Azione a=(Azione)tc.traduci();
			RispostaController risp;
			synchronized(this.getPartite()){
				risp=a.esegui();
			}
			
			
			this.publish(new Messaggio(risp.getMessaggioBroadcast()),id);
			return risp.getMessaggioClient();
		} catch (ClassCastException e) {
			//e.printStackTrace();
			return "Comando non valido";
			
		}
		
		
		
		
		/*System.out.println("Posizione finale: "+partitaProva.getGiocatore(id).getPosizione());
		System.out.println("Stato finale: "+partitaProva.getGiocatore(id).getStato());
		System.out.println("Situa finale: "+partitaProva.getGiocatore(id).getSituazione());*/
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void publish(Messaggio msg,int id) throws RemoteException {
		List<RMIClientInterface> subscribers=idSub.get(id);
		
		if(subscribers!=null){
			System.out.println("Publishing message");
			for (RMIClientInterface sub : subscribers) {
				synchronized(this){
					sub.inviaMessaggio(msg.getMessaggio());	
				}
				
			}
		}else{
			/////
			System.out.println("Non c'è iscritto nessuno...");
			//System.out.println("Devo pubblicare all'id "+id);
			//sala.publish(msg, id);
		
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	public synchronized String registrazione(RMIClientInterface client, String command) throws RemoteException {
		System.out.println("Mi è arrivata un iscrizione per "+command);
		
		int posizione=sala.aggiungiGiocatore(command.split("-")[0], client, this, command.split("-")[1]);
		
		int id=counter;
		this.incCounter();
		//subscribers.add(client);
		
		String razza;
		if(posizione%2==0){
			razza=new String("alieno");
		} else razza=new String("umano");
	
		sala.publish(new Messaggio("Si è iscritto un nuovo giocatore"), id);
		return new String(id+"-Iscritto alla sala di attesa.-"+razza);
	}
	
	/**
	 * 
	 * @return the map that associate every client's id to the relative client's connection interface.
	 */
	public Map<Integer, ArrayList<RMIClientInterface>> getIdSub() {
		return idSub;
	}


}
