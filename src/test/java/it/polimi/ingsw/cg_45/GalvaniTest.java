package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GalvaniTest {

	@Test
	public void test() {
		Mappa mappa=new Galvani();
		
		assertTrue(mappa.mappa.get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.mappa.get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.mappa.get(new Coordinate("F01")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("P01")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("B10")) instanceof SettoreScialuppa);
		assertTrue(mappa.mappa.get(new Coordinate("V11")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.mappa.get(new Coordinate("Q06")) instanceof SettorePericoloso);
		assertTrue(mappa.mappa.get(new Coordinate("L04")) instanceof SettoreVuoto);
		assertTrue(mappa.mappa.get(new Coordinate("O14")) instanceof SettoreSicuro);
		
		assertTrue(mappa.mossaValida(mappa.mappa.get(new Coordinate("L05")),mappa.mappa.get(new Coordinate("L03")),3));
		assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("L05")),mappa.mappa.get(new Coordinate("L03")),2));
	}

}
