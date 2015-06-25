package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;

/**Represent the turn ending action performing, for a player that wants to finish his turn.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class TerminaTurno extends Azione {
	
	private ServerInterface server;

	/**Create the turn ending action to perform, associating it to a game and to a player and
	 * storing the server in which the game resides. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 * @param server the server in which the game resides.
	 */
	public TerminaTurno(Giocatore giocatore, StatoDiGioco model, ServerInterface server) {
		super(giocatore, model);
		this.server=server;
	}

	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the turn ending action;
	 * the current player's turn and game statuses are updated, the next player is searched in the
	 * players list and his statuses are modified to permit him to play. Also, if the current player is human,
	 * all the cards' effects that modifies player's attributes are reset. Moreover, if the game reaches the
	 * 40th turn, the game ending action is performed.
	 * 
	 * @return the {@code RispostaController} object with the response for the client.
	 */
	@Override
	public RispostaController esegui() throws IOException {
		if(this.controlli()){
			Giocatore giocatorePassato,giocatoreProssimo,giocatoreSwap;
			
			if(giocatore.getSituazione()!=Situazione.VINTO){
				giocatore.setSituazione(Situazione.INATTIVO);
				giocatore.setStato(Stato.TURNOTERMINATO);
			}
				
			
			if(giocatore instanceof Umano){
				Umano giocatoreUmano=(Umano)giocatore;
				giocatoreUmano.setSedato(false);
				giocatoreUmano.setPortata(1);
			}
			
			giocatorePassato=model.getGiocatori().remove(0);
			model.getGiocatori().add(giocatorePassato);
			giocatoreProssimo=model.getGiocatori().get(0);
			
			int giocatoriSaltati=0;
			
			while(giocatoreProssimo.getSituazione()==Situazione.DISCONNESSO || giocatoreProssimo.getSituazione()==Situazione.MORTO || giocatoreProssimo.getSituazione()==Situazione.VINTO){
				giocatoreSwap=model.getGiocatori().remove(0);
				model.getGiocatori().add(giocatoreSwap);
				giocatoreProssimo=model.getGiocatori().get(0);
				
				giocatoriSaltati++;
			
			}
			giocatoreProssimo.setSituazione(Situazione.ATTIVO);
			giocatoreProssimo.setStato(Stato.INIZIO);
			
			if(giocatore.getOrdine()+giocatoriSaltati>=model.getGiocatori().size()-1){
				model.incrementTurno();
				if(model.getTurno()==40)
					return new TerminaPartita(giocatore,model,server).esegui();
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
						+ "Turno "+model.getTurno()+". "
						+ "Tocca a "+giocatoreProssimo.getNome()+".");
			}
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
					+ "Tocca a "+giocatoreProssimo.getNome()+".");
			
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if((giocatore.getStato()==Stato.ATTACCATO||giocatore.getStato()==Stato.CARTASCIALUPPA||giocatore.getStato()==Stato.EFFETTOCONCLUSO||giocatore.getStato()==Stato.SICURO) 
				&& (giocatore.getSituazione()==Situazione.ATTIVO || giocatore.getSituazione()==Situazione.VINTO)){
			if(giocatore.getCarte().size()>=4){
				return false;
			}		
			return true;
		}
		return false;
	}

}
