package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsaDifesaTest {

	@Test
	public void test() {
		
		Umano u1=new Umano(2,2);
		StatoDiGioco partita=new StatoDiGioco();
		
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		partita.getGiocatori().add(u1);
	
		UsaDifesa difesa=new UsaDifesa(u1,partita);
		difesa.esegui();
		
		
		assertTrue(u1.getCarte().isEmpty());
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)));
		
		
		
	}

}
