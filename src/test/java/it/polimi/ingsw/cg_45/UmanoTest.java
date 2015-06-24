package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UmanoTest {

	@Test
	public void test() {
		Mappa fermi=new Fermi();
		Mappa galvani=new Galvani();
		Mappa galilei=new Galilei();
		Giocatore u1=new Umano(1,1,fermi,"Lorenzo");
		Giocatore u2=new Umano(2,2,galvani,"Andrea");
		Giocatore u3=new Umano(3,3,galilei,"Giovanni");
		
		assertEquals(1,u1.id);
		assertEquals(1,u1.ordine);
		assertEquals(1,u1.portata);
		assertEquals(u1.getSituazione(),Situazione.ATTIVO);
		assertEquals(u1.getStato(),Stato.INIZIO);
		
		assertEquals(fermi.settori.get(new Coordinate("L10")), u1.getPosizione());
		assertEquals(galvani.settori.get(new Coordinate("L08")), u2.getPosizione());
		assertEquals(galilei.settori.get(new Coordinate("L08")), u3.getPosizione());
		
		

	}

}
