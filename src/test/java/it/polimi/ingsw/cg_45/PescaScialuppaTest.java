package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.PescaScialuppa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PescaScialuppaTest {

	@Test
	public void test() throws IOException {
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		Mappa mappa=new Galvani();
		Azione azione;
			
		Umano g1=new Umano(1,0,mappa,"g1");
		
		g1.setStato(Stato.SCIALUPPA);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setPosizione(mappa.getMappa().get(new Coordinate("P01")));
		
		giocatori.add(g1);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		azione=new PescaScialuppa(g1,model);
		CartaScialuppa carta=(CartaScialuppa) model.getMazzoScialuppe().getMazzoIniziale().get(0);
		azione.esegui();
		if(carta.getTipo()==TipoCartaScialuppa.VERDE){
			assertEquals(Stato.TURNOTERMINATO,g1.getStato());
			assertEquals(Situazione.DISCONNESSO,g1.getSituazione());}
		else
			assertEquals(Stato.CARTASCIALUPPA,g1.getStato());
		
		g1.setStato(Stato.INIZIO);
		azione=new PescaScialuppa(g1,model);
		assertEquals(Stato.INIZIO,g1.getStato());
		
		SettoreScialuppa settore=(SettoreScialuppa) mappa.getMappa().get(new Coordinate("P01"));
		settore.setScoperta();
		g1.setStato(Stato.SCIALUPPA);
		g1.setSituazione(Situazione.ATTIVO);
		azione=new PescaScialuppa(g1,model);
		azione.esegui();
		assertEquals(Stato.SCIALUPPA,g1.getStato());

	}

}
