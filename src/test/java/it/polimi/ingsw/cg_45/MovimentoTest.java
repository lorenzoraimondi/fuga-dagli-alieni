package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Movimento;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Galvani;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Settore;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MovimentoTest {

	@Test
	public void test() throws IOException {
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		Mappa mappa=new Galvani();
		Azione azione;
		RispostaController rix;
	
		Settore sicuro=mappa.getMappa().get(new Coordinate("K09"));
		Settore pericolo=mappa.getMappa().get(new Coordinate("L09"));
		Settore scialuppa1=mappa.getMappa().get(new Coordinate("Q02"));
		Settore scialuppa2=mappa.getMappa().get(new Coordinate("P01"));
		
		Umano g1=new Umano(1,0,mappa,"g1");
		Alieno g2=new Alieno(2,1,mappa,"g2");
		
		g1.setStato(Stato.INIZIO);
		g1.setSituazione(Situazione.ATTIVO);
		g2.setStato(Stato.TURNOTERMINATO);
		g2.setSituazione(Situazione.INATTIVO);
		
		giocatori.add(g1);
		giocatori.add(g2);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		azione=new Movimento(model,g1,sicuro);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.SICURO,g1.getStato());
		
		g1.setStato(Stato.INIZIO);
		azione=new Movimento(model,g1,pericolo);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.PERICOLO,g1.getStato());
		
		g1.setStato(Stato.INIZIO);
		g1.setPosizioneIniziale(mappa);
		g1.setSedato(true);
		azione=new Movimento(model,g1,pericolo);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.SICURO,g1.getStato());
		g1.setSedato(false);
		
		g1.setStato(Stato.INIZIO);
		g1.setPosizione(scialuppa1);
		azione=new Movimento(model,g1,scialuppa2);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.SCIALUPPA,g1.getStato());
		g1.setPosizioneIniziale(mappa);
		
		g1.setStato(Stato.INIZIO);
		azione=new Movimento(model,g2,sicuro);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.TURNOTERMINATO,g2.getStato());
		
		g2.setPosizione(scialuppa1);
		g2.setStato(Stato.INIZIO);
		g2.setSituazione(Situazione.ATTIVO);
		azione=new Movimento(model,g2,scialuppa2);
		rix=azione.esegui();
		System.out.println(rix.getMessaggioClient());
		assertEquals(Stato.INIZIO,g2.getStato());
		g2.setPosizioneIniziale(mappa);
		g2.setStato(Stato.TURNOTERMINATO);
		g2.setSituazione(Situazione.INATTIVO);
	
	}

}
