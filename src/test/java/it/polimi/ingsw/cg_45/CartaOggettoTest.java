package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CartaOggettoTest {

	@Test
	public void test() {
		CartaOggetto c=new CartaOggetto(TipoCartaOggetto.ADRENALINA);
		assertEquals(c.getTipo(),TipoCartaOggetto.ADRENALINA);
		assertNotEquals(c.getTipo(),TipoCartaOggetto.ATTACCO);
		assertNotEquals(c.getTipo(),TipoCartaOggetto.DIFESA);
		assertNotEquals(c.getTipo(),TipoCartaOggetto.LUCI);
		assertNotEquals(c.getTipo(),TipoCartaOggetto.SEDATIVI);
		assertNotEquals(c.getTipo(),TipoCartaOggetto.TELETRASPORTO);
		
	}

}
