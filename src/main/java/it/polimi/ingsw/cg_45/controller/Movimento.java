package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Settore;
import it.polimi.ingsw.cg_45.model.SettorePericoloso;
import it.polimi.ingsw.cg_45.model.SettoreScialuppa;
import it.polimi.ingsw.cg_45.model.SettoreSicuro;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;

/**Represent the move action performing, for a player that wants to move from his current sector.
 * This class permit to verify if the player can perform the move and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Movimento extends Azione{
	
	private Settore settorePartenza,settoreArrivo;
	private Mappa mappa;
	private int portata;
	
	
	/**Create the move action to perform, associating it to a game and to a player, and storing
	 * the sector in which the player wants to move, player's current sector, and how many sector he can cover.
	 * 
	 * @param p the player that performs the action.
	 * @param g the game in which the player is playing.
	 * @param s the sector in which the player wants to move.
	 */
	public Movimento(StatoDiGioco p,Giocatore g, Settore s){
		super(g,p);
		
		this.mappa=p.getMappa();
		this.settoreArrivo=s;
		this.portata=g.getPortata();
		this.settorePartenza=g.getPosizione();
		
		
	}
	
	/**Execute the move action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform a move in the chosen sector.
	 * Once moved, player's turn status is updated to the subsequent one, giving him the possibility
	 * to draw a card, attack, or finish his turn.
	 * The method then return the responses for the client and all the other players.
	 * 
	 * @return the {@code RispostaController} object with the response for the client.
	 */
	@Override
	public RispostaController esegui(){
		if(this.controlli()){
			giocatore.setPosizione(settoreArrivo);
			if(settoreArrivo instanceof SettoreSicuro){
				giocatore.setStato(Stato.SICURO);
				return new RispostaController("Movimento effettuato in settore sicuro",null);
			} else if(settoreArrivo instanceof SettorePericoloso){
				if(giocatore instanceof Umano){
					Umano umano=(Umano)giocatore;
					if(umano.isSedato()){
						giocatore.setStato(Stato.SICURO);
						return new RispostaController("Movimento effettuato in settore pericoloso",null);
					}
				}
				giocatore.setStato(Stato.PERICOLO);
			
				return new RispostaController("Movimento effettuato in settore pericoloso",null);
			} else {
				giocatore.setStato(Stato.SCIALUPPA);
				
				return new RispostaController("Movimento effettuato, pesca una carta Scialuppa",giocatore.getNome()+" è nel settore scialuppa "+settoreArrivo.getCoordinate().toString());
				}
		}
		return new RispostaController("Mossa non valida",null);
		
			
	}
		
	/**This methods checks if the move action can be performed from the player, basing the controls
	 * over player's game and turn state, the target sector and the coverable distance.
	 * 
	 * @return {@code true} if the player can perform the action, {@code false} otherwise.
	 */
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()!=Situazione.ATTIVO || giocatore.getStato()!=Stato.INIZIO){
			return false;
		} else if(mappa.mossaValida(settorePartenza, settoreArrivo, portata)){
			
			if(settoreArrivo instanceof SettoreScialuppa && giocatore instanceof Alieno){
				return false;
			}
			return true;
		}
		return false;
	}
	
}

