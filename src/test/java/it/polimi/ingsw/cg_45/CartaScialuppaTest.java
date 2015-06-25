package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import it.polimi.ingsw.cg_45.model.CartaScialuppa;
import it.polimi.ingsw.cg_45.model.TipoCartaScialuppa;

import org.junit.Test;

public class CartaScialuppaTest {

	@Test
	public void test() {
		CartaScialuppa c1=new CartaScialuppa(TipoCartaScialuppa.ROSSA);
		CartaScialuppa c2=new CartaScialuppa(TipoCartaScialuppa.VERDE);
		/*c1.blocca();
		assertTrue(c1.isBlocked());
		assertFalse(c2.isBlocked());*/
		assertEquals(c1.getTipo(),TipoCartaScialuppa.ROSSA);
		assertEquals(c2.getTipo(),TipoCartaScialuppa.VERDE);
		assertNotEquals(c1.getTipo(),TipoCartaScialuppa.VERDE);
		assertNotEquals(c2.getTipo(),TipoCartaScialuppa.ROSSA);
	}

}
