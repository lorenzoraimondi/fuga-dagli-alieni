package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.Carta;
import it.polimi.ingsw.cg_45.CartaOggetto;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Represent the attack performing, for an alien that attacks or a human that uses an attack card.
 * This class permit to verify if the player can perform the action and in case perform it, communicating
 * a response to the client player and also the other ones.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Attacco extends Azione{
	
	private Settore settore;
	private Carta carta;
	private ServerInterface server;
	
	/**Create the action to perform, associating it to a game and to a player, and storing
	 * the sector in which the player wants to attack and the server in which the game resides.
	 * 
	 * @param p the player that performs the action.
	 * @param g the game in which the player is playing.
	 * @param s the sector in which the player wants to attack.
	 * @param server the server in which the game resides.
	 */
	public Attacco(StatoDiGioco p, Giocatore g, Settore s, ServerInterface server){
		super(g,p);
		this.settore=s;
		this.server=server;
	}
	
	
	/**Execute the action, after checking its possibility.
	 * <p> 
	 * Depending on the player turn status, this method perform an attack in the chosen sector; if a human player
	 * in the target sector has the Defense card it will be used to save him. If the attack caused at least a human
	 * die, will be verified if the game is still on course or if it has finished.
	 * The method then return the responses for the client and all the other players.
	 * 
	 * @return the {@code RispostaController} object with the responses for the players.
	 */
	@Override
	public RispostaController esegui() throws IOException{
		//IOEx aggiunta per terminaPartita
		List<String> risposte=new ArrayList<String>();
		
		if(controlli()){
			String rispostaCarta=new String();
			if(giocatore instanceof Umano){
				carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
				giocatore.getCarte().remove(carta);
				model.getMazzoOggetti().getMazzoScarti().add(carta);
				rispostaCarta=new String(giocatore.getNome()+" usa carta Attacco\n");
			}
			for(Giocatore g : model.getGiocatori()){
				if(g!=giocatore && g.getPosizione()==settore){
					if(g instanceof Umano && (g.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)))){
						//new UsaDifesa(g,model).esegui();
						//risposte.add(g.getNome()+" ha usato la carta Difesa");
						RispostaController rispostaDifesa = new UsaDifesa(g,model).esegui();
						risposte.add(rispostaDifesa.getMessaggioBroadcast());
					} else if(g.getSituazione()!=Situazione.MORTO){
						g.setSituazione(Situazione.MORTO);
						g.setStato(Stato.TURNOTERMINATO);
						if(g instanceof Umano && giocatore instanceof Alieno){
							giocatore.setPortata(3);
						}
						if(!g.getCarte().isEmpty()){
							model.getMazzoOggetti().getMazzoScarti().addAll(g.getCarte());
							g.getCarte().removeAll(g.getCarte());							
						}
						risposte.add(g.getNome()+", "+g.getClass().getSimpleName()+", è morto!");
						//return new RispostaController("Attacco riuscito!","Il giocatore "+g.getID()+","+g.getClass().getName()+", è morto!");
					}
					}
			}
			giocatore.setStato(Stato.ATTACCATO);
			
			if(risposte.isEmpty())
				//da togliere hai fallito
				return new RispostaController("Hai fallito",rispostaCarta+"Attacco in "+settore.getCoordinate().toString()+" fallito!");
			String risposta=new String(rispostaCarta+"Attacco in "+settore.getCoordinate().toString()+"\n");
			for(String s : risposte){
				risposta=risposta.concat(s+"\n");
			}
			//Giocatore inutile, riesco a overrideare costruttore?
			RispostaController terminaPartita=new TerminaPartita(giocatore,model,server).esegui();
			if(terminaPartita!=null){
				return new RispostaController("",risposta+terminaPartita.getMessaggioBroadcast());
			}
			return new RispostaController("",risposta);
		}
		
		return new RispostaController("Mossa non valida",null);
	}
	
	/**This methods checks if the attack action can be performed from the player, basing the controls
	 * over player's game and turn state and the target sector. Also, if the attacking player is human, is required
	 * the Attack card.
	 * 
	 * @return {@code true} if the player can perform the action, {@code false} otherwise.
	 */
	@Override
	protected boolean controlli(){		
		if(giocatore.getPosizione().getCoordinate().equals(settore.getCoordinate()) && (stato==Stato.PERICOLO || stato==Stato.SICURO) && giocatore.getSituazione()==Situazione.ATTIVO){
					if(giocatore instanceof Alieno){
						return true;
					} else {
						if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
							return true;
						} 
					}
				}
				return false;
			/*}
			else {
				if(stato!=Stato.INIZIO && stato!=Stato.SCIALUPPA && stato!=Stato.CARTASCIALUPPA){
					
					return true;
				}
									
				return false;
			}	*/	
	}
	
	//Per test
	/*
		public Settore getSettore() {
			return settore;
		}
		public Giocatore getGiocatore() {
			return giocatore;
		}
		public StatoDiGioco getPartita() {
			return model;
		}
		
		//
		 
		 */
}
