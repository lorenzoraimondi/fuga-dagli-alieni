package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.UsaLuci;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UsaLuciTest {

	@Test
	public void test() throws IOException {
		Mappa mappa =new Galvani();
		Azione azione;
		RispostaController risp;
		CartaOggetto carta=new CartaOggetto(TipoCartaOggetto.LUCI);
		Settore settore=mappa.getMappa().get(new Coordinate("H01"));
		Settore settore1=mappa.getMappa().get(new Coordinate("G02"));
		
		Umano g1= new Umano(1,0,mappa,"g1");
		Alieno g2=new Alieno(2,1,mappa,"g2");
		g1.setStato(Stato.INIZIO);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setCarta(carta);
		g2.setStato(Stato.TURNOTERMINATO);
		g2.setSituazione(Situazione.INATTIVO);
		
		
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori, mappa);
		
		azione=new UsaLuci(model,g1,settore);
		risp=azione.esegui();
		assertEquals("Nessun giocatore presente nelle vicinanze",risp.getMessaggioBroadcast());
		
		g1.setCarta(carta);
		g2.setPosizione(settore1);
		azione=new UsaLuci(model,g1,settore);
		risp=azione.esegui();
		assertEquals("g2 si trova nel settore G02\n",risp.getMessaggioBroadcast());	}

}
