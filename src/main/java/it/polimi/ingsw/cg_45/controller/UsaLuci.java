package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.MazzoOggetti;
import it.polimi.ingsw.cg_45.model.Settore;
import it.polimi.ingsw.cg_45.model.SettoreVuoto;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**Represent the Spotlight Card action performing, for a human player that uses this card during his turn.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class UsaLuci extends Azione {
	
	private Settore settore;
	private Settore[] vicini;
	private MazzoOggetti mazzo;
	private CartaOggetto carta;
	
	/**Create the Spotlight Card action to perform, associating it to a game and to a player,
	 * and storing the sector in which the player wants to light up.
	 * 
	 * @param giocatore the player that performs the action.
	 * @param partita the game in which the player is playing.
	 * @param settore the sector wished to be lit up.
	 */
	public UsaLuci(StatoDiGioco partita,Giocatore giocatore,Settore settore){
		super(giocatore,partita);
		this.settore=settore;	
		this.mazzo=(MazzoOggetti) partita.getMazzoOggetti();
	}
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform Spotlight Card action;
	 * starting from the target sector, all the adjacent sectors are searched for players.
	 * In case there are players in this seven sectors, their position will be revealed
	 * to everyone.  
	 * 
	 * @return the {@code RispostaController} object with the response for the players.
	 */
	@Override
	public RispostaController esegui(){
		if(controlli()){
			carta=giocatore.getCarta(TipoCartaOggetto.LUCI);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
			List<String> risposte=new ArrayList<String>();
			vicini=model.getMappa().getMappa().get(settore.getCoordinate()).getVicini();
			List<Settore> viciniList=new ArrayList<Settore>(Arrays.asList(vicini));
			viciniList.add(settore);
			
			for(Giocatore g :  model.getGiocatori()){
				for(Settore s : viciniList){
					if(g.getPosizione()==s){
						risposte.add(g.getNome()+" si trova nel settore "+g.getPosizione().getCoordinate().toString());
					}	
				}
			}
		
			if(risposte.isEmpty())
				return new RispostaController("","Nessun giocatore presente nelle vicinanze");
			String risposta=new String("");
			for(String s : risposte){
				risposta=risposta.concat(s+"\n");
			}
			return new RispostaController("",risposta);
			
		}
		return new RispostaController("Mossa non valida",null);
	}
	
	@Override
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVO && giocatore.getStato()!=Stato.TURNOTERMINATO && giocatore instanceof Umano){
			try{
				if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.LUCI)) && !(model.getMappa().getMappa().get(settore.getCoordinate()) instanceof SettoreVuoto)){
				return true;
			} 
			return false;
			}catch(NullPointerException nu){
				return false;
			}
		}
		return false;
	}
	
}
