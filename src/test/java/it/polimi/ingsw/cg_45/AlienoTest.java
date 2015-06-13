package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlienoTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Mappa galvani=new Galvani();
		Mappa galilei=new Galilei();
		Giocatore a=new Alieno(1,1,mappa,"Lorenzo");
		Giocatore a2=new Alieno(2,2,galvani,"Andrea");
		Giocatore a3=new Alieno(3,3,galilei,"Francesco");
		
		assertEquals(1,a.id);
		assertEquals(1,a.ordine);
		assertEquals(2,a.portata);
		assertFalse(((Alieno) a).isHaUcciso());	
		
		((Alieno) a).setHaUcciso(true);
		assertTrue(((Alieno) a).isHaUcciso());
		
		assertEquals(mappa.mappa.get(new Coordinate("L09")), a.getPosizione());
		assertEquals(galvani.mappa.get(new Coordinate("L06")), a2.getPosizione());
		assertEquals(galilei.mappa.get(new Coordinate("L06")), a3.getPosizione());
		assertEquals(a.getSituazione(),Situazione.ATTIVO);
		assertEquals(a.getStato(),Stato.INIZIO);
		
		
	}

}
