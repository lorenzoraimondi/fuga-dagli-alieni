package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienoTest {

	@Test
	public void test() {
		Alieno a=new Alieno(1,1);
		assertEquals(a.id,1);
		assertEquals(a.ordine,1);
		assertEquals(a.portata,2);
		assertEquals(a.isHaUcciso(),false);
		
		a.setHaUcciso(true);
		assertEquals(a.isHaUcciso(),true);
		
	}

}
