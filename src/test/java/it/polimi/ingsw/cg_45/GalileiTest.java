package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class GalileiTest {

	@Test
	public void test() {
	Mappa mappa= new Galilei();
	
	assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(0,1,-1)), mappa.mappa.get(new Coordinate(0,2,-2)), 2),true);
	assertEquals(mappa.mossaValida(mappa.mappa.get(new Coordinate(0,1,-1)), mappa.mappa.get(new Coordinate(0,5,-5)), 2),false);
	}

}
