package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.UsaTeletrasporto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UsaTeletrasportoTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Giocatore g1=new Umano(1,1,mappa,"g1");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.INIZIO);
		CartaOggetto c=new CartaOggetto(TipoCartaOggetto.TELETRASPORTO);
		StatoDiGioco partita=new StatoDiGioco(giocatori,mappa);
		g1.setCarta(c);
		
		g1.setPosizione(mappa.getMappa().get(new Coordinate("L06")));
		new UsaTeletrasporto(g1,partita).esegui();
		assertFalse(g1.getCarte().contains(c));
		assertEquals(mappa.getMappa().get(new Coordinate("L10")),g1.getPosizione());
		
		g1.setPosizione(mappa.getMappa().get(new Coordinate("M11")));
		new UsaTeletrasporto(g1,partita).esegui();
		assertFalse(g1.getCarte().contains(c));
		assertEquals(mappa.getMappa().get(new Coordinate("M11")),g1.getPosizione());
		
		g1.setStato(Stato.TURNOTERMINATO);
		g1.setCarta(c);
		g1.setPosizione(mappa.getMappa().get(new Coordinate("L06")));
		new UsaTeletrasporto(g1,partita).esegui();
		assertTrue(g1.getCarte().contains(c));
		assertEquals(mappa.getMappa().get(new Coordinate("L06")),g1.getPosizione());
		
	}

}
