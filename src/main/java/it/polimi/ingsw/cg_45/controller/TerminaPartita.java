package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.rmi.RMIServer;
import it.polimi.ingsw.cg_45.socket.SocketServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Represent the game end action performing, used after an attack or an escape to verify if the game is finished.
 * This class permit to verify if the game ended, and in case stops the game, communicating
 * a response to the players and closing the game.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class TerminaPartita extends Azione{
	
	private ServerInterface server;
	
	
	/**Create the game end action to perform, associating it to a game and to the current player, and
	 * storing the server in which the game resides. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 * @param server the server in which the game resides.
	 */
	public TerminaPartita(Giocatore giocatore, StatoDiGioco partita, ServerInterface server){
		super(giocatore,partita);
		this.server=server;
	}
	
	
	/**Execute the game end action, after checking its possibility.
	 * <p> 
	 * Depending on the number of the human players remained, this method stops the game; 
	 * the turn is stopped, and the game deleted from the server, and the results are sent to the players. 
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui() throws IOException {
		if(controlli()){
			int umaniVincitori=0;
			List<String> risposte=new ArrayList<String>();
	
			
			for(Giocatore g : model.getGiocatori()){
				if(g instanceof Umano && g.getSituazione()==Situazione.VINTO){
					umaniVincitori++;
					risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", ha vinto!\n");
				}
			}
			
			
			if(umaniVincitori!=(model.getGiocatori().size()/2)){
				for(Giocatore g : model.getGiocatori()){
					if(g instanceof Alieno){
						risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", ha vinto!\n");
					}
				}	
			}
			
			String risposta=new String("\nPartita terminata!\n");
			
			
			for(String s : risposte){
				risposta=risposta.concat(s);
			}
			
			
			for(Giocatore g : model.getGiocatori()){
				g.setSituazione(Situazione.DISCONNESSO);
				g.setStato(Stato.TURNOTERMINATO);
			}

			if(server instanceof RMIServer && 
						(((RMIServer) server).getRmiTimers().get(model.getGiocatori().get(0).getID())!=null)){
				
				((RMIServer) server).getRmiTimers().get(model.getGiocatori().get(0).getID()).interrupt();
				
				}
			else if(server instanceof SocketServer &&
						(((SocketServer) server).getTimers().get(model.getGiocatori().get(0).getID())!=null)){
				
				((SocketServer) server).getTimers().get(model.getGiocatori().get(0).getID()).interrupt();
				
			}
				
				
			
			
			for(Giocatore g : model.getGiocatori()){
				server.getPartite().remove(g.getID());
				
				
			}
			
			
			return new RispostaController("",risposta);
	
		}
		
		return null;
				
	}

	@Override
	protected boolean controlli() {
		if(model.numeroUmaniInGioco()==0 || model.getTurno()==40)
			return true;
		return false;
		
	}
	
}
