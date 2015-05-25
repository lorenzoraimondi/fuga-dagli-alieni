package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class FermiTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();

		
		
		assertTrue(mappa.mappa.get(new Coordinate("L10")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.mappa.get(new Coordinate("L09")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.mappa.get(new Coordinate("N01")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("J01")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("N05")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("J05")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.mappa.get(new Coordinate("K11")) instanceof SettoreSicuro);
		assertTrue(mappa.mappa.get(new Coordinate("N06")) instanceof SettoreVuoto);
		
		assertTrue(mappa.mossaValida(mappa.mappa.get(new Coordinate("H10")), mappa.mappa.get(new Coordinate("K11")), 3));
		assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("H10")),mappa.mappa.get(new Coordinate("K10")), 3));
		assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("H10")),mappa.mappa.get(new Coordinate("L10")), 10));
		assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("H10")),mappa.mappa.get(new Coordinate("H10")), 3));
		
		assertTrue(mappa.mossaValida(mappa.mappa.get(new Coordinate("L06")), mappa.mappa.get(new Coordinate("N05")), 5));
		

		
	}

}
