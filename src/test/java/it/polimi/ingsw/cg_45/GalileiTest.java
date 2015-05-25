package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class GalileiTest {

	@Test
	public void test() {
	Mappa mappa= new Galilei();
	
	assertTrue(mappa.mappa.get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
	assertTrue(mappa.mappa.get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
	
	assertTrue(mappa.mappa.get(new Coordinate("B02")) instanceof SettoreScialuppa);
	assertTrue(mappa.mappa.get(new Coordinate("V02")) instanceof SettoreScialuppa);
	assertTrue(mappa.mappa.get(new Coordinate("B13")) instanceof SettoreScialuppa);
	assertTrue(mappa.mappa.get(new Coordinate("V13")) instanceof SettoreScialuppa);
	
	assertTrue(mappa.mappa.get(new Coordinate("S10")) instanceof SettoreVuoto);
	assertTrue(mappa.mappa.get(new Coordinate("F04")) instanceof SettorePericoloso);
	assertTrue(mappa.mappa.get(new Coordinate("N03")) instanceof SettoreSicuro);
	
	assertTrue(mappa.mossaValida(mappa.mappa.get(new Coordinate("G05")), mappa.mappa.get(new Coordinate("J06")), 4));
	assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("G05")), mappa.mappa.get(new Coordinate("J06")), 3));

	assertFalse(mappa.mossaValida(mappa.mappa.get(new Coordinate("A03")), mappa.mappa.get(new Coordinate("A03")), 5));
	}

}
