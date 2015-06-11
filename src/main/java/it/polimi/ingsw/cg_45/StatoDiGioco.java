package it.polimi.ingsw.cg_45;

import java.util.ArrayList;
import java.util.List;

public class StatoDiGioco{
	private int currentPlayer;
	private int turno;
	private List<Giocatore> giocatori;
	private Mappa mappa;
	private MazzoSettori mazzosettori;
	private MazzoOggetti mazzooggetti;
	private MazzoScialuppe mazzoscialuppe;
	
	public StatoDiGioco(){
		this.currentPlayer=1;
		this.turno=1;
		mazzosettori=new MazzoSettori();
		mazzooggetti=new MazzoOggetti();
		mazzoscialuppe=new MazzoScialuppe();
		giocatori=new ArrayList<Giocatore>();
	}
	
	public StatoDiGioco(ArrayList<Giocatore> giocatori,Mappa mappa){
		this.currentPlayer=1;
		this.turno=1;
		this.giocatori=giocatori;
		this.mappa=mappa;
		mazzosettori=new MazzoSettori();
		mazzooggetti=new MazzoOggetti();
		mazzoscialuppe=new MazzoScialuppe();
	}


	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void incrementCurrentPlayer(){
		if(currentPlayer<giocatori.size())
			this.currentPlayer++;
		else
			this.currentPlayer=1;
	}

	public int getTurno() {
		return turno;
	}

	public void incrementTurno(){
		this.turno++;
	}

	public List<Giocatore> getGiocatori() {
		return giocatori;
	}
	//Per termina partita
	public int numeroUmaniInGioco(){
		int count = 0;
		for(Giocatore g : giocatori){
			if(g instanceof Umano && (g.getSituazione()==Situazione.ATTIVONASCOSTO || g.getSituazione()==Situazione.INATTIVO)){
				count++;				
			}
		}
		return count;
	}	
	//
	

	public Giocatore getGiocatore(int id){
		for(Giocatore g : giocatori){
			if(g.getID()==id){
				return g;
			}
		}
		return null;
		
	}

	public Mappa getMappa() {
		return mappa;
	}


	public Mazzo getMazzoSettori() {
		return mazzosettori;
	}


	public Mazzo getMazzoOggetti() {
		return mazzooggetti;
	}


	public Mazzo getMazzoScialuppe() {
		return mazzoscialuppe;
	}
	
	
}
