package it.polimi.ingsw.cg_45;


import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.UsaSedativi;

import org.junit.Test;

public class UsaSedativiTest {

	@Test
	public void test() {
				
		Umano p1=new Umano(1,1);
		Umano p2=new Umano(2,2);
		Umano p3=new Umano(3,3);
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.SEDATIVI));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p1.setSituazione(Situazione.ATTIVONASCOSTO);
		
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p2.setSituazione(Situazione.ATTIVONASCOSTO);
		
		p3.setSituazione(Situazione.INATTIVO);
		
		StatoDiGioco partita=new StatoDiGioco();
		partita.getGiocatori().add(p1);
		partita.getGiocatori().add(p2);
		partita.getGiocatori().add(p3);
		
		UsaSedativi a=new UsaSedativi(p1,partita);
		a.esegui();
		
		UsaSedativi b=new UsaSedativi(p2,partita);
		b.esegui();
		
		UsaSedativi d=new UsaSedativi(p2,partita);
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
		
		assertEquals(true,p1.isSedato());
		assertEquals(false,p2.isSedato());
		assertEquals(false,p3.isSedato());
		
		

	}

}
