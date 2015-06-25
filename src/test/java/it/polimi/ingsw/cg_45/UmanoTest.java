package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Galilei;
import it.polimi.ingsw.cg_45.model.Galvani;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.Umano;

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
		
		assertEquals(1,u1.getID());
		assertEquals(1,u1.getOrdine());
		assertEquals(1,u1.getPortata());
		assertEquals(u1.getSituazione(),Situazione.ATTIVO);
		assertEquals(u1.getStato(),Stato.INIZIO);
		
		assertEquals(fermi.getMappa().get(new Coordinate("L10")), u1.getPosizione());
		assertEquals(galvani.getMappa().get(new Coordinate("L08")), u2.getPosizione());
		assertEquals(galilei.getMappa().get(new Coordinate("L08")), u3.getPosizione());
		
		

	}

}
