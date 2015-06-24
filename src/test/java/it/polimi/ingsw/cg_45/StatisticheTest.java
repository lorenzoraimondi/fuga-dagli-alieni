package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.Statistiche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StatisticheTest {

	@Test
	public void test() throws IOException {
		Mappa mappa=new Fermi();
		Azione azione;
		RispostaController risp;
		
		Giocatore g1=new Umano(1,1,mappa,"g1");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(g1);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.INIZIO);
		StatoDiGioco partita=new StatoDiGioco(giocatori,mappa);
		
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertEquals(Stato.INIZIO,g1.getStato());
		assertTrue(risp.getMessaggioClient().contains("movimento"));
		assertTrue(risp.getMessaggioClient().contains("umano"));
		assertTrue(risp.getMessaggioClient().contains("L10"));
		assertTrue(risp.getMessaggioClient().contains("1"));

		
		g1.setStato(Stato.SICURO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("terminare"));
		
		g1.setStato(Stato.PERICOLO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("pescare una carta settore"));
		
		g1.setStato(Stato.CARTASCIALUPPA);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("pescare una carta scialuppa"));
		
		g1.setStato(Stato.ATTACCATO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("terminare"));
		
		g1.setStato(Stato.SILENZIO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("annunciare silenzio"));
		
		g1.setStato(Stato.CARTABLUFF);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("annuncia un settore in cui vuoi far rumore"));
		
		g1.setStato(Stato.CARTABLUFFOGGETTO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("annuncia un settore in cui vuoi far rumore"));
		
		g1.setStato(Stato.CARTARIVELA);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("annuncia il tuo settore in cui fare rumore"));
		
		g1.setStato(Stato.CARTARIVELAOGGETTO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("annuncia il tuo settore in cui fare rumore"));
		
		g1.setStato(Stato.RIVELATO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("pescare una carta oggetto"));
		
		g1.setStato(Stato.BLUFFATO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("pescare una carta oggetto"));
		
		g1.setStato(Stato.SCIALUPPA);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("terminare"));
		
		g1.setStato(Stato.EFFETTOCONCLUSO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("terminare"));
		
		g1.setStato(Stato.TURNOTERMINATO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("non è il tuo turno"));
		
		g1.setSituazione(Situazione.DISCONNESSO);
		g1.setStato(Stato.INIZIO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("Mossa non valida, sei disconnesso"));
		
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		g1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		g1.setSituazione(Situazione.ATTIVO);
		g1.setStato(Stato.INIZIO);
		azione=new Statistiche(g1,partita);
		risp=azione.esegui();
		assertTrue(risp.getMessaggioClient().contains("scartare una carta oggetto"));
	}

}
