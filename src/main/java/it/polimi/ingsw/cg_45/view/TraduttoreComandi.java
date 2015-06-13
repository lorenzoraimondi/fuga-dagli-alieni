package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Coordinate;
import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Settore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.controller.AnnunciaRumore;
import it.polimi.ingsw.cg_45.controller.Attacco;
import it.polimi.ingsw.cg_45.controller.Chat;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.controller.Movimento;
import it.polimi.ingsw.cg_45.controller.PescaOggetto;
import it.polimi.ingsw.cg_45.controller.PescaScialuppa;
import it.polimi.ingsw.cg_45.controller.PescaSettore;
import it.polimi.ingsw.cg_45.controller.RegistraClient;
import it.polimi.ingsw.cg_45.controller.ScartaCarta;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.controller.UsaAdrenalina;
import it.polimi.ingsw.cg_45.controller.UsaLuci;
import it.polimi.ingsw.cg_45.controller.UsaSedativi;
import it.polimi.ingsw.cg_45.controller.UsaTeletrasporto;

import java.io.IOException;
import java.util.NoSuchElementException;
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
		try{
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
			giocatore=partita.getGiocatore(id);
			return new Chat(comando.substring(5),giocatore);
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
			String nome=s.nextToken();
			//server.getSala().aggiungiGiocatore(scelta, client, server);
			//return new RegistraClient(server,client);
			return new RegistraClient(server,client,scelta,nome);
		case "Attacco":
			s.nextToken();
			terzaParola=s.nextToken();
			coordinate=new Coordinate(terzaParola);
			settore=partita.getMappa().getMappa().get(coordinate);
			giocatore=partita.getGiocatore(id);
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
				return new Attacco(partita,giocatore,settore);
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
		case "exit":
				giocatore=partita.getGiocatore(id);
				return new Disconnessione(giocatore,partita);
			
		}
		} catch(NoSuchElementException n){
			return "Comando errato";
		}
		return "Comando errato";
		
	}
}
