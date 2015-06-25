package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.UsaTeletrasporto;
import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

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
