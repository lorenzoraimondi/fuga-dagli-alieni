package it.polimi.ingsw.cg_45;

import org.junit.Test;

public class MazzoSettoriTest {

	@Test
	public void test() {
		Mazzo mazzo=new MazzoSettori();
		CartaSettore carta;
		System.out.println("mazzo iniziale");
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
		
		for(int i=0;i<24;i++){
			carta=(CartaSettore) mazzo.pescaCarta();
			System.out.println("carta pescata"+carta);
		}
		
		System.out.println("mazzo iniziale");
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
		
		carta=(CartaSettore) mazzo.pescaCarta();
		System.out.println("carta pescata (ultima)"+carta);
		
		System.out.println("mazzo scarti");
		for(Carta c: mazzo.getMazzoScarti()){
			System.out.println(c);
		}
		
		carta=(CartaSettore) mazzo.pescaCarta();
		System.out.println("carta pescata (ultima)"+carta);
		
		System.out.println("mazzo iniziale (rifatto)");
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
	}

}
