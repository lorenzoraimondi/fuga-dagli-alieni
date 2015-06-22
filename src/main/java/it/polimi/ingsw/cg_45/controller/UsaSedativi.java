package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

/**Represent the Sedatives Card action performing, for a human player that uses a Sedative card during his turn.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class UsaSedativi extends Azione{

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	/**Create the Sedatives Card action to perform, associating it to a game and to a player, and storing
	 * the Items discarded cards' deck, in which the used card will be added. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 */
	public UsaSedativi(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
		this.mazzo=(MazzoOggetti) partita.getMazzoOggetti();
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform Sedatives Card action;
	 * the effect gets activated, permitting the player not to draw a card in case he
	 * move to a Dangerous sector; the Sedatives card 
	 * is taken from the player's hand and put in the Item discarded cards' deck.
	 *  
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui(){
		if(controlli()){
			Umano g=(Umano)giocatore;
			g.setSedato(true);
			carta=giocatore.getCarta(TipoCartaOggetto.SEDATIVI);
			if(giocatore.getStato()==Stato.PERICOLO)
				giocatore.setStato(Stato.SICURO);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			return new RispostaController("Hai usato la carta Sedativi",giocatore.getNome()+"ha usato una carta Sedativi");
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.SEDATIVI))){
				return true;
		} 		
			return false;
		}
		return false;
	}
	/*Per test
		
		public Giocatore getGiocatore() {
			return giocatore;
		}
		public StatoDiGioco getPartita() {
			return model;
		}
		
		*/
	
}
