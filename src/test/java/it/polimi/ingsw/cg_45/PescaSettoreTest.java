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
		CartaSettore carta=(CartaSettore) model.getMazzoSettori().getMazzoIniziale().get(0);
		
		azione=new PescaSettore(g1,model);
		azione.esegui();
		if(carta.getTipo()==TipoCartaSettore.RUMOREQUALUNQUESETTORE && carta.isOggetto()==true)
			assertEquals(Stato.CARTABLUFFOGGETTO,g1.getStato());
		else if(carta.getTipo()==TipoCartaSettore.RUMOREQUALUNQUESETTORE && carta.isOggetto()==false)
			assertEquals(Stato.CARTABLUFF,g1.getStato());
		else if(carta.getTipo()==TipoCartaSettore.RUMORETUOSETTORE && carta.isOggetto()==true)
			assertEquals(Stato.CARTARIVELAOGGETTO,g1.getStato());
		else if(carta.getTipo()==TipoCartaSettore.RUMORETUOSETTORE && carta.isOggetto()==false)
			assertEquals(Stato.CARTARIVELA,g1.getStato());
		else
			assertEquals(Stato.SILENZIO,g1.getStato());
		
		g1.setStato(Stato.SICURO);
		azione = new PescaSettore(g1,model);
		azione.esegui();
		assertEquals(Stato.SICURO,g1.getStato());
		
	
	}

}
