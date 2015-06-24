package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.ScartaCarta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ScartaCartaTest {

	@Test
	public void test() throws IOException {
		Mappa mappa=new Fermi();
		Giocatore g1=new Umano(1,1,mappa,"g1");
		CartaOggetto carta1=new CartaOggetto(TipoCartaOggetto.ADRENALINA);
		CartaOggetto carta2=new CartaOggetto(TipoCartaOggetto.ATTACCO);
		CartaOggetto carta3=new CartaOggetto(TipoCartaOggetto.DIFESA);
		CartaOggetto carta4=new CartaOggetto(TipoCartaOggetto.SEDATIVI);
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.EFFETTOCONCLUSO);
		g1.setCarta(carta1);
		g1.setCarta(carta2);
		g1.setCarta(carta3);
		g1.setCarta(carta4);
		StatoDiGioco partita=new StatoDiGioco(giocatori,mappa);
		
		new ScartaCarta(g1,partita,TipoCartaOggetto.ADRENALINA).esegui();
		assertFalse(g1.getCarte().contains(carta1));
		
		new ScartaCarta(g1,partita,TipoCartaOggetto.ATTACCO).esegui();
		assertTrue(g1.getCarte().contains(carta2));
		
		
	}

}
