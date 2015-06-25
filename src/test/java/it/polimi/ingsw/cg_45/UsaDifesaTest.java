package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.UsaDifesa;
import it.polimi.ingsw.cg_45.model.CartaOggetto;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaOggetto;
import it.polimi.ingsw.cg_45.model.Umano;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UsaDifesaTest {

	@Test
	public void test() {
		
		Mappa mappa=new Fermi();
		RispostaController risp;
		Umano u1=new Umano(2,2,mappa,"u1");
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(u1);
		StatoDiGioco partita=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		u1.setCarta(new CartaOggetto(TipoCartaOggetto.DIFESA));
		partita.getGiocatori().add(u1);
	
		UsaDifesa difesa=new UsaDifesa(u1,partita);
		difesa.esegui();
		
		
		assertTrue(u1.getCarte().isEmpty());
		assertTrue(partita.getMazzoOggetti().getMazzoScarti().contains(new CartaOggetto(TipoCartaOggetto.DIFESA)));
		
		risp=new UsaDifesa(u1,partita).esegui();
		
		assertTrue(risp.getMessaggioClient().contains("Mossa non valida"));
		
	}

}
