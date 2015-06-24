package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FermiTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();

		
		
		assertTrue(mappa.settori.get(new Coordinate("L10")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.settori.get(new Coordinate("L09")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.settori.get(new Coordinate("N01")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("J01")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("N05")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("J05")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.settori.get(new Coordinate("K11")) instanceof SettoreSicuro);
		assertTrue(mappa.settori.get(new Coordinate("N06")) instanceof SettoreVuoto);
		
		assertTrue(mappa.mossaValida(mappa.settori.get(new Coordinate("H10")), mappa.settori.get(new Coordinate("K11")), 3));
		assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("H10")),mappa.settori.get(new Coordinate("K10")), 3));
		assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("H10")),mappa.settori.get(new Coordinate("L10")), 10));
		assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("H10")),mappa.settori.get(new Coordinate("H10")), 3));
		
		assertTrue(mappa.mossaValida(mappa.settori.get(new Coordinate("L06")), mappa.settori.get(new Coordinate("N05")), 5));
		

		
	}

}
