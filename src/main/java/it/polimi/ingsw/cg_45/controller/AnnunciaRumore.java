package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Coordinate;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

/**Represent the noise announcement performing, for a player that has drawn a Dangerous Sector Card.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class AnnunciaRumore extends Azione{
	private String coordinateSettore;
	
	/**Create the action to perform, associating it to a game and to a player, and eventually storing
	 * the sector in which the player is announcing the noise.
	 * 
	 * @param p the player that performs the action.
	 * @param g the game in which the player is playing.
	 * @param cs the desired sector's sign. 
	 */
	public AnnunciaRumore(StatoDiGioco p, Giocatore g, String cs){
		super(g,p);
		this.coordinateSettore=cs;
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method update it to the subsequent and return the responses for
	 * the client and all the other players.
	 * 
	 * @return the {@code RispostaController} object with the responses for the players.
	 */
	@Override
	public RispostaController esegui() throws IOException {
		if(controlli()){
		switch(giocatore.getStato()){
		case SILENZIO:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Silenzio nell'astronave.");
		case CARTABLUFFOGGETTO:
			giocatore.setStato(Stato.BLUFFATO);
			System.out.println(giocatore.getStato());
			return new RispostaController("Pesca un oggetto","Rumore nel settore "+coordinateSettore+".");
		case CARTABLUFF:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Rumore nel settore "+coordinateSettore+".");
		case CARTARIVELAOGGETTO:
			giocatore.setStato(Stato.RIVELATO);
			System.out.println(giocatore.getStato());
			return new RispostaController("Pesca un oggetto","Rumore nel settore "+coordinateSettore+".");
		case CARTARIVELA:
			giocatore.setStato(Stato.EFFETTOCONCLUSO);
			System.out.println(giocatore.getStato());
			return new RispostaController("","Rumore nel settore "+coordinateSettore+".");
		default:
			break;
		
		}
		}
		return new RispostaController("Mossa non valida",null);
		
	}

	/**This methods checks if the action can be performed from the player, basing the controls
	 * over player's game and turn state.
	 * 
	 * @return {@code true} if the player can perform the action, {@code false} otherwise.
	 */
	@Override
	protected boolean controlli() {
			if(giocatore.getStato()==Stato.CARTABLUFF || giocatore.getStato()==Stato.CARTABLUFFOGGETTO || giocatore.getStato()==Stato.SILENZIO)
				return true;
			else if(giocatore.getStato()==Stato.CARTARIVELA || giocatore.getStato()==Stato.CARTARIVELAOGGETTO){
				if(giocatore.getPosizione().getCoordinate().equals(new Coordinate(coordinateSettore))){
					return true;
				}
				return false;
			}
			return false;	
	}

}
