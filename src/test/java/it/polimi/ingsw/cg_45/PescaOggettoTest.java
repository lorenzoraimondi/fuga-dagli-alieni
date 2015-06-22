package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.PescaOggetto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PescaOggettoTest {

	@Test
	public void test() throws IOException {
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		Mappa mappa=new Galvani();
		Azione azione;
			
		Umano g1=new Umano(1,0,mappa,"g1");
		Alieno g2=new Alieno(2,1,mappa,"g2");
		
		g1.setStato(Stato.BLUFFATO);
		g1.setSituazione(Situazione.ATTIVO);
		g2.setStato(Stato.TURNOTERMINATO);
		g2.setSituazione(Situazione.INATTIVO);
		
		giocatori.add(g1);
		giocatori.add(g2);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		azione=new PescaOggetto(g1,model);
		azione.esegui();
		assertNotEquals(null,g1.getCarte());
		assertEquals(Stato.EFFETTOCONCLUSO,g1.getStato());
		
		g1.setStato(Stato.INIZIO);
		azione=new PescaOggetto(g1,model);
		azione.esegui();
		assertEquals(Stato.INIZIO,g1.getStato());
		
		g1.setStato(Stato.RIVELATO);
		g1.getCarte().removeAll(g1.getCarte());
		model.getMazzoOggetti().getMazzoIniziale().removeAll(model.getMazzoOggetti().getMazzoIniziale());
		azione=new PescaOggetto(g1,model);
		azione.esegui();
		assertEquals(0,g1.getCarte().size());
		assertEquals(Stato.EFFETTOCONCLUSO,g1.getStato());
	}

}
