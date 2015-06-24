package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;


/**Represents a generic timer to be used for count how many time take player's turn.
 * In fact if it lasts more than the specified number of seconds (120) the player will be
 * disconnected from the game and the turn passed to the next player.
 * 
 * @author Andrea Turconi
 *
 */
public abstract class Timer {
	
	protected StatoDiGioco partita;
	protected Giocatore giocatore;
	protected Azione azione;
	protected static final int SECONDI=40;
	protected RispostaController risp;
	protected ServerInterface server;

	/**Creates a new timer and links it to the relative player, game, and server in which it's hosted.
	 * 
	 * @param partita the {@code StatoDiGioco} game.
	 * @param giocatore the current player to time track.
	 * @param server the server that hosts the game.
	 */
	public Timer(StatoDiGioco partita, Giocatore giocatore,ServerInterface server){
		this.giocatore=giocatore;
		this.partita=partita;
		this.server=server;
	}
	
	/**
	 * @throws InterruptedException
	 */
	public int[] ordinamento() {
		
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
			}
			/*try {
				//Thread.sleep((long)SECONDI*1000);
				azione=new Disconnessione(giocatore,partita,server);
				
			//} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				//System.out.println("timer interrotto");
			//}
			try {
				risp=azione.esegui();
			} catch (IOException e) {
				System.out.println("comando non valido");
			}
			for(int i=0;i<ordine.length;i++){
				System.out.println("id "+ordine[i]);
			}*/
			return ordine;
		}
}
