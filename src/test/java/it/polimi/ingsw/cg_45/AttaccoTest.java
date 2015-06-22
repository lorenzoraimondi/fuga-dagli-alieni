package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.Attacco;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.view.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AttaccoTest {
	
	@Test
	public void test() throws IOException {
		
		ServerInterface server=null;
		Mappa mappa = new Fermi();
		Alieno a=new Alieno(1,1,mappa,"Lorenzo");
		Umano u1=new Umano(2,2,mappa,"Andrea");
		Umano u2=new Umano(2,2,mappa,"Giovanni");
		Umano u3=new Umano(3,3,mappa,"Francesco");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(a);
		giocatori.add(u1);
		giocatori.add(u2);
		giocatori.add(u3);
		SettorePericoloso s=new SettorePericoloso(1,1,1);
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		u1.setPosizione(s);
		u1.setSituazione(Situazione.INATTIVO);
		u1.setStato(Stato.TURNOTERMINATO);
		
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		a.setPosizione(s);
		a.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		a.setSituazione(Situazione.ATTIVO);
		a.setStato(Stato.PERICOLO);
					
		Attacco attacco=new Attacco(partita,a,s,server);
		attacco.esegui();
		
		assertEquals(Situazione.MORTO,u1.getSituazione());
		assertEquals(Situazione.ATTIVO,a.getSituazione());
		assertEquals(Stato.ATTACCATO,a.getStato());
		assertTrue(u1.getCarte().isEmpty());
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.SEDATIVI)));
		assertEquals(3,a.getPortata());
		
		u3.setPosizione(s);
		u3.setSituazione(Situazione.ATTIVO);
		u3.setStato(Stato.PERICOLO);
		u3.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
	
		u2.setPosizione(s);
		u2.setSituazione(Situazione.INATTIVO);
		u2.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		u2.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		
		Attacco attacco2=new Attacco(partita,u3,s,server);
		attacco2.esegui();
		
		assertEquals(Situazione.INATTIVO,u2.getSituazione());
		assertEquals(Situazione.ATTIVO,u3.getSituazione());
		assertEquals(Situazione.MORTO,a.getSituazione());
		assertEquals(Stato.TURNOTERMINATO,a.getStato());
		assertEquals(Stato.ATTACCATO,u3.getStato());
		assertTrue(a.getCarte().isEmpty());
		assertTrue(u2.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO)));
		assertFalse(u2.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)));
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO)));
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)));
		assertEquals(1,u3.getPortata());
		
		Attacco attacco3=new Attacco(partita,u2,s,server);
		attacco3.esegui();
		
		assertEquals(Situazione.INATTIVO,u2.getSituazione());
		assertEquals(Situazione.ATTIVO,u3.getSituazione());
		assertEquals(Stato.ATTACCATO,u3.getStato());
		assertTrue(u2.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO)));
		assertEquals(1,u2.getPortata());
	
	}
}
