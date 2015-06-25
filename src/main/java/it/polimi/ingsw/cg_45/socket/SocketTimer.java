package it.polimi.ingsw.cg_45.socket;

import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaPartita;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.Timer;

import java.io.IOException;

/**Represents a timer to be used for count how many time take player's turn.
* In fact if it lasts more than the specified number of seconds (120) the player will be
* disconnected from the game and the turn passed to the next player.
* 
* @author Andrea Turconi
*
*/
public class SocketTimer extends Timer implements Runnable{
	private Azione azione;
	private RispostaController risp;
	
	/**Creates a new timer and links it to the relative player, game, and server in which it's hosted.
	 * 
	 * @param partita the {@code StatoDiGioco} game.
	 * @param giocatore the current player to time track.
	 * @param server the server that hosts the game.
	 */
	public SocketTimer(StatoDiGioco partita, Giocatore giocatore,SocketServer server){
		super(partita,giocatore,server);
	}

	@Override
	public void run() {
		
			int ordine[]=ordinamento();
			
			try{
				Thread.sleep((long)SECONDI*1000);
				azione=new Disconnessione(giocatore,partita,server);
				try {
					risp=azione.esegui();
				} catch (IOException e) {
				}
				
				
				for(int i=0;i<ordine.length;i++){
					if(ordine[i]==giocatore.getID()){
						((SocketServer) server).getIdSub().get(giocatore.getID()).remove(i);
					}	
				}
				((SocketServer)server).publish(new Messaggio(risp.getMessaggioBroadcast()), giocatore.getID());
				
				Giocatore primo=partita.getGiocatori().get(0);
				
				if(partita.numeroUmaniInGioco()>0)
					server.startTimer(partita, primo);
				else{
					try {
						server.publish(new Messaggio(new TerminaPartita(giocatore, partita,server).esegui().getMessaggioBroadcast()), giocatore.getID());
					} catch (IOException e) {
					}
				}
		} catch (InterruptedException e) {
		}
	}
}