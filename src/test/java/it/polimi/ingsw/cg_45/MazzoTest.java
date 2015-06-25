package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Mazzo;
import it.polimi.ingsw.cg_45.model.MazzoOggetti;

import org.junit.Test;

public class MazzoTest {

	@Test
	public void test() {
		
		Mazzo mazzo=new MazzoOggetti();
	
		CartaOggetto carta1=(CartaOggetto) mazzo.pescaCarta();
		assertTrue(carta1 instanceof CartaOggetto);
		
		mazzo.getMazzoScarti().addAll(mazzo.getMazzoIniziale());
		mazzo.getMazzoIniziale().removeAll(mazzo.getMazzoIniziale());
		
		CartaOggetto carta2=(CartaOggetto) mazzo.pescaCarta();
		assertTrue(carta2 instanceof CartaOggetto);
		assertEquals(12,mazzo.getNumCarte());
		
		mazzo.getMazzoScarti().addAll(mazzo.getMazzoIniziale());
		mazzo.getMazzoIniziale().removeAll(mazzo.getMazzoIniziale());
		mazzo.getMazzoScarti().removeAll(mazzo.getMazzoScarti());
	
		assertEquals(null,mazzo.pescaCarta());
	}

}
