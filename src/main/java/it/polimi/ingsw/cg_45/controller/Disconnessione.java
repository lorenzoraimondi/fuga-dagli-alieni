package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;

/**Represent the disconnection performing, for a player that left the game or that doesn't complete
 * his turn in 120 seconds.
 * 
 * @author Andrea Turconi
 *
 */
public class Disconnessione extends Azione {
	
	ServerInterface server;

	/**Creates the disconnection action, associating it to the relative game and player, and storing
	 * the server in way to disconnect the player from it.
	 * 
	 * @param giocatore
	 * @param partita
	 * @param server
	 */
	public Disconnessione(Giocatore giocatore, StatoDiGioco partita, ServerInterface server) {
		super(giocatore,partita);
		this.server=server;
	}

	/**Execute player disconnection.
	 * 
	 * This method sets player turn and game states to the relative termined and disconnected values
	 * and calls the {@code TerminaTurno} action to get the turn passed to the next player.
	 * The method then return the disconnection warn response for and all the players.
	 */
	@Override
	public RispostaController esegui() throws IOException {
		giocatore.setStato(Stato.EFFETTOCONCLUSO);
		new TerminaTurno(giocatore,model,server).esegui();
		giocatore.setSituazione(Situazione.DISCONNESSO);
		giocatore.setStato(Stato.TURNOTERMINATO);
		return new RispostaController("ti sei disconnesso con successo",giocatore.getNome()+" si è disconnesso.Tocca a "+model.getGiocatori().get(0).getNome());
	}

	@Override
	protected boolean controlli() {
		return true;
	}

}
