package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.Carta;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;

/**Represent the discard action performing, for a player that has four Item cards in his hand.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Andrea Turconi
 *
 */

public class ScartaCarta extends Azione{
	
	private TipoCartaOggetto tipoCarta;
	private Carta carta;
	
	/**Create the Discard action to perform, associating it to a game and to a player, and storing
	 * the card's type which the player wants to discard.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 * @param tipoCarta the wished discard card's type.
	 */
	public ScartaCarta(Giocatore giocatore, StatoDiGioco model, TipoCartaOggetto tipoCarta) {
		super(giocatore, model);
		this.tipoCarta=tipoCarta;
	}	

	/**Execute the discard action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the card discard; the card is taken
	 * from the player's hand and added to the Items discarded card's deck. 
	 * 
	 * @return the {@code RispostaController} object with the response for the client and the other players.
	 */
	@Override
	public RispostaController esegui() {
		if(controlli()){
			carta=giocatore.getCarta(tipoCarta);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
			return new RispostaController("Carta "+tipoCarta.toString()+" scartata.",giocatore.getNome()+" ha scartato una carta.");
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getCarte().size()>=4 && giocatore.getCarta(tipoCarta)!=null && giocatore.getSituazione()==Situazione.ATTIVO){
				return true;
		}
		return false;
	}
}