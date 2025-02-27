package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.model.Coordinate;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void test() {
		Coordinate c1=new Coordinate(1,2,3);
		Coordinate c2=new Coordinate(1,2,3);
		Coordinate c3=new Coordinate(0,0,0);
		Coordinate c4=new Coordinate(1,2,4);
		assertEquals(c1.getX(),1);
		assertEquals(c1.getY(),2);
		assertEquals(c1.getZ(),3);
		assertTrue(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		/*Coordinate c4= new Coordinate("H01");
		assertEquals(c4.getX(),0);
		assertEquals(c4.getY(),0);
		assertEquals(c4.getZ(),0);*/
		
	}

}
