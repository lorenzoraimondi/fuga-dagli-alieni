package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Galilei;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.SettorePartenzaAlieni;
import it.polimi.ingsw.cg_45.model.SettorePartenzaUmani;
import it.polimi.ingsw.cg_45.model.SettorePericoloso;
import it.polimi.ingsw.cg_45.model.SettoreScialuppa;
import it.polimi.ingsw.cg_45.model.SettoreSicuro;
import it.polimi.ingsw.cg_45.model.SettoreVuoto;

import org.junit.Test;

public class GalileiTest {

	@Test
	public void test() {
	Mappa mappa= new Galilei();
	
	assertTrue(mappa.getMappa().get(new Coordinate("L08")) instanceof SettorePartenzaUmani);
	assertTrue(mappa.getMappa().get(new Coordinate("L06")) instanceof SettorePartenzaAlieni);
	
	assertTrue(mappa.getMappa().get(new Coordinate("B02")) instanceof SettoreScialuppa);
	assertTrue(mappa.getMappa().get(new Coordinate("V02")) instanceof SettoreScialuppa);
	assertTrue(mappa.getMappa().get(new Coordinate("B13")) instanceof SettoreScialuppa);
	assertTrue(mappa.getMappa().get(new Coordinate("V13")) instanceof SettoreScialuppa);
	
	assertTrue(mappa.getMappa().get(new Coordinate("S10")) instanceof SettoreVuoto);
	assertTrue(mappa.getMappa().get(new Coordinate("F04")) instanceof SettorePericoloso);
	assertTrue(mappa.getMappa().get(new Coordinate("N03")) instanceof SettoreSicuro);
	
	assertTrue(mappa.mossaValida(mappa.getMappa().get(new Coordinate("G05")), mappa.getMappa().get(new Coordinate("J06")), 4));
	assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("G05")), mappa.getMappa().get(new Coordinate("J06")), 3));

	assertFalse(mappa.mossaValida(mappa.getMappa().get(new Coordinate("A03")), mappa.getMappa().get(new Coordinate("A03")), 5));
	}

}
