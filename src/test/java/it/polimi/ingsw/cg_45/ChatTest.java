package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Chat;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class ChatTest {

	@Test
	public void test() throws IOException {
		Mappa mappa=new Fermi();
		Azione azione;
		RispostaController risp;
		
		Giocatore g1=new Umano(1,1,mappa,"g1");
		ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		StatoDiGioco partita=new StatoDiGioco(giocatori,mappa);

		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.INIZIO);
						
		azione=new Chat(g1,partita,"ciao");
		risp=azione.esegui();
		assertEquals(Stato.INIZIO,g1.getStato());
		assertTrue(risp.getMessaggioBroadcast().contains("ciao"));
		
		g1.setSituazione(Situazione.DISCONNESSO);
		azione=new Chat(g1,partita,"ciao");
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("Messaggio non inviato"));
	}

}
