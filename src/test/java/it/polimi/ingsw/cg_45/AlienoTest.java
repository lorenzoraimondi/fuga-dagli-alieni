package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienoTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		Giocatore a=new Alieno(1,1,mappa);
		assertEquals(1,a.id);
		assertEquals(1,a.ordine);
		assertEquals(2,a.portata);
		assertFalse(((Alieno) a).isHaUcciso());	
		
		((Alieno) a).setHaUcciso(true);
		assertTrue(((Alieno) a).isHaUcciso());
		
		
		assertEquals(mappa.mappa.get(new Coordinate("L09")), a.getPosizione());
		
		assertEquals(a.getSituazione(),Situazione.ATTIVONASCOSTO);
		assertEquals(a.getStato(),Stato.INIZIO);
		
		
	}

}
