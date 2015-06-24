package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Alieno g=new Alieno(1,1,mappa,"Lorenzo");
		CartaOggetto c=new CartaOggetto(TipoCartaOggetto.ADRENALINA);
		
		g.setCarta(c);
		g.setPortata(5);
		assertTrue(g.getPortata()==5);
		assertTrue(g.getCarte().contains(c));
		assertEquals(null,g.getCarta(TipoCartaOggetto.ATTACCO));
		assertEquals(g.getID(),1);
		assertEquals(g.getOrdine(),1);
		
		g.setOrdine(4);
		assertEquals(4,g.getOrdine());
		
		
		
	}

}
