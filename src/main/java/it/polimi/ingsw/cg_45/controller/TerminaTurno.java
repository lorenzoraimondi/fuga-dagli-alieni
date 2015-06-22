package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;
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

			giocatore.setSituazione(Situazione.INATTIVO);
			giocatore.setStato(Stato.TURNOTERMINATO);
			if(giocatore instanceof Umano){
				Umano giocatoreUmano=(Umano)giocatore;
				giocatoreUmano.setSedato(false);
				giocatoreUmano.setPortata(1);
			}
			//model.incrementCurrentPlayer();
			/*	Meglio se messo in Attacco e PescaScialuppa
			//Giocatore inutile, riesco a overrideare costruttore?
			RispostaController terminaPartita=new TerminaPartita(giocatore,model).esegui();
			if(terminaPartita!=null)
				return terminaPartita;*/
			
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
			
			//if(giocatore.getOrdine()==1){
			System.out.println("ordine "+giocatore.getOrdine());
			System.out.println("somma "+giocatoriSaltati);
			if(giocatore.getOrdine()+giocatoriSaltati>=model.getGiocatori().size()-1){
				model.incrementTurno();
				System.out.println("turno dopo: "+model.getTurno());
				if(model.getTurno()==40)
					return new TerminaPartita(giocatore,model,server).esegui();
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
						+ "Turno "+model.getTurno()+". "
						+ "Tocca a "+giocatoreProssimo.getNome()+".");
			}
				return new RispostaController("Turno terminato", giocatore.getNome()+" ha passato. "
					+ "Tocca a "+giocatoreProssimo.getNome()+".");
			//if(model.getTurno()==40)
				//Partita.set("FINITA");
			/*
			if(giocatore.getID()==model.getGiocatori().size())
				model.incrementTurno();
			//if(model.getTurno()==40)
				//Partita.set("FINITA");
				 
				 */
		}
		return new RispostaController("Mossa non valida",null);
	}

	@Override
	protected boolean controlli() {
		if((giocatore.getStato()==Stato.ATTACCATO||giocatore.getStato()==Stato.CARTASCIALUPPA||giocatore.getStato()==Stato.EFFETTOCONCLUSO||giocatore.getStato()==Stato.SICURO) && giocatore.getSituazione()==Situazione.ATTIVO){
			if(giocatore.getCarte().size()>=4){
				System.out.println("1");
				return false;
			}
				
			return true;
		}
		System.out.println("2");
		return false;
	}

}
