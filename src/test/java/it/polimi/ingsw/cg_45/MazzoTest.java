package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MazzoTest {

	@Test
	public void test() {
		Mazzo mazzo=new MazzoOggetti();
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
		
		CartaOggetto carta1=(CartaOggetto) mazzo.pescaCarta();
		System.out.println("cartapescata"+carta1);
		
		System.out.println("tolgo tutto");
		mazzo.getMazzoScarti().addAll(mazzo.getMazzoIniziale());
		mazzo.getMazzoIniziale().removeAll(mazzo.getMazzoIniziale());
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
		System.out.println("mazzo scarti:");
		
		for(Carta c: mazzo.getMazzoScarti()){
			System.out.println(c);
		}
		
		CartaOggetto carta=(CartaOggetto) mazzo.pescaCarta();
		System.out.println("\nmazzo iniziale");
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
		
		System.out.println("carta pescata"+carta);
		assertEquals(12,mazzo.numCarte);
	}

}
