package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GalvaniTest {

	@Test
	public void test() {
		Mappa mappa=new Galvani();
		
		assertTrue(mappa.settori.get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.settori.get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.settori.get(new Coordinate("F01")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("P01")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("B10")) instanceof SettoreScialuppa);
		assertTrue(mappa.settori.get(new Coordinate("V11")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.settori.get(new Coordinate("Q06")) instanceof SettorePericoloso);
		assertTrue(mappa.settori.get(new Coordinate("L04")) instanceof SettoreVuoto);
		assertTrue(mappa.settori.get(new Coordinate("O14")) instanceof SettoreSicuro);
		
		assertTrue(mappa.mossaValida(mappa.settori.get(new Coordinate("L05")),mappa.settori.get(new Coordinate("L03")),3));
		assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("L05")),mappa.settori.get(new Coordinate("L03")),2));
	}

}
