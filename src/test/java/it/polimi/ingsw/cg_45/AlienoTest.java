package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienoTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Alieno a=new Alieno(1,1,mappa);
		assertEquals(1,a.id);
		assertEquals(1,a.ordine);
		assertEquals(2,a.portata);
		assertFalse(a.isHaUcciso());	
		
		a.setHaUcciso(true);
		assertTrue(a.isHaUcciso());
		
		assertEquals(a.getSituazione(),Situazione.ATTIVONASCOSTO);
		assertEquals(a.getStato(),Stato.INIZIO);
		
	}

}
