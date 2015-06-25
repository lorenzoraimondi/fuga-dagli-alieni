package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Galilei;
import it.polimi.ingsw.cg_45.model.Galvani;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;

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
		
		assertEquals(1,a.getID());
		assertEquals(1,a.getOrdine());
		assertEquals(2,a.getPortata());
		//assertFalse(((Alieno) a).isHaUcciso());	
		
		//((Alieno) a).setHaUcciso(true);
		//assertTrue(((Alieno) a).isHaUcciso());
		
		assertEquals(mappa.getMappa().get(new Coordinate("L09")), a.getPosizione());
		assertEquals(galvani.getMappa().get(new Coordinate("L06")), a2.getPosizione());
		assertEquals(galilei.getMappa().get(new Coordinate("L06")), a3.getPosizione());
		assertEquals(a.getSituazione(),Situazione.ATTIVO);
		assertEquals(a.getStato(),Stato.INIZIO);
		
		
	}

}
