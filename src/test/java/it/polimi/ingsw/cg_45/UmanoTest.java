package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class UmanoTest {

	@Test
	public void test() {
		Mappa fermi=new Fermi();
		Mappa galvani=new Galvani();
		Mappa galilei=new Galilei();
		Giocatore u1=new Umano(1,1,fermi);
		Giocatore u2=new Umano(2,2,galvani);
		Giocatore u3=new Umano(3,3,galilei);
		
		assertEquals(1,u1.id);
		assertEquals(1,u1.ordine);
		assertEquals(1,u1.portata);
		assertEquals(u1.getSituazione(),Situazione.ATTIVONASCOSTO);
		assertEquals(u1.getStato(),Stato.INIZIO);
		
		assertEquals(fermi.mappa.get(new Coordinate("L10")), u1.getPosizione());
		assertEquals(galvani.mappa.get(new Coordinate("L08")), u2.getPosizione());
		assertEquals(galilei.mappa.get(new Coordinate("L08")), u3.getPosizione());
		
		

	}

}
