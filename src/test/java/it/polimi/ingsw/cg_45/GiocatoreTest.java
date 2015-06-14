package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Alieno g=new Alieno(1,1,mappa,"Lorenzo");
		CartaOggetto c=new CartaOggetto(TipoCartaOggetto.ADRENALINA);
		CartaOggetto d=new CartaOggetto(TipoCartaOggetto.ATTACCO);
		
		g.setCarta(c);
		g.setPortata(5);
		assertTrue(g.getPortata()==5);
		assertTrue(g.getCarte().contains(c));
		assertFalse(g.getCarte().contains(d));
		assertEquals(g.getID(),1);
		assertEquals(g.getOrdine(),1);
		
	}

}
