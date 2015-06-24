package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.StatoDiGioco;

import java.io.IOException;

/**Represent an information request, for all player's action during the game.
 * This class permit to request some basic information about ourselves to the server,
 * for getting help during the turn.
 * 
 * @author Andrea Turconi
 *
 */
public class Statistiche extends Azione {

	/**Create the information request to perform, associating it to a game and to a player.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 */
	public Statistiche(Giocatore giocatore, StatoDiGioco partita){
		super(giocatore,partita);
	}
	
	/**Execute the action.
	 * <p> 
	 * Depending on the player turn status, this method perform the action 
	 * by creating a message reporting a range of information useful for the player:
	 * <ul>
	 * <li>race; 
	 * <li>position;
	 * <li>gained cards;
	 * <li>number of capable steps;
	 * <li>action that can be performed according to player's game and turn statuses.
	 * 
	 * @return the {@code RispostaController} object with the response for the client.
	 * @throws IOException
	 */
	@Override
	public RispostaController esegui() throws IOException {
		
		if(controlli()){
			String messaggio=new String("\nsei un "+giocatore.getClass().getSimpleName()+"\nportata: "+
					giocatore.getPortata()+"\nti trovi in posizione: "+
					giocatore.getPosizione().getCoordinate()+"\nle tue carte sono: "+giocatore.getCarte()
					+"\n\n");
			if(giocatore.getCarte().size()>=4)
				return new RispostaController(messaggio+"mossa obbligata:\nscartare una carta oggetto",null);
			
			switch(giocatore.getStato()){
			case INIZIO :
				return new RispostaController(messaggio+"mosse valide:\neffettuare il movimento\nusare una carta oggetto (solo se umano)",null);
			case PERICOLO :
				return new RispostaController(messaggio+"mosse valide:\npescare una carta settore\nattaccare (solo se alieno o umano con la carta attacco)\nusare una carta oggetto (solo se umano)",null);
			case SICURO :
				return new RispostaController(messaggio+"mosse valide:\nusare una carta oggetto (solo se umano)\nterminare il turno",null);
			case CARTASCIALUPPA : 
				return new RispostaController(messaggio+"mossa obbligata:\npescare una carta scialuppa",null);
			case ATTACCATO : 
				return new RispostaController(messaggio+"mosse valide:\nusare carta (solo se umano)\nterminare il turno",null);
			case SILENZIO :
				return new RispostaController(messaggio+"mossa obbligata:\nannunciare silenzio",null);
			case CARTABLUFF : 
				return new RispostaController(messaggio+"mossa obbligata:\nannuncia un settore in cui vuoi far rumore",null);
			case CARTABLUFFOGGETTO:
				return new RispostaController(messaggio+"mossa obbligata:\nannuncia un settore in cui vuoi far rumore",null);
			case CARTARIVELA:
				return new RispostaController(messaggio+"mossa obbligata:\nannuncia il tuo settore in cui fare rumore",null);
			case CARTARIVELAOGGETTO:
				return new RispostaController(messaggio+"mossa obbligata:\nannuncia il tuo settore in cui fare rumore",null);
			case SCIALUPPA:
				return new RispostaController(messaggio+"mosse valide:\nusare carta (solo se umano)\nterminare il turno",null);
			case RIVELATO:
				return new RispostaController(messaggio+"mossa obbligata:\npescare una carta oggetto",null);
			case BLUFFATO:
				return new RispostaController(messaggio+"mossa obbligata:\npescare una carta oggetto",null);
			case EFFETTOCONCLUSO:
				return new RispostaController(messaggio+"mosse valide:\nusare una carta (solo se umano)\nterminare il turno",null);
			case TURNOTERMINATO:
				return new RispostaController(messaggio+"non è il tuo turno",null);
			default:
				break; 
			}
		}
		return new RispostaController("Mossa non valida, sei disconnesso",null);
	}

	@Override
	protected boolean controlli() {
		if(giocatore.getSituazione()==Situazione.DISCONNESSO)
			return false;
		return true;
	}

}
