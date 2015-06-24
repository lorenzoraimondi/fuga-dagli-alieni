package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GalileiTest {

	@Test
	public void test() {
	Mappa mappa= new Galilei();
	
	assertTrue(mappa.settori.get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
	assertTrue(mappa.settori.get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
	
	assertTrue(mappa.settori.get(new Coordinate("B02")) instanceof SettoreScialuppa);
	assertTrue(mappa.settori.get(new Coordinate("V02")) instanceof SettoreScialuppa);
	assertTrue(mappa.settori.get(new Coordinate("B13")) instanceof SettoreScialuppa);
	assertTrue(mappa.settori.get(new Coordinate("V13")) instanceof SettoreScialuppa);
	
	assertTrue(mappa.settori.get(new Coordinate("S10")) instanceof SettoreVuoto);
	assertTrue(mappa.settori.get(new Coordinate("F04")) instanceof SettorePericoloso);
	assertTrue(mappa.settori.get(new Coordinate("N03")) instanceof SettoreSicuro);
	
	assertTrue(mappa.mossaValida(mappa.settori.get(new Coordinate("G05")), mappa.settori.get(new Coordinate("J06")), 4));
	assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("G05")), mappa.settori.get(new Coordinate("J06")), 3));

	assertFalse(mappa.mossaValida(mappa.settori.get(new Coordinate("A03")), mappa.settori.get(new Coordinate("A03")), 5));
	}

}
