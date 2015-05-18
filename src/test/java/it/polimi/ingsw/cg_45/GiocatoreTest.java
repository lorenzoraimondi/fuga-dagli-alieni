package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void test() {
		Alieno g=new Alieno(1,1);
		CartaOggetto[] c=new CartaOggetto[3];
		g.setOggetti(c);
		g.setPortata(5);
		assertTrue(g.getPortata()==5);
		assertTrue(g.getOggetti()==c);
	}

}
