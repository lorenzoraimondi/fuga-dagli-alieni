package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.AnnunciaRumore;
import it.polimi.ingsw.cg_45.controller.RispostaController;

import java.io.IOException;

import org.junit.Test;

public class AnnunciaRumoreTest {

	@Test
	public void test() throws IOException {
		Fermi mappa=new Fermi();
		
		Umano u3=new Umano(3,3,mappa,"Addio");
		
		
		Settore s=mappa.getMappa().get(new Coordinate("L07"));
		StatoDiGioco partita=new StatoDiGioco();
		
		
		
		
		u3.setStato(Stato.CARTARIVELAOGGETTO);
		
		
		u3.setPosizione(s);
		

		
		partita.getGiocatori().add(u3);
		
		
		
		u3.setStato(Stato.CARTABLUFF);
		AnnunciaRumore ar1=new AnnunciaRumore(partita,u3,"L07");
		RispostaController risposta=ar1.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.EFFETTOCONCLUSO);
		
		u3.setStato(Stato.CARTARIVELA);
		AnnunciaRumore ar5=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar5.esegui();
		System.out.println(risposta.getMessaggioClient());
		assertTrue(u3.getStato()==Stato.EFFETTOCONCLUSO);
		
		u3.setStato(Stato.CARTARIVELAOGGETTO);
		AnnunciaRumore ar6=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar6.esegui();
		System.out.println(risposta.getMessaggioClient());
		assertTrue(u3.getStato()==Stato.RIVELATO);
		
		u3.setStato(Stato.CARTABLUFFOGGETTO);
		AnnunciaRumore ar3=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar3.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.BLUFFATO);
		
		u3.setStato(Stato.SILENZIO);
		AnnunciaRumore ar4=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar4.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.EFFETTOCONCLUSO);	
		
		u3.setStato(Stato.CARTARIVELA);
		AnnunciaRumore ar2=new AnnunciaRumore(partita,u3,"L08");
		risposta=ar2.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.CARTARIVELA);
		
		u3.setStato(Stato.INIZIO);
		AnnunciaRumore ar7=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar7.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.INIZIO);
		
		
	}

}
