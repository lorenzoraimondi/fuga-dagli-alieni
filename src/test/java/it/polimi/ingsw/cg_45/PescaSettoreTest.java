package it.polimi.ingsw.cg_45;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.PescaSettore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PescaSettoreTest {

	@Test
	public void test() throws IOException {
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		Mappa mappa=new Galvani();
		Azione azione;
			
		Umano g1=new Umano(1,0,mappa,"g1");
		
		g1.setStato(Stato.PERICOLO);
		g1.setSituazione(Situazione.ATTIVO);
		
		giocatori.add(g1);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		CartaSettore carta=new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,true);
		model.getMazzoSettori().getMazzoIniziale().removeAll(model.getMazzoSettori().getMazzoIniziale());
		model.getMazzoSettori().getMazzoIniziale().add(carta);
				
		azione=new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.CARTABLUFFOGGETTO,g1.getStato());
		
		carta=new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,false);
		model.getMazzoSettori().getMazzoIniziale().add(carta);
		g1.setStato(Stato.PERICOLO);
		azione=new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.CARTABLUFF,g1.getStato());
		
		carta=new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,true);
		model.getMazzoSettori().getMazzoIniziale().add(carta);
		g1.setStato(Stato.PERICOLO);
		azione=new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.CARTARIVELAOGGETTO,g1.getStato());
		
		carta=new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,false);
		model.getMazzoSettori().getMazzoIniziale().add(carta);
		g1.setStato(Stato.PERICOLO);
		azione=new PescaSettore(g1,model);
		azione.esegui();	
		assertEquals(Stato.CARTARIVELA,g1.getStato());
		
		carta=new CartaSettore(TipoCartaSettore.SILENZIO,true);
		model.getMazzoSettori().getMazzoIniziale().add(carta);
		g1.setStato(Stato.PERICOLO);
		azione=new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.SILENZIO,g1.getStato());
		
		g1.setStato(Stato.SICURO);
		azione = new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.SICURO,g1.getStato());
		
	
	}

}
