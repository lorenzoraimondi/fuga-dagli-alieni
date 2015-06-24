package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

/**Represent the Item Card Draw action performing, for a player that drawn a Dangerous Sector card with the Item icon.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Andrea Turconi
 *
 */
public class PescaOggetto extends Azione{

	private CartaOggetto carta;
	
	/**Create the Item Card Draw action to perform, associating it to a game and to a player. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 */
	public PescaOggetto(Giocatore giocatore, StatoDiGioco model){
		super(giocatore,model);
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the Item Card draw; the card taken by the Item cards' deck
	 * is assigned to the player and player's turn status updated. If there is no card to draw the player's status is updated
	 * and the game can normally continue. 
	 * 
	 * @return the {@code RispostaController} object with the response for the client.
	 */
	@Override
	public RispostaController esegui(){
		if(this.controlli()){
			carta=(CartaOggetto) model.getMazzoOggetti().pescaCarta();
			if(carta!=null){
				giocatore.setCarta(carta);
				giocatore.setStato(Stato.EFFETTOCONCLUSO);
				return new RispostaController("Hai pescato una carta "+carta.getTipo().toString(),giocatore.getNome()+" ha pescato una carta oggetto");	
			} else {
				giocatore.setStato(Stato.EFFETTOCONCLUSO);
				return new RispostaController("Non ci sono carte oggetto disponibili, continua il turno.",null);
				
			}
			
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if((stato==Stato.BLUFFATO)||(stato==Stato.RIVELATO) && giocatore.getSituazione()==Situazione.ATTIVO)
					return true;
		return false;
	}
	
}
