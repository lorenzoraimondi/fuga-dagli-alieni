package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class SettoreScialuppaTest {

	@Test
	public void test() {
		SettoreScialuppa settore=new SettoreScialuppa(0,0,0);
		
		assertFalse(settore.isScoperta());
		settore.setScoperta();
		
		assertTrue(settore.isScoperta());
	}

}
