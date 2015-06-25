package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**Represent a Chat message dispatch.
 * This class permit to send a chat message as an action, due to grant communication between players.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Chat extends Azione {
	private String messaggio;
	
	/**Create the chat message to dispatch, associating it to a player and a game, and storing
	 * the message which the player wants to send.
	 * 
	 * @param messaggio the message to send to the other players
	 * @param giocatore giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 */
	public Chat(Giocatore giocatore, StatoDiGioco partita, String messaggio){
		super(giocatore,partita);
		this.messaggio=messaggio;
		
	}

	/**Execute the action, in this case always possible because a player always can chat with others.
	 * <p> 
	 * Create the chat string to send to other players, generating a time stamp and adding the message.
	 * The method then return the responses for the client and the message for all the players.
	 * 
	 * @return the {@code RispostaController} object with the responses for the players.
	 */
	@Override
	public RispostaController esegui() throws IOException {
		if(controlli()){
			Date date= new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
			
	
			return new RispostaController("Messaggio inviato","\n"+"["+sdf.format(date)+"] "+giocatore.getNome()+": "+messaggio);		
		}
		return new RispostaController("Messaggio non inviato",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getSituazione()==Situazione.DISCONNESSO)
			return false;
		return true;
	}

}
