package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

/**Represent the Defense Card action performing, for a human player attacked from an alien.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class UsaDifesa extends Azione{
	
	private CartaOggetto carta;
	
	/**Create the Defense Card action to perform, associating it to a game and to a player.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 */
	public UsaDifesa(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform Defense Card action;
	 * when an human player having this card gets attacked, it automatically gets actived:
	 * the attack don't kill the human player and the card get discarded.
	 *  
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui(){
		if(controlli()){
			carta=giocatore.getCarta(TipoCartaOggetto.DIFESA);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
			return new RispostaController("Hai usato la carta difesa",giocatore.getNome()+" ha usato la carta Difesa");	
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore instanceof Umano && (giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA))))
			return true;
		return false;
	}
	
}
