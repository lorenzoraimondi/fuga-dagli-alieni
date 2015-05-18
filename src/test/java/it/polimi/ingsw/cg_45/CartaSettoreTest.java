package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class CartaSettoreTest {

	@Test
	public void test() {
	 CartaSettore c1=new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,true);
	 CartaSettore c2=new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,false);
	 
	 assertEquals(c1.getTipo(),TipoCartaSettore.RUMOREQUALUNQUESETTORE);
	 assertNotEquals(c1.getTipo(),TipoCartaSettore.RUMORETUOSETTORE);
	 assertNotEquals(c1.getTipo(),TipoCartaSettore.SILENZIO);
	 assertEquals(c1.isOggetto(),true);
	 assertEquals(c2.isOggetto(),false);
	}

}
