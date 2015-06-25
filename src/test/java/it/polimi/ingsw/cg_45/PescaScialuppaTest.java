package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.PescaScialuppa;
import it.polimi.ingsw.cg_45.model.CartaScialuppa;
import it.polimi.ingsw.cg_45.model.Coordinate;
import it.polimi.ingsw.cg_45.model.Galvani;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.TipoCartaScialuppa;
import it.polimi.ingsw.cg_45.model.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PescaScialuppaTest {

	@Test
	public void test() throws IOException {
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		Mappa mappa=new Galvani();
		Azione azione;
		ServerInterface server=null;
			
		Umano g1=new Umano(1,0,mappa,"g1");
		Umano g2=new Umano(2,1,mappa,"g2");
		
		g1.setStato(Stato.SCIALUPPA);
		g1.setSituazione(Situazione.ATTIVO);
		g1.setPosizione(mappa.getMappa().get(new Coordinate("P01")));
		g2.setSituazione(Situazione.ATTIVO);
		g2.setStato(Stato.SCIALUPPA);
		g2.setPosizione(mappa.getMappa().get(new Coordinate("V11")));
		
		giocatori.add(g1);
		giocatori.add(g2);
		
		StatoDiGioco model=new StatoDiGioco((ArrayList<Giocatore>) giocatori,mappa);
		
		CartaScialuppa carta=new CartaScialuppa(TipoCartaScialuppa.VERDE);
		model.getMazzoScialuppe().getMazzoIniziale().removeAll(model.getMazzoScialuppe().getMazzoIniziale());
		model.getMazzoScialuppe().getMazzoIniziale().add(carta);
		
		azione=new PescaScialuppa(g1,model,server);
		azione.esegui();
		assertEquals(Stato.EFFETTOCONCLUSO,g1.getStato());
		assertEquals(Situazione.VINTO,g1.getSituazione());
			
		carta=new CartaScialuppa(TipoCartaScialuppa.ROSSA);
		model.getMazzoScialuppe().getMazzoIniziale().add(carta);
		azione=new PescaScialuppa(g2,model,server);
		azione.esegui();
		assertEquals(Stato.CARTASCIALUPPA,g2.getStato());
		
		model.getMazzoScialuppe().getMazzoIniziale().add(carta);
		g2.setStato(Stato.INIZIO);
		azione=new PescaScialuppa(g2,model,server);
		assertEquals(Stato.INIZIO,g2.getStato());
		
		g2.setStato(Stato.SCIALUPPA);
		g2.setSituazione(Situazione.ATTIVO);
		model.getMazzoScialuppe().getMazzoIniziale().add(carta);
		azione=new PescaScialuppa(g2,model,server);
		azione.esegui();
		assertEquals(Stato.SCIALUPPA,g2.getStato());

	}

}
