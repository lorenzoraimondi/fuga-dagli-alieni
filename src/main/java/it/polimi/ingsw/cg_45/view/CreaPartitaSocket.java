package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.Fermi;
import it.polimi.ingsw.cg_45.Galilei;
import it.polimi.ingsw.cg_45.Galvani;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;
import it.polimi.ingsw.cg_45.netCommons.Accettazione;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

/**Represents the game creator item for socket-connected players. When the requirements
 * for a new game are fulfilled this class is used for creating the game and for warn
 * player's for game start.
 * 
 * @author Lorenzo
 *
 */
public class CreaPartitaSocket extends TimerTask {
	
	private List<Accettazione> giocatori=new ArrayList<Accettazione>();
	private List<BrokerThread> threadSubs=new ArrayList<BrokerThread>();
	private ServerInterface server;
	private String mappa;
	private SalaSocket sala;
	private Mappa map;
	
	/**This constructor stores into the class all the useful attributes to create a new game. 
	 * 
	 * @param giocatori the player's list to be added in the game.
	 * @param server the RMI server that will host the game.
	 * @param mappa the name of the chosen map.
	 * @param sala the room in which the players are subscribed to the waiting list.
	 */
	public CreaPartitaSocket(List<Accettazione> giocatori, ServerInterface server, String mappa,SalaSocket sala){
		this.mappa=mappa;
		this.giocatori=giocatori;
		this.server=server;
		this.sala=sala;
	}
	
	/**This method properly creates the game with the attributes given to the class.
	 * It's first created a new map, and then the players, basing on their position in the subsription list,
	 * are created, humans or aliens. Their list is then shuffled and then is created the {@code StatoDiGioco}
	 * class which contains them and the game map. The association between communication interface and client's id
	 * gets stored into the server, and the waiting room restored. Players are warned for the game start,
	 * so they can begin to play.
	 * 
	 */
	@Override
	public void run() {
		int i=0;
		Giocatore g;
		List<Giocatore> players=new ArrayList<Giocatore>();
		
		if(mappa.contentEquals("fermi"))
			 map=new Fermi();
		else if(mappa.contentEquals("galvani"))
			map=new Galvani();
		else
			map=new Galilei();
		
		for(Accettazione a : giocatori){
			if((i%2)==0){
				g=new Alieno(a.getId(),0,map,a.getNomeGiocatore());
				
				if(i==0)
					g.setSituazione(Situazione.ATTIVO);
					
				else
					g.setSituazione(Situazione.INATTIVO);
				
			}
			else
				g=new Umano(a.getId(),0,map,a.getNomeGiocatore());
			i++;
			players.add(g);
		}
		Collections.shuffle(players);
		int j=0;
		for(Giocatore g1 : players){
			g1.setOrdine(j);
			if(j==0){
				g1.setSituazione(Situazione.ATTIVO);
				g1.setStato(Stato.INIZIO);
			} else {
				g1.setSituazione(Situazione.INATTIVO);
				g1.setStato(Stato.TURNOTERMINATO);
			}
			j++;
		}
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) players, map);
		for(Giocatore p: players){
			server.getPartite().put(p.getID(), partita);
		}
		
		for(Accettazione a : sala.getListaAccettazione(map)){
			threadSubs.add(((AccettazioneSocket) a).getbt());
		}
		
		for(Giocatore g2 : players){
			((Server) server).getIdSub().put(g2.getID(), (ArrayList<BrokerThread>)threadSubs);
		}
		
		sala.svuotaLista(mappa);
		sala.cancelloTimer(mappa);
		String messaggio=new String("Partita Iniziata. Parte "+players.get(0).getNome()+".");
		try {
			server.publish(new Messaggio(messaggio),players.get(0).getID());
		} catch (RemoteException e) {
		}
		server.startTimer(partita, partita.getGiocatori().get(0));
	}
		
}
