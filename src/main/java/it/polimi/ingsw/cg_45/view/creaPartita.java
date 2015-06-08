package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class creaPartita extends TimerTask {
	
	private List<Accettazione> giocatori=new ArrayList<Accettazione>();
	private ArrayList<BrokerThread> threadSubs=new ArrayList<BrokerThread>();
	private Server server;
	private String mappa;
	private Sala sala;
	private Mappa map;
	
	public creaPartita(List<Accettazione> giocatori, Server server, String mappa,Sala sala){
		this.mappa=mappa;
		this.giocatori=giocatori;
		this.server=server;
		this.sala=sala;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i=0;
		Giocatore g;
		List<Giocatore> players=new ArrayList<Giocatore>();
		
		if(mappa.contentEquals("Fermi"))
			 map=new Fermi();
		else if(mappa.contentEquals("Galvani"))
			map=new Galvani();
		else
			map=new Galilei();
		//Cambiare algoritmo inserimento giocatori alieni/umani
		for(Accettazione a : giocatori){
			if((i%2)==0){
				g=new Alieno(a.getId(),0,map);
				/*
				if(i==0)
					g.setSituazione(Situazione.ATTIVONASCOSTO);
					
				else
					g.setSituazione(Situazione.INATTIVO);
				*/
			}
			else
				g=new Umano(a.getId(),0,map);
			i++;
			players.add(g);
		}
		Collections.shuffle(players);
		int j=0;
		for(Giocatore g1 : players){
			
			g1.setOrdine(j);
			if(j==0){
				g1.setSituazione(Situazione.ATTIVONASCOSTO);
				g1.setStato(Stato.INIZIO);
			} else {
				g1.setSituazione(Situazione.INATTIVO);
				g1.setStato(Stato.TURNOTERMINATO);
			}
			j++;
		}
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) players,map);
		System.out.println("creo partita");
		for(Giocatore p: players){
			server.getPartite().put(p.getID(), partita);
			//
			if(p instanceof Alieno)
				System.out.println("alieno"+p.getID()+" "+p.getStato()+" "+p.getSituazione());
				
			else
				System.out.println("umano"+p.getID()+" "+p.getStato()+" "+p.getSituazione());
			//
		}
		server.addPartita(partita);
		int k=0;
		
		for(Accettazione a : sala.getListaAccettazione(map)){
			threadSubs.add(a.getbt());
		}
		
		for(Giocatore g2 : players){
			
			server.getPartite().put(g2.getID(), partita);
			server.getIdSub().put(g2.getID(), threadSubs);
		}
		
		sala.svuotaLista(mappa);
		System.out.println("svuoto Lista");
		sala.cancelloTimer(mappa);
		String messaggio=new String("Partita Iniziata. Parte il giocatore "+players.get(0).getID()+" .");
		server.publish(new Messaggio(messaggio),players.get(0).getID());
	}
		
}
