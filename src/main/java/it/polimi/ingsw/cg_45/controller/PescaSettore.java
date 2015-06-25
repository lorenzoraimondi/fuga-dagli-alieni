package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.CartaSettore;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaSettore;

/**Represent the Dangerous Sector card draw action performing, for a player that has moved into a Dangerous Sector.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Andrea Turconi
 *
 */

public class PescaSettore extends Azione{

	private CartaSettore carta;
	
	/**Create the Dangerous Sector card draw action to perform, associating it to a game and to a player
	 * and storing player's game turn status. 
	 * 
	 * @param giocatore the player that performs the action.
	 * @param model the game in which the player is playing.
	 */
	public PescaSettore(Giocatore giocatore,StatoDiGioco model){
		super(giocatore,model);
		this.stato=giocatore.getStato();
	}
	
	/**Execute the Dangerous Sector card draw action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform the Dangerous Sector card draw; 
	 * the card taken by the Dangerous Sector cards' deck is drawn and depending on its type 
	 * player's turn status gets updated, and response created. 
	 * 
	 * @return the {@code RispostaController} object with the response for the client and the other players.
	 */
	@Override
	public RispostaController esegui(){
		final String tuoSettore="Hai pescato una carta 'Rumore nel tuo settore'";
		final String qualsiasiSettore="Hai pescato una carta 'Rumore in qualunque settore'";
		final String pescatoSettore=" ha pescato una carta settore";
		
		if(this.controlli()){
			carta=(CartaSettore) model.getMazzoSettori().pescaCarta();
			if(carta.getTipo()==TipoCartaSettore.RUMOREQUALUNQUESETTORE){
				if(!carta.isOggetto()){
					giocatore.setStato(Stato.CARTABLUFF);
					return new RispostaController(qualsiasiSettore+". Annuncia il settore.",giocatore.getNome()+pescatoSettore);
				}
				else
					giocatore.setStato(Stato.CARTABLUFFOGGETTO);
				return new RispostaController(qualsiasiSettore+" con oggetto. Annuncia il settore e pesca una carta.",giocatore.getNome()+pescatoSettore);
			}
			else if(carta.getTipo()==TipoCartaSettore.RUMORETUOSETTORE){
				if(!carta.isOggetto()){
					giocatore.setStato(Stato.CARTARIVELA);
				return new RispostaController(tuoSettore+". Annuncia il tuo settore.",giocatore.getNome()+pescatoSettore);
				}
				else
					giocatore.setStato(Stato.CARTARIVELAOGGETTO);
				return new RispostaController(tuoSettore+" con oggetto. Annuncia il tuo settore e pesca una carta.",giocatore.getNome()+pescatoSettore);
			}
			else
				giocatore.setStato(Stato.SILENZIO);
				return new RispostaController("Hai pescato una carta 'Silenzio'. Annuncia il silenzio.",giocatore.getNome()+pescatoSettore);
		}
		return new RispostaController("Mossa non valida",null);	
	}

	@Override
	protected boolean controlli() {
		if(stato==Stato.PERICOLO && giocatore.getSituazione()==Situazione.ATTIVO)
			return true;
		return false;
		
	}
	
}
