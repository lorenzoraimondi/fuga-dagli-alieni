package it.polimi.ingsw.cg_45.netCommons;

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
import it.polimi.ingsw.cg_45.controller.ScartaCarta;
import it.polimi.ingsw.cg_45.controller.Statistiche;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.controller.UsaAdrenalina;
import it.polimi.ingsw.cg_45.controller.UsaLuci;
import it.polimi.ingsw.cg_45.controller.UsaSedativi;
import it.polimi.ingsw.cg_45.controller.UsaTeletrasporto;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**Represent a command translator. From the command line written by the player is built an
 * action that the server can execute.
 *  
 * @author Lorenzo Raimondi
 *
 */
public class TraduttoreComandi {
	
	private String comando;
	private int id;
	private ServerInterface server;	
	
	/**Create a translator by the player's command. From the {@link PacchettoAzione} created from the client
	 * is triggered out the command and the player's id, in way to create an action linked to the
	 * resources it will use and modify.  
	 * 
	 * @param azione the command object received from a client.
	 * @param server the server in which find action-related resources.
	 */
	public TraduttoreComandi(PacchettoAzione azione, ServerInterface server){
		this.comando=azione.getComando();
		this.id=azione.getId();
		this.server=server;
		
	}
	
	/**This method translate the command and creates the action. From the {@code PacchettoAzione} object
	 * received it gets player's {@code id}, used to find the related game and player; this, in 
	 * addiction with the command string, creates the right action requested from the client,
	 * ready to be executed.
	 * 
	 * @return the required action's class ready to be executed.
	 * @throws IOException
	 */
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
		String nome;
		
		switch(primaParola){
		case "?":
			giocatore=partita.getGiocatore(id);
			return new Statistiche(giocatore,partita);
		case "scarto":
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
			default:
			}
			return "Comando errato";
		case "silenzio":
			giocatore=partita.getGiocatore(id);
			return new AnnunciaRumore(partita,giocatore,null);
		case "chat":
			giocatore=partita.getGiocatore(id);
			return new Chat(giocatore,partita,comando.substring(5));
		case "rumore":
			s.nextToken();
			terzaParola=s.nextToken().toUpperCase();
			if(terzaParola.length()!=3)
				return "comando errato";
			giocatore=partita.getGiocatore(id);
			return new AnnunciaRumore(partita,giocatore,terzaParola);
		case "termino":
			giocatore=partita.getGiocatore(id);
			return new TerminaTurno(giocatore,partita,server);
		case "attacco":
			giocatore=partita.getGiocatore(id);
			settore=partita.getMappa().getMappa().get(giocatore.getPosizione().getCoordinate());
			return new Attacco(partita,giocatore,settore,server);
		case "movimento":
			s.nextToken();
			terzaParola=s.nextToken().toUpperCase();
			if(terzaParola.length()!=3)
				return "comando errato";
			coordinate=new Coordinate(terzaParola);
			settore=partita.getMappa().getMappa().get(coordinate);
			giocatore=partita.getGiocatore(id);
			return new Movimento(partita,giocatore,settore);
		case "pesca":
			giocatore=partita.getGiocatore(id);
			s.nextToken();
			terzaParola=s.nextToken();
			switch(terzaParola){
			case "oggetto":
				return new PescaOggetto(giocatore,partita);
			case "scialuppa":
				return new PescaScialuppa(giocatore,partita,server);
			case "settore":
				return new PescaSettore(giocatore,partita);
			default: 
			}
			return "Comando errato";
		case "usa":
			giocatore=partita.getGiocatore(id);
			s.nextToken();
			terzaParola=s.nextToken();
			switch(terzaParola){
			case "adrenalina":
				return new UsaAdrenalina(giocatore,partita);
			case "attacco":
				settore=partita.getMappa().getMappa().get(giocatore.getPosizione().getCoordinate());
				return new Attacco(partita,giocatore,settore,server);
			case "luci":
				s.nextToken();
				quintaParola=s.nextToken().toUpperCase();
				coordinate=new Coordinate(quintaParola);
				settore=partita.getMappa().getMappa().get(coordinate);
				return new UsaLuci(partita,giocatore,settore);
			case "sedativi":
				return new UsaSedativi(giocatore,partita);
			case "teletrasporto":
				return new UsaTeletrasporto(giocatore,partita);
			default:	
			}
			return "Comando errato";
		case "exit":
				giocatore=partita.getGiocatore(id);
				return new Disconnessione(giocatore,partita,server);
		default:
			return "Comando errato";
		}
		} catch(NoSuchElementException n){
			return "Comando errato";
		} catch(NullPointerException e) {
			return "Comando errato";
		}
		
	}
}
