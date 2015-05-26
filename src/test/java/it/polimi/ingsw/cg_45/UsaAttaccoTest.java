package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.UsaAttacco;

import org.junit.Test;

public class UsaAttaccoTest {

	@Test
	public void test() {
		
		
		Alieno a=new Alieno(1,1);
		Umano u1=new Umano(2,2);
		Umano u2=new Umano(2,2);
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
		
		
		
	}
}
