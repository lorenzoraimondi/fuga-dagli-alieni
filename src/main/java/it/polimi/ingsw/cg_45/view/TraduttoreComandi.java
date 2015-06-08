package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.controller.*;
import it.polimi.ingsw.cg_45.*;

import java.io.IOException;
import java.util.StringTokenizer;

public class TraduttoreComandi {
	private String comando;
	private int id;
	private Server server;
	//Aggiunto il client nel traduttore
	private Communicator client;
	public TraduttoreComandi(PacchettoAzione a, Server server, Communicator client){
		this.comando=a.getComando();
		this.id=a.getId();
		this.server=server;
		this.client=client;
	}
	
	public Object traduci() throws IOException{
		StringTokenizer s=new StringTokenizer(comando);
		String primaParola=s.nextToken();
		String terzaParola;
		String quintaParola;
		StatoDiGioco partita=server.getPartite().get(id);
		Giocatore giocatore;
		Coordinate coordinate;
		Settore settore;
		
		switch(primaParola){
		case "Scarto":
			giocatore=partita.getGiocatore(id);
			s.nextToken();
			terzaParola=s.nextToken();
			switch(terzaParola){
			case "adrenalina":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.ADRENALINA);
			case "luci":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.LUCI);
			case "difesa":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.DIFESA);
			case "sedativi":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.SEDATIVI);
			case "teletrasporto":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.TELETRASPORTO);
			case "attacco":
				return new ScartaCarta(giocatore,partita,TipoCartaOggetto.ATTACCO);
			}
		case "Silenzio":
			giocatore=partita.getGiocatore(id);
			return new AnnunciaRumore(partita,giocatore,null);
		case "Chat":
			return new Chat(comando.substring(5),id);
		case "Rumore":
			s.nextToken();
			terzaParola=s.nextToken();
			giocatore=partita.getGiocatore(id);
			return new AnnunciaRumore(partita,giocatore,terzaParola);
		case "Termino":
			giocatore=partita.getGiocatore(id);
			return new TerminaTurno(giocatore,partita);
		case "Scelgo":
			String scelta=s.nextToken();
			server.getSala().aggiungiGiocatore(scelta, client, server);
			return new RegistraClient(server,client);
		case "Attacco":
			s.nextToken();
			terzaParola=s.nextToken();
			coordinate=new Coordinate(terzaParola);
			settore=partita.getMappa().getMappa().get(coordinate);
			System.out.println("a");
			giocatore=partita.getGiocatore(id);
			System.out.println("b");
			return new Attacco(partita,giocatore,settore);
		case "Movimento":
			s.nextToken();
			terzaParola=s.nextToken();
			coordinate=new Coordinate(terzaParola);
			settore=partita.getMappa().getMappa().get(coordinate);
			giocatore=partita.getGiocatore(id);
			return new Movimento(partita,giocatore,settore);
		case "Pesca":
			giocatore=partita.getGiocatore(id);
			s.nextToken();
			terzaParola=s.nextToken();
			switch(terzaParola){
			case "oggetto":
				return new PescaOggetto(giocatore,partita);
			case "scialuppa":
				return new PescaScialuppa(giocatore,partita);
			case "settore":
				return new PescaSettore(giocatore,partita);
			}
		case "Usa":
			giocatore=partita.getGiocatore(id);
			s.nextToken();
			terzaParola=s.nextToken();
			switch(terzaParola){
			case "adrenalina":
				return new UsaAdrenalina(giocatore,partita);
			case "attacco":
				s.nextToken();
				quintaParola=s.nextToken();
				coordinate=new Coordinate(quintaParola);
				settore=partita.getMappa().getMappa().get(coordinate);
				return new UsaAttacco(partita,giocatore,settore);
			case "luci":
				s.nextToken();
				quintaParola=s.nextToken();
				coordinate=new Coordinate(quintaParola);
				settore=partita.getMappa().getMappa().get(coordinate);
				return new UsaLuci(partita,giocatore,settore);
			case "sedativi":
				return new UsaSedativi(giocatore,partita);
			case "teletrasporto":
				return new UsaTeletrasporto(giocatore,partita);
			}
			
			
		}
		return "Comando errato";
		
	}
}
