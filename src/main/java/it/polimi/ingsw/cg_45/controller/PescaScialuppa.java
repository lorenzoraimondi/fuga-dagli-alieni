package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.CartaScialuppa;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.SettoreScialuppa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaScialuppa;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;

/**Represent the Escape Hatch Card draw action performing, for a player that has moved into an Escape Hatch sector.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Andrea Turconi
 *
 */
public class PescaScialuppa extends Azione{
	
	private CartaScialuppa carta;
	private SettoreScialuppa scialuppa;
	private ServerInterface server;
	
	/**Create the Escape Hatch card draw action to perform, associating it to a game and to a player.
	 * It's also stored the Escape Hatch sector in which the player tries to escape, and the server 
	 * in which the game resides.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 * @param server the server in which the game resides.
	 */
	public PescaScialuppa(Giocatore giocatore,StatoDiGioco model, ServerInterface server){
		super(giocatore,model);
		this.scialuppa=(SettoreScialuppa)giocatore.getPosizione();
		this.server=server;
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the Escape Hatch card draw; 
	 * the card taken by the Escape Hatch cards' deck is drawn and depending on its color 
	 * the player can win or the Hatch can get closed. 
	 * If the players win it's also checked if the game has finished or can go on, instead if the hatch gets
	 * closed this is saved in way to forbid to other players to move into it. 
	 * 
	 * @return the {@code RispostaController} object with the response for the client and the other players.
	 */
	@Override
	public RispostaController esegui() throws IOException{
		//IOEx lanciata per terminaPartita
		if(this.controlli()){
			carta=(CartaScialuppa) model.getMazzoScialuppe().pescaCarta();
			if(carta.getTipo()!=TipoCartaScialuppa.ROSSA){
				scialuppa.setScoperta();
				giocatore.setSituazione(Situazione.VINTO);
				//In questo stato può solo usare/scartare carta, che sono vietati da controlli
				giocatore.setStato(Stato.EFFETTOCONCLUSO);				
				RispostaController terminaPartita=new TerminaPartita(giocatore,model,server).esegui();
				if(terminaPartita!=null)
					return terminaPartita;
				
				return new RispostaController("Ci sei riuscito!",giocatore.getNome()+" è scappato dall'astronave!");
			}
			scialuppa.setScoperta();
			giocatore.setStato(Stato.CARTASCIALUPPA);
			return new RispostaController("Scialuppa bloccata!",giocatore.getNome()+"ha pescato una carta scialuppa, ma la scialuppa è bloccata!");
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getStato()==Stato.SCIALUPPA && giocatore.getSituazione()==Situazione.ATTIVO){
			SettoreScialuppa scialuppa=(SettoreScialuppa) model.getMappa().getMappa().get(giocatore.getPosizione().getCoordinate());
			if(!scialuppa.isScoperta())
				return true;
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
