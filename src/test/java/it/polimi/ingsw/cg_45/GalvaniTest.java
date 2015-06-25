package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Galvani;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.SettorePartenzaAlieni;
import it.polimi.ingsw.cg_45.model.SettorePartenzaUmani;
import it.polimi.ingsw.cg_45.model.SettorePericoloso;
import it.polimi.ingsw.cg_45.model.SettoreScialuppa;
import it.polimi.ingsw.cg_45.model.SettoreSicuro;
import it.polimi.ingsw.cg_45.model.SettoreVuoto;

import org.junit.Test;

public class GalvaniTest {

	@Test
	public void test() {
		Mappa mappa=new Galvani();
		
		assertTrue(mappa.getMappa().get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
		assertTrue(mappa.getMappa().get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
		
		assertTrue(mappa.getMappa().get(new Coordinate("F01")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("P01")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("B10")) instanceof SettoreScialuppa);
		assertTrue(mappa.getMappa().get(new Coordinate("V11")) instanceof SettoreScialuppa);
		
		assertTrue(mappa.getMappa().get(new Coordinate("Q06")) instanceof SettorePericoloso);
		assertTrue(mappa.getMappa().get(new Coordinate("L04")) instanceof SettoreVuoto);
		assertTrue(mappa.getMappa().get(new Coordinate("O14")) instanceof SettoreSicuro);
		
		assertTrue(mappa.mossaValida(mappa.getMappa().get(new Coordinate("L05")),mappa.getMappa().get(new Coordinate("L03")),3));
		assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("L05")),mappa.getMappa().get(new Coordinate("L03")),2));
	}

}
