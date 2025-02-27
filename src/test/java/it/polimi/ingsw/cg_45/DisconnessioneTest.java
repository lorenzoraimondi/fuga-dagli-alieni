package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class DisconnessioneTest {

	@Test
	public void test() throws IOException {
		Mappa mappa=new Fermi();
		//ServerInterface server=new Server(1337);
		ServerInterface server=null;
		
		Giocatore g1=new Umano(1,1,mappa,"g1");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.INIZIO);
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		new Disconnessione(g1,partita,server).esegui();
		assertEquals(Stato.TURNOTERMINATO,g1.getStato());
		assertEquals(Situazione.DISCONNESSO,g1.getSituazione());
	}

}
