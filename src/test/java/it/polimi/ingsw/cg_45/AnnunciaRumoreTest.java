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
		Alieno a1=new Alieno(1,1,mappa,"Lorenzo");
		Umano u1=new Umano(2,2);
		Umano u2=new Umano(2,2);
		Umano u3=new Umano(3,3);
		Alieno a2=new Alieno(4,4,mappa,"Andrea");
		Alieno a3=new Alieno(5,5,mappa,"Giovanni");
		
		Settore s=mappa.getMappa().get(new Coordinate("L07"));
		StatoDiGioco partita=new StatoDiGioco();
		
		
		u1.setStato(Stato.CARTABLUFF);
		a1.setStato(Stato.CARTABLUFFOGGETTO);
		u2.setStato(Stato.CARTARIVELA);
		u3.setStato(Stato.CARTARIVELAOGGETTO);
		a2.setStato(Stato.CARTARIVELA);
		a3.setStato(Stato.CARTARIVELAOGGETTO);
		u1.setPosizione(s);
		u2.setPosizione(s);
		u3.setPosizione(s);
		a1.setPosizione(s);
		a2.setPosizione(s);
		a3.setPosizione(s);

		partita.getGiocatori().add(u1);
		partita.getGiocatori().add(u2);
		partita.getGiocatori().add(u3);
		partita.getGiocatori().add(a1);
		partita.getGiocatori().add(a2);
		partita.getGiocatori().add(a3);
		
		AnnunciaRumore ar1=new AnnunciaRumore(partita,u1,"L07");
		RispostaController risposta=ar1.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u1.getStato()==Stato.EFFETTOCONCLUSO);
		
		AnnunciaRumore ar2=new AnnunciaRumore(partita,u2,"L07");
		risposta=ar2.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u2.getStato()==Stato.EFFETTOCONCLUSO);
		
		AnnunciaRumore ar3=new AnnunciaRumore(partita,u3,"L07");
		risposta=ar3.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(u3.getStato()==Stato.RIVELATO);
		
		AnnunciaRumore ar4=new AnnunciaRumore(partita,a1,"L07");
		risposta=ar4.esegui();
		System.out.println(risposta.getMessaggioBroadcast());
		assertTrue(a1.getStato()==Stato.BLUFFATO);
		
		
		AnnunciaRumore ar5=new AnnunciaRumore(partita,a2,"L08");
		risposta=ar5.esegui();
		System.out.println(risposta.getMessaggioClient());
		assertTrue(a2.getStato()==Stato.CARTARIVELA);
		
		AnnunciaRumore ar6=new AnnunciaRumore(partita,a3,"L08");
		risposta=ar6.esegui();
		System.out.println(risposta.getMessaggioClient());
		assertTrue(a3.getStato()==Stato.CARTARIVELAOGGETTO);
	}

}
