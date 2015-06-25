package it.polimi.ingsw.cg_45.rmi;

import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.netCommons.Timer;
import it.polimi.ingsw.cg_45.netCommons.TraduttoreComandi;

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


	private Map<Integer, StatoDiGioco> partite = new HashMap<Integer, StatoDiGioco>();
	private Map<Integer, ArrayList<RMIClientInterface>> idSub = new HashMap<Integer, ArrayList<RMIClientInterface>>();
	
	private SalaRMI sala=new SalaRMI();
	
	private int counter=1;
	
	
	/**Create a game RMI server on the specified port.
	 * 
	 * @param port the port on which open the server.
	 */
	public RMIServer() {
	
	}
	
	private Map<Integer, Thread> timers=new HashMap<Integer, Thread>();
		
	/**The map stores a {@code Timer} for each game. It's possible to get the correct
	 * timer by a game player's id.
	 *   
	 * @return the {@code Map} of the associations between {@code id} and {@code Timer} 
	 */
	public Map<Integer, Thread> getRmiTimers(){
			return timers;
		}
	
	@Override
	public void startTimer(StatoDiGioco partita,Giocatore giocatore){
		Timer timerTurno=new RMITimer(partita,giocatore,this);
		Thread t=new Thread((Runnable) timerTurno);
		timers.put(giocatore.getID(), t);
		t.start();
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
		return partite;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String svolgiAzione(int id, String comando) throws IOException {
		TraduttoreComandi tc=new TraduttoreComandi(new PacchettoAzione(id,comando),this);
		
		try{
			Azione a=(Azione)tc.traduci();
			RispostaController risp;
			synchronized(this.getPartite()){
				risp=a.esegui();
			}
			try{
				this.publish(new Messaggio(risp.getMessaggioBroadcast()),id);	
			} catch (RemoteException e){
			}
			
			if(a instanceof TerminaTurno){
				this.getRmiTimers().get(id).interrupt();
				if(this.getPartite().containsKey(id))
					this.startTimer(this.getPartite().get(id), this.getPartite().get(id).getGiocatori().get(0));
			}
			return risp.getMessaggioClient();
		} catch (ClassCastException e) {
			return "Comando non valido";
			
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void publish(Messaggio msg,int id) throws RemoteException {
		List<RMIClientInterface> subscribers=idSub.get(id);
		
		if(subscribers!=null){
			for (RMIClientInterface sub : subscribers) {
				synchronized(this){
					sub.inviaMessaggio(msg.getMessaggio());	
				}
				
			}
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized String registrazione(RMIClientInterface client, String command) throws RemoteException {
		
		int posizione=sala.aggiungiGiocatore(command.split("-")[0], client, this, command.split("-")[1]);
		
		int id=counter;
		this.incCounter();
		
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
