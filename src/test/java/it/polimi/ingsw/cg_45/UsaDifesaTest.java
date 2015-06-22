package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg_45.controller.UsaDifesa;

import org.junit.Test;

public class UsaDifesaTest {

	@Test
	public void test() {
		
		Mappa mappa=new Fermi();
		Umano u1=new Umano(2,2,mappa,"u1");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(u1);
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		partita.getGiocatori().add(u1);
	
		UsaDifesa difesa=new UsaDifesa(u1,partita);
		difesa.esegui();
		
		
		assertTrue(u1.getCarte().isEmpty());
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)));
		
		
		
	}

}
