package it.polimi.ingsw.cg_45;


import static org.junit.Assert.*;

import org.junit.Test;


public class UsaAdrenalinaTest {
	
	@Test
	public void test(){


		Umano p1=new Umano(1,1);
		Umano p2=new Umano(2,2);
		Umano p3=new Umano(3,3);
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p1.setStato(Stato.INIZIO);
		
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p2.setStato(Stato.INIZIO);
		
		p3.setStato(Stato.TURNOTERMINATO);
		
		StatoDiGioco partita=new StatoDiGioco();
		partita.getGiocatori().add(p1);
		partita.getGiocatori().add(p2);
		partita.getGiocatori().add(p3);
		
		UsaAdrenalina a=new UsaAdrenalina(p1,partita);
		a.esegui();
		
		UsaAdrenalina b=new UsaAdrenalina(p2,partita);
		b.esegui();
		
		UsaAdrenalina d=new UsaAdrenalina(p2,partita);
		d.esegui();
		
		for(CartaOggetto c: p1.getCarte()){
			System.out.println(c.getTipo());
		}
		
		
		
		for(CartaOggetto c: p2.getCarte()){
			System.out.println(c.getTipo());
		}
		
		System.out.println("Mazzo Scarti: ");
		
		for(Carta c: partita.getMazzoOggetti().mazzoScarti){
			System.out.println(c.toString());
		}
		
		assertEquals(2,p1.getPortata());
		assertEquals(1,p2.getPortata());
		assertEquals(1,p3.getPortata());
		
		
	}
	
	
}
