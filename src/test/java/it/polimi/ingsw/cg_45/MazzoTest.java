package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.Carta;
import it.polimi.ingsw.cg_45.CartaOggetto;

import it.polimi.ingsw.cg_45.Mazzo;
import it.polimi.ingsw.cg_45.MazzoOggetti;



import org.junit.Test;

public class MazzoTest {

	@Test
	public void test() {
		Mazzo mazzo=new MazzoOggetti();
		for(Carta c: mazzo.getMazzoIniziale()){
			System.out.println(c);
		}
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
	}

}
