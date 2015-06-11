package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.UsaAttacco;

import java.io.IOException;

import org.junit.Test;

public class UsaAttaccoTest {

	@Test
	public void test() throws IOException {
		
		
		Mappa mappa = new Fermi();
		Alieno a=new Alieno(1,1,mappa,"Lorenzo");
		Umano u1=new Umano(2,2,mappa,"Andrea");
		Umano u2=new Umano(2,2,mappa,"Giovanni");
		Umano u3=new Umano(3,3,mappa,"Francesco");
		SettorePericoloso s=new SettorePericoloso(1,1,1);
		StatoDiGioco partita=new StatoDiGioco();
		
		u1.setPosizione(s);
		u1.setSituazione(Situazione.ATTIVONASCOSTO);
		u1.setStato(Stato.PERICOLO);
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		a.setPosizione(s);
		a.setSituazione(Situazione.INATTIVO);
		u2.setPosizione(s);
		u2.setSituazione(Situazione.INATTIVO);
		u2.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));

		
		
		partita.getGiocatori().add(u1);
		partita.getGiocatori().add(u2);
		partita.getGiocatori().add(a);
		
		for(CartaOggetto c: u1.getCarte()){
			System.out.println(c.getTipo());
		}
		
		UsaAttacco attacco=new UsaAttacco(partita,u1,s);
		attacco.esegui();
		
		for(CartaOggetto c: u1.getCarte()){
			System.out.println(c.getTipo());
		}
		
		System.out.println("Mazzo Scarti: ");
		
		for(Carta c: partita.getMazzoOggetti().mazzoScarti){
			System.out.println(c.toString());
		}
		
		assertEquals(Situazione.MORTO,a.getSituazione());
		assertEquals(Situazione.INATTIVO,u2.getSituazione());
		
		u3.setPosizione(s);
		u3.setSituazione(Situazione.ATTIVONASCOSTO);
		u3.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		
		UsaAttacco attacco2=new UsaAttacco(partita,u3,s);
		attacco2.esegui();
		
		assertEquals(Situazione.ATTIVONASCOSTO, u1.getSituazione());
		assertEquals(Situazione.INATTIVO, u2.getSituazione());
		
	}
}
