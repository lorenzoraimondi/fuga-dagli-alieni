package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class FermiTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();

		assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(6,1,-7)), mappa.mappa.get(new Coordinate(4,3,-7)), 2),false);
		assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(8,5,-13)), mappa.mappa.get(new Coordinate(6,7,-13)), 3),true);
		assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(4,3,-7)), mappa.mappa.get(new Coordinate(2,5,-7)), 2),false);
		assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(6,4,-10)), mappa.mappa.get(new Coordinate(7,3,-10)), 14),false);
		assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(4,6,-10)), mappa.mappa.get(new Coordinate(4,7,-11)), 2),false);
	}

}
