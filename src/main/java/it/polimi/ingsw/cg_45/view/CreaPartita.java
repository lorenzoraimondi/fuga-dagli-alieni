package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Alieno;
import it.polimi.ingsw.cg_45.Fermi;
import it.polimi.ingsw.cg_45.Galilei;
import it.polimi.ingsw.cg_45.Galvani;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Mappa;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.Stato;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class CreaPartita extends TimerTask {
	
	private List<Accettazione> giocatori=new ArrayList<Accettazione>();
	private List<BrokerThread> threadSubs=new ArrayList<BrokerThread>();
	private Server server;
	private String mappa;
	private Sala sala;
	private Mappa map;
	
	public CreaPartita(List<Accettazione> giocatori, Server server, String mappa,Sala sala){
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
		
		if(mappa.contentEquals("fermi"))
			 map=new Fermi();
		else if(mappa.contentEquals("galvani"))
			map=new Galvani();
		else
			map=new Galilei();
		
		for(Accettazione a : giocatori){
			if((i%2)==0){
				g=new Alieno(a.getId(),0,map,a.getNomeGiocatore());
				/*
				if(i==0)
					g.setSituazione(Situazione.ATTIVO);
					
				else
					g.setSituazione(Situazione.INATTIVO);
				*/
			}
			else
				g=new Umano(a.getId(),0,map,a.getNomeGiocatore());
			i++;
			players.add(g);
		}
		Collections.shuffle(players);
		int j=0;
		for(Giocatore g1 : players){
			g1.setOrdine(j);
			if(j==0){
				g1.setSituazione(Situazione.ATTIVO);
				g1.setStato(Stato.INIZIO);
			} else {
				g1.setSituazione(Situazione.INATTIVO);
				g1.setStato(Stato.TURNOTERMINATO);
			}
			j++;
		}
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) players, map);
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
		//server.addPartita(partita);
		
		for(Accettazione a : sala.getListaAccettazione(map)){
			threadSubs.add(a.getbt());
		}
		
		for(Giocatore g2 : players){
			
			//server.getPartite().put(g2.getID(), partita);
			server.getIdSub().put(g2.getID(), (ArrayList<BrokerThread>)threadSubs);
		}
		
		sala.svuotaLista(mappa);
		System.out.println("svuoto Lista");
		sala.cancelloTimer(mappa);
		String messaggio=new String("Partita Iniziata. Parte "+players.get(0).getNome()+".");
		server.publish(new Messaggio(messaggio),players.get(0).getID());
	}
		
}
