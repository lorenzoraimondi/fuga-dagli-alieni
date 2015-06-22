package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.Fermi;
import it.polimi.ingsw.cg_45.Galvani;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.view.Messaggio;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;

/**Represents a generic room where are stored player's subscriptions and where are
 * created new games when the requirements are satisfied.
 * 
 * @author Lorenzo Raimondi
 *
 */
public abstract class Sala {
	
	protected String scelta;
	
	protected ServerInterface server;
	//
	protected String nomeGiocatore;
	//
	protected int seconds=60;
	
	protected List<Accettazione> giocatoriFermi;
	protected List<Accettazione> giocatoriGalilei;
	protected List<Accettazione> giocatoriGalvani;
	
	protected Timer fermi;
	protected Timer galilei;
	protected Timer galvani;
	
	protected abstract int esegui();

	/**Creating a new game, this methods delete the players from the
	 * waiting list, in way to accept requests from new clients.
	 * 
	 * @param mappa the map in which the players will play during this new game. 
	 */
	public void svuotaLista(String mappa){
		if(mappa.contentEquals("fermi"))
			giocatoriFermi.clear();
		else if(mappa.contentEquals("galilei"))
			giocatoriGalilei.clear();
		else
			giocatoriGalvani.clear();
	}
	
	/**Creating a new game, the timers bounded to its map is deleted. It will be restored
	 * when at least two new clients request to play in the same map.  
	 * 
	 * @param mappa the map in which the players will play during this new game.
	 */
	public void cancelloTimer(String mappa){
		if(mappa.contentEquals("fermi"))
			fermi.cancel();
		else if(mappa.contentEquals("galvani"))
			galvani.cancel();
		else
			galilei.cancel();
	}
	
	/**
	 * 
	 * @param mappa the map for which obtain the list of waiting players.
	 * @return the lists of player waiting for playing in the desired map.
	 */
	public List<Accettazione> getListaAccettazione(Mappa mappa) {
		if(mappa instanceof Fermi)
			return giocatoriFermi;
		if(mappa instanceof Galvani)
			return giocatoriGalvani;
		return giocatoriGalilei;
		
	}

	/**Sends a message to all the players waiting for playing in a specified waiting list.
	 * From the {@code id} of one player is found the list in which the message must 
	 * be sent.
	 * 
	 * @param msg the message to send.
	 * @param id the id number of the player subscribed in the desired waiting list.  
	 * @throws RemoteException
	 */
	public abstract void publish(Messaggio msg, int id) throws RemoteException;
	
	
		
	
}
