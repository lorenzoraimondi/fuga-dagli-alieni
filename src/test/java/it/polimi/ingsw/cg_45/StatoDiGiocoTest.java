package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StatoDiGiocoTest {

	@Test
	public void test() {
		Mappa mappa=new Fermi();
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		
		Giocatore g1=new Alieno(1,1,mappa,"Lorenzo");
		Giocatore g2=new Umano(2,2,mappa,"Andrea");
		
		giocatori.add(g1);
		giocatori.add(g2);
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		//partita.incrementCurrentPlayer();
		partita.incrementTurno();
		
		//assertEquals(2,partita.getCurrentPlayer());
		assertEquals(2,partita.getTurno());
		
		assertEquals(mappa,partita.getMappa());
		
		assertEquals(25,partita.getMazzoSettori().getNumCarte());
		assertEquals(6,partita.getMazzoScialuppe().getNumCarte());
		
		assertEquals(g1,partita.getGiocatore(1));
		assertEquals(null,partita.getGiocatore(10));
		
		//partita.incrementCurrentPlayer();
		//assertEquals(1,partita.getCurrentPlayer());
		
		
	}

}
