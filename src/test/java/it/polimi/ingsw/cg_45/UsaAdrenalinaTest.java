package it.polimi.ingsw.cg_45;


import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.UsaAdrenalina;
import it.polimi.ingsw.cg_45.model.Carta;
import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class UsaAdrenalinaTest {
	
	@Test
	public void test(){

		Mappa mappa=new Fermi();
		Umano p1=new Umano(1,1,mappa,"p1");
		Umano p2=new Umano(2,2,mappa,"p2");
		Umano p3=new Umano(3,3,mappa,"p3");
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ADRENALINA));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p1.setStato(Stato.INIZIO);
		
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.ATTACCO));
		p2.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		p2.setStato(Stato.INIZIO);
		
		p3.setStato(Stato.TURNOTERMINATO);
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(p1);
		giocatori.add(p2);
		giocatori.add(p3);
		
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		
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
		
		for(Carta c: partita.getMazzoOggetti().getMazzoScarti()){
			System.out.println(c.toString());
		}
		
		assertEquals(2,p1.getPortata());
		assertEquals(1,p2.getPortata());
		assertEquals(1,p3.getPortata());
		
		
	}
	
	
}
