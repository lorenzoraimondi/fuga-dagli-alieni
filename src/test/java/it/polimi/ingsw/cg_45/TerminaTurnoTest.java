package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaTurno;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.view.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TerminaTurnoTest {

	@Test
	public void test() throws IOException {
		Mappa mappa=new Fermi();
		ServerInterface server=new Server(29999);
		RispostaController risp;
		Giocatore g1=new Umano(1,1,mappa,"g1");
		Giocatore g2=new Alieno(2,2,mappa,"g2");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.ATTACCATO);
		g1.setPortata(2);
		giocatori.add(g2);
		g2.setSituazione(Situazione.INATTIVO);
		g2.setStato(Stato.TURNOTERMINATO);
		StatoDiGioco partita=new StatoDiGioco(giocatori,mappa);
		
		new TerminaTurno(g1,partita,server).esegui();
		assertEquals(Stato.TURNOTERMINATO,g1.getStato());
		assertEquals(Situazione.INATTIVO,g1.getSituazione());
		assertEquals(Stato.INIZIO,g2.getStato());
		assertEquals(Situazione.ATTIVO,g2.getSituazione());
		assertEquals(1,g1.getPortata());
		
		g1.setSituazione(Situazione.MORTO);
		g2.setStato(Stato.EFFETTOCONCLUSO);
		new TerminaTurno(g2,partita,server).esegui();
		assertEquals(Situazione.ATTIVO,g2.getSituazione());
		assertEquals(Stato.INIZIO,g2.getStato());
		
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.EFFETTOCONCLUSO);
		g2.setStato(Stato.TURNOTERMINATO);
		g2.setSituazione(Situazione.INATTIVO);
		new TerminaTurno(g1,partita,server).esegui();
		assertEquals(Stato.EFFETTOCONCLUSO,g1.getStato());
		assertEquals(Situazione.ATTIVO,g1.getSituazione());
		
		g1.getCarte().remove(0);
		for(int i=1;i<37;i++){
			partita.incrementTurno();
		}
		risp=new TerminaTurno(g1,partita,server).esegui();
		assertTrue(risp.getMessaggioBroadcast().contains("Partita terminata!"));
	}

}
