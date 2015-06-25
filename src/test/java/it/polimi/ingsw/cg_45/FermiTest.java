package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.SettorePartenzaAlieni;
import it.polimi.ingsw.cg_45.model.SettorePartenzaUmani;
import it.polimi.ingsw.cg_45.model.SettoreScialuppa;
import it.polimi.ingsw.cg_45.model.SettoreSicuro;
import it.polimi.ingsw.cg_45.model.SettoreVuoto;

import org.junit.Test;

public class FermiTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();

		
		
		assertTrue(mappa.getMappa().get(new Coordinate("L10")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.getMappa().get(new Coordinate("L09")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.getMappa().get(new Coordinate("N01")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("J01")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("N05")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("J05")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.getMappa().get(new Coordinate("K11")) instanceof SettoreSicuro);
		assertTrue(mappa.getMappa().get(new Coordinate("N06")) instanceof SettoreVuoto);
		
		assertTrue(mappa.mossaValida(mappa.getMappa().get(new Coordinate("H10")), mappa.getMappa().get(new Coordinate("K11")), 3));
		assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("H10")),mappa.getMappa().get(new Coordinate("K10")), 3));
		assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("H10")),mappa.getMappa().get(new Coordinate("L10")), 10));
		assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("H10")),mappa.getMappa().get(new Coordinate("H10")), 3));
		
		assertTrue(mappa.mossaValida(mappa.getMappa().get(new Coordinate("L06")), mappa.getMappa().get(new Coordinate("N05")), 5));
		

		
	}

}
