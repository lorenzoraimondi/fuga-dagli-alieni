package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.MazzoOggetti;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

/**Represent the Adrenaline Card action performing, for a human player that uses an Adrenaline card during his turn.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class UsaAdrenalina extends Azione {

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	/**Create the Adrenaline Card action to perform, associating it to a game and to a player, and storing
	 * the Items discarded cards' deck, in which the used card will be added. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 */
	public UsaAdrenalina(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
		this.mazzo=(MazzoOggetti) partita.getMazzoOggetti();
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform Adrenaline Card action;
	 * the effect gets activated, increasing player's {@code portata}, and the Adrenaline card 
	 * is taken from the player's hand and put in the Item discarded cards' deck.
	 *  
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui(){
		
		if(controlli()){
			
			giocatore.setPortata(2);
			carta=giocatore.getCarta(TipoCartaOggetto.ADRENALINA);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			return new RispostaController("Hai utilizzato Adrenalina",giocatore.getNome()+" ha utilizzato Adrenalina");
		}
		return new RispostaController("Mossa non valida",null);
		
	}
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ADRENALINA))){
				return true;
			} 
			return false;	
		}
		return false;
	}
	
}
