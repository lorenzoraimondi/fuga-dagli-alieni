package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazzoScialuppeTest {

	@Test
	public void test() {
		MazzoScialuppe mazzo=new MazzoScialuppe();
		CartaScialuppa carta=(CartaScialuppa) mazzo.pescaCarta();
		
		assertTrue(carta instanceof CartaScialuppa);
		
		mazzo.getMazzoScarti().addAll(mazzo.getMazzoIniziale());
		mazzo.getMazzoIniziale().removeAll(mazzo.getMazzoIniziale());
		
		CartaScialuppa carta2=(CartaScialuppa) mazzo.pescaCarta();
		assertTrue(carta2 instanceof CartaScialuppa);
		
	}

}
