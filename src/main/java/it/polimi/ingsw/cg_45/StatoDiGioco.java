package it.polimi.ingsw.cg_45;

import java.util.List;

/**Represent an entire game with all of its attributes. Contains the players, the game map, and the
 * decks with all the cards.
 * 
 * @author Andrea Turconi
 *
 */
public class StatoDiGioco{
	//Lo usiamo?
	private int currentPlayer;
	
	/**
	 * @param the number of the actual turn. After 39 turns the game ends automatically.
	 */
	private int turno;
	
	/**
	 * @param the list of players, ordered by their sequence into the game turn.
	 */
	private List<Giocatore> giocatori;
	
	/**
	 * @param the game map in which the players have decided to play.
	 */
	private Mappa mappa;
	
	/**
	 * @param the Dangerous Sector Card's deck from which the players can draw cards.
	 */
	private MazzoSettori mazzosettori;
	
	/**
	 * @param the Item Card's deck from which the players can draw cards.
	 */
	private MazzoOggetti mazzooggetti;
	
	/**
	 * @param the Escape Hatch Card's deck from which the players can draw cards.
	 */
	private MazzoScialuppe mazzoscialuppe;
	
	/*
	public StatoDiGioco(){
		this.currentPlayer=1;
		this.turno=1;
		mazzosettori=new MazzoSettori();
		mazzooggetti=new MazzoOggetti();
		mazzoscialuppe=new MazzoScialuppe();
		giocatori=new ArrayList<Giocatore>();
	}*/
	
	/**Create a new game in the specified map and with the specified players and create the needed decks. 
	 * 
	 * @param giocatori the list of players that wish to play a game.
	 * @param mappa the map in which the players will play.
	 */
	public StatoDiGioco(List<Giocatore> giocatori,Mappa mappa){
		this.currentPlayer=1;
		this.turno=1;
		this.giocatori=giocatori;
		this.mappa=mappa;
		mazzosettori=new MazzoSettori();
		mazzooggetti=new MazzoOggetti();
		mazzoscialuppe=new MazzoScialuppe();
	}

	//LO USIAMO?
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void incrementCurrentPlayer(){
		if(currentPlayer<giocatori.size())
			this.currentPlayer++;
		else
			this.currentPlayer=1;
	}

	/**
	 * 
	 * @return the number of the current game turn.
	 */
	public int getTurno() {
		return turno;
	}

	/**Increments the game turn's number.
	 * 
	 */
	public void incrementTurno(){
		this.turno++;
	}
	/**
	 * 
	 * @return the player's list of the game.
	 */
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}
	//Per termina partita
	/**
	 * 
	 * @return the number of the Human players that are still playing.  
	 */
	public int numeroUmaniInGioco(){
		int count = 0;
		for(Giocatore g : giocatori){
			if(g instanceof Umano && (g.getSituazione()==Situazione.ATTIVO || g.getSituazione()==Situazione.INATTIVO)){
				count++;				
			}
		}
		return count;
	}	
	//
	

	/**
	 * 
	 * @param id the id number of the player to get from the player's list.
	 * @return if present in this game, the player with the specified id. 
	 */
	public Giocatore getGiocatore(int id){
		for(Giocatore g : giocatori){
			if(g.getID()==id){
				return g;
			}
		}
		return null;
		
	}

	/**
	 * 
	 * @return the game map in which the players are playing.
	 */
	public Mappa getMappa() {
		return mappa;
	}

	/**
	 * @return the Dangerous Sector Card's deck.
	 */
	public Mazzo getMazzoSettori() {
		return mazzosettori;
	}

	/**
	 * @return the Item Card's deck.
	 */
	public Mazzo getMazzoOggetti() {
		return mazzooggetti;
	}

	/**
	 * @return the Escape Hatch Card's deck.
	 */
	public Mazzo getMazzoScialuppe() {
		return mazzoscialuppe;
	}
	
	
}
