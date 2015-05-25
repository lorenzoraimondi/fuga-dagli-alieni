package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Alieno g=new Alieno(1,1,mappa);
		CartaOggetto c=new CartaOggetto(TipoCartaOggetto.ADRENALINA);
		g.setCarta(c);
		g.setPortata(5);
		assertTrue(g.getPortata()==5);
		assertTrue(g.getCarte().contains(c));
		
	}

}
