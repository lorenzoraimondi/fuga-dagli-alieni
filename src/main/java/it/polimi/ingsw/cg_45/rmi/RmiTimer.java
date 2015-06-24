package it.polimi.ingsw.cg_45.rmi;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaPartita;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;

import java.io.IOException;
import java.rmi.RemoteException;

/**Represents a timer to be used for count how many time take player's turn.
* In fact if it lasts more than the specified number of seconds (120) the player will be
* disconnected from the game and the turn passed to the next player.
* 
* @author Andrea Turconi
*
*/
//public class RmiTimer extends it.polimi.ingsw.cg_45.netCommons.Timer implements Runnable{
public class RmiTimer extends it.polimi.ingsw.cg_45.netCommons.Timer implements Runnable{
	//private StatoDiGioco partita;
	//private Giocatore giocatore;
	private Azione azione;
	//private int secondi;
	private RispostaController risp;
	//private RMIServer server;
	
	/**Creates a new timer and links it to the relative player, game, and server in which it's hosted.
	 * 
	 * @param partita the {@code StatoDiGioco} game.
	 * @param giocatore the current player to time track.
	 * @param server the server that hosts the game.
	 */
	/*public RmiTimer(StatoDiGioco partita, Giocatore giocatore,ServerInterface server){
		super(partita,giocatore,server);
	}*/
	public RmiTimer(StatoDiGioco partita, Giocatore giocatore,RMIServer server){
		super(partita,giocatore,server);
		//secondi=50;
	}

	/**This method starts the timer's action, counting how many seconds does player's turn take.
	 * The timer can only be stopped if the player pass the turn before 120 seconds. Otherwise
	 * it will be disconnected from the game and the other players warned of this fact. 
	 * This action causes the possibility that without the 
	 * disconnected player the game could be ended, so is needed to verify it and if so
	 * to stop the game and warn all the players.
	 * 
	 */
	@Override
	public void run() {
		System.out.println("timer RMI partito");
		/*try {
			int size=0;
			for(Giocatore g:partita.getGiocatori()){
				if(g.getSituazione()==Situazione.ATTIVO || g.getSituazione()==Situazione.INATTIVO || g.getSituazione()==Situazione.MORTO)
					size++;
			}
			int[] ordine=new int[size];
			for(int i=0;i<ordine.length;i++){
				if(partita.getGiocatori().get(i).getSituazione()==Situazione.ATTIVO ||partita.getGiocatori().get(i).getSituazione()==Situazione.INATTIVO || partita.getGiocatori().get(i).getSituazione()==Situazione.MORTO)
				ordine[i]=partita.getGiocatori().get(i).getID();
			}
			int swap;
			for(int j=0;j<ordine.length-1;j++){
				for(int i=0;i<ordine.length-1;i++){
					if(ordine[i]>ordine[i+1]){
						swap=ordine[i+1];
						ordine[i+1]=ordine[i];
						ordine[i]=swap;
						}
				}
			}*/
			int ordine[]=ordinamento();

			try{Thread.sleep(SECONDI*1000);
				azione=new Disconnessione(giocatore,partita,server);
				try {
					risp=azione.esegui();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("comando non valido");
				}		
				
				for(int i=0;i<ordine.length;i++){
					System.out.println("id "+ordine[i]);
				}
			
				
				System.out.println(((RMIServer) server).getIdSub());
					for(int i=0;i<ordine.length;i++){
						if(ordine[i]==giocatore.getID()){
							System.out.println("posizione da rimuovere: "+(i));
							((RMIServer) server).getIdSub().get(giocatore.getID()).remove(i);
						}	
					}
				System.out.println(((RMIServer) server).getIdSub());
				try{
				server.publish(new Messaggio(risp.getMessaggioBroadcast()), giocatore.getID());
				}catch(RemoteException e){
					//e.printStackTrace();
					System.out.println("remote exception");
				}
				
				Giocatore primo=partita.getGiocatori().get(0);
				
				if(partita.numeroUmaniInGioco()>0){
					((RMIServer) server).startTimer(partita, primo);
				}
				else{
				try {
					((RMIServer) server).publish(new Messaggio(new TerminaPartita(giocatore, partita, server).esegui().getMessaggioBroadcast()), giocatore.getID());
				} catch (IOException e) {
				}
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("timer interrotto");
		}
	}
}