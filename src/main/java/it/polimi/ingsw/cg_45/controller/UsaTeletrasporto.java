package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.MazzoOggetti;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;

/**Represent the Teleport Card action performing, for a human player that uses a Teleport card during his turn.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class UsaTeletrasporto extends Azione {
	
	private Mappa mappa; 
	private MazzoOggetti mazzo;
	private CartaOggetto carta;
	
	/**Create the Teleport Card action to perform, associating it to a game and to a player, storing
	 * the Items discarded cards' deck, in which the used card will be added, and the map in 
	 * which the player is.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 */
	public UsaTeletrasporto(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
		this.mappa=partita.getMappa();
		this.mazzo=(MazzoOggetti) partita.getMazzoOggetti();
		
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform Teleport Card action;
	 * the effect gets activated, moving the human player to the map's Human Sector, 
	 * and the Teleport card is taken from the player's hand and put in the Item discarded cards' deck.
	 *  
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui(){
		if(controlli()){
			Umano giocatoreUmano=(Umano)giocatore;
			giocatoreUmano.setPosizioneIniziale(mappa);
			carta=giocatore.getCarta(TipoCartaOggetto.TELETRASPORTO);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			return new RispostaController("Sei stato teletrasportato nella tua posizione iniziale",giocatore.getNome()+"è stato teletrasportato nella tua posizione iniziale");
		}
		return new RispostaController("Mossa non valida",null);
		
		
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO))){
				return true;
			}
			return false;
		} 
		return false;
	}
	//Per test
	
			public Giocatore getGiocatore() {
				return giocatore;
			}
			public StatoDiGioco getPartita() {
				return model;
			}
			
			//
}
