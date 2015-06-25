package it.polimi.ingsw.cg_45;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaPartita;
import it.polimi.ingsw.cg_45.model.Alieno;
import it.polimi.ingsw.cg_45.model.Fermi;
import it.polimi.ingsw.cg_45.model.Giocatore;
import it.polimi.ingsw.cg_45.model.Mappa;
import it.polimi.ingsw.cg_45.model.Situazione;
import it.polimi.ingsw.cg_45.model.Stato;
import it.polimi.ingsw.cg_45.model.StatoDiGioco;
import it.polimi.ingsw.cg_45.model.Umano;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;
import it.polimi.ingsw.cg_45.netCommons.Timer;
import it.polimi.ingsw.cg_45.socket.SocketServer;
import it.polimi.ingsw.cg_45.socket.SocketTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TerminaPartitaTest {

	@Test
	public void test() throws IOException {
		ServerInterface server=new SocketServer(1337);
		
		StatoDiGioco partita1;
		StatoDiGioco partita2;
		StatoDiGioco partita3;
		StatoDiGioco partita4;
		StatoDiGioco partita5;
		
		Mappa mappa1=new Fermi();
		Mappa mappa2=new Fermi();
		Mappa mappa3=new Fermi();
		Mappa mappa4=new Fermi();
		Mappa mappa5=new Fermi();
		
		Giocatore g1=new Alieno(1,1,mappa1,"Lorenzo");
		Giocatore g2=new Umano(2,2,mappa1,"Andrea");
		Giocatore g3=new Alieno(3,1,mappa2,"Claudio");
		Giocatore g4=new Umano(4,2,mappa2,"Matteo");
		Giocatore g5=new Alieno(5,1,mappa3,"Marco");
		Giocatore g6=new Umano(6,2,mappa3,"Paola");
		Giocatore g7=new Umano(7,3,mappa3,"Tizio");
		Giocatore g8=new Alieno(8,4,mappa3,"Caio");
		Giocatore g9=new Alieno(9,5,mappa3,"Sempronio");
		Giocatore g10=new Alieno(10,1,mappa1,"Lorenzo");
		Giocatore g11=new Umano(11,2,mappa1,"Andrea");
		Giocatore g12=new Alieno(12,1,mappa2,"Claudio");
		Giocatore g13=new Umano(13,2,mappa2,"Matteo");
		
		
		g2.setSituazione(Situazione.MORTO);
		g2.setStato(Stato.TURNOTERMINATO);
		
		g4.setSituazione(Situazione.VINTO);
		g4.setStato(Stato.EFFETTOCONCLUSO);
		
		g6.setSituazione(Situazione.VINTO);
		g6.setStato(Stato.EFFETTOCONCLUSO);
		g7.setSituazione(Situazione.MORTO);
		g7.setStato(Stato.TURNOTERMINATO);
		g8.setSituazione(Situazione.MORTO);
		g8.setStato(Stato.TURNOTERMINATO);
		
		
		
		List<Giocatore> lista1=new ArrayList<Giocatore>();
		List<Giocatore> lista2=new ArrayList<Giocatore>();
		List<Giocatore> lista3=new ArrayList<Giocatore>();
		List<Giocatore> lista4=new ArrayList<Giocatore>();
		List<Giocatore> lista5=new ArrayList<Giocatore>();
		
		lista1.add(g1);
		lista1.add(g2);
		lista2.add(g3);
		lista2.add(g4);
		lista3.add(g5);
		lista3.add(g6);
		lista3.add(g7);
		lista3.add(g8);
		lista3.add(g9);
		lista4.add(g10);
		lista4.add(g11);
		lista5.add(g12);
		lista5.add(g13);
		
		
		partita1=new StatoDiGioco(lista1,mappa1);
		partita2=new StatoDiGioco(lista2,mappa2);
		partita3=new StatoDiGioco(lista3,mappa3);
		partita4=new StatoDiGioco(lista4,mappa4);
		partita5=new StatoDiGioco(lista5,mappa5);
		
		Timer timerTurno1=new SocketTimer(partita1,g1,(SocketServer)server);
		Thread t1=new Thread((Runnable) timerTurno1);
		((SocketServer)server).getTimers().put(g1.getID(), t1);
		
		Timer timerTurno2=new SocketTimer(partita1,g2,(SocketServer)server);
		Thread t2=new Thread((Runnable) timerTurno2);
		((SocketServer)server).getTimers().put(g2.getID(), t2);
		
		Timer timerTurno3=new SocketTimer(partita2,g3,(SocketServer)server);
		Thread t3=new Thread((Runnable) timerTurno3);
		((SocketServer)server).getTimers().put(g3.getID(), t3);
		
		Timer timerTurno4=new SocketTimer(partita2,g4,(SocketServer)server);
		Thread t4=new Thread((Runnable) timerTurno4);
		((SocketServer)server).getTimers().put(g4.getID(), t4);
		
		Timer timerTurno5=new SocketTimer(partita3,g5,(SocketServer)server);
		Thread t5=new Thread((Runnable) timerTurno5);
		((SocketServer)server).getTimers().put(g5.getID(), t5);
		
		Timer timerTurno6=new SocketTimer(partita3,g6,(SocketServer)server);
		Thread t6=new Thread((Runnable) timerTurno6);
		((SocketServer)server).getTimers().put(g6.getID(), t6);
		
		Timer timerTurno7=new SocketTimer(partita3,g7,(SocketServer)server);
		Thread t7=new Thread((Runnable) timerTurno7);
		((SocketServer)server).getTimers().put(g7.getID(), t7);
		
		Timer timerTurno8=new SocketTimer(partita3,g8,(SocketServer)server);
		Thread t8=new Thread((Runnable) timerTurno8);
		((SocketServer)server).getTimers().put(g8.getID(), t8);
		
		Timer timerTurno9=new SocketTimer(partita3,g9,(SocketServer)server);
		Thread t9=new Thread((Runnable) timerTurno9);
		((SocketServer)server).getTimers().put(g9.getID(), t9);
		
		Timer timerTurno10=new SocketTimer(partita4,g10,(SocketServer)server);
		Thread t10=new Thread((Runnable) timerTurno10);
		((SocketServer)server).getTimers().put(g10.getID(), t10);
		
		Timer timerTurno11=new SocketTimer(partita4,g11,(SocketServer)server);
		Thread t11=new Thread((Runnable) timerTurno11);
		((SocketServer)server).getTimers().put(g11.getID(), t11);
		
		Timer timerTurno12=new SocketTimer(partita5,g12,(SocketServer)server);
		Thread t12=new Thread((Runnable) timerTurno12);
		((SocketServer)server).getTimers().put(g12.getID(), t12);
		
		Timer timerTurno13=new SocketTimer(partita5,g13,(SocketServer)server);
		Thread t13=new Thread((Runnable) timerTurno13);
		((SocketServer)server).getTimers().put(g13.getID(), t13);
		
		for(int i=0;i<39;i++){
			partita4.incrementTurno();	
		}
		
		
		//partita1: Umano morto, vince l'alieno.
		server.getPartite().put(1,partita1);
		server.getPartite().put(2,partita1);
		assertFalse(server.getPartite().values().isEmpty());
		RispostaController risposta1=new TerminaPartita(g1,partita1,server).esegui();
		assertEquals(risposta1.getMessaggioBroadcast(),"\nPartita terminata!\nLorenzo, Alieno, ha vinto!\n");
		assertTrue(g1.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g2.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g1.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g2.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(server.getPartite().values().isEmpty());
		
		//partita2: unico Umano vince.
		server.getPartite().put(3,partita2);
		server.getPartite().put(4,partita2);
		assertFalse(server.getPartite().values().isEmpty());
		RispostaController risposta2=new TerminaPartita(g4,partita2,server).esegui();
		assertEquals(risposta2.getMessaggioBroadcast(),"\nPartita terminata!\nMatteo, Umano, ha vinto!\n");
		assertTrue(g3.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g4.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g3.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g4.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(server.getPartite().values().isEmpty());
		
		//partita3: Umano vince, altro umano morto.
		server.getPartite().put(5,partita3);
		server.getPartite().put(6,partita3);
		server.getPartite().put(7,partita3);
		server.getPartite().put(8,partita3);
		server.getPartite().put(9,partita3);
		assertFalse(server.getPartite().values().isEmpty());
		RispostaController risposta3=new TerminaPartita(g6,partita3,server).esegui();
		assertEquals(risposta3.getMessaggioBroadcast(),"\nPartita terminata!\nPaola, Umano, ha vinto!\nMarco, Alieno, ha vinto!\nCaio, Alieno, ha vinto!\nSempronio, Alieno, ha vinto!\n");
		assertTrue(g5.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g6.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g7.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g8.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g9.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g5.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g6.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g7.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g8.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g9.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(server.getPartite().values().isEmpty());
		
		//partita4: Turno 40:
		server.getPartite().put(10,partita4);
		server.getPartite().put(11,partita4);
		assertFalse(server.getPartite().values().isEmpty());
		RispostaController risposta4=new TerminaPartita(g10,partita4,server).esegui();
		assertEquals(risposta4.getMessaggioBroadcast(),"\nPartita terminata!\nLorenzo, Alieno, ha vinto!\n");
		assertTrue(g10.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g11.getStato()==Stato.TURNOTERMINATO);
		assertTrue(g10.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(g11.getSituazione()==Situazione.DISCONNESSO);
		assertTrue(server.getPartite().values().isEmpty());	
	
		//partita5: non può terminare
		server.getPartite().put(12,partita4);
		server.getPartite().put(13,partita4);
		assertFalse(server.getPartite().values().isEmpty());
		RispostaController risposta5=new TerminaPartita(g12,partita5,server).esegui();
		assertEquals(risposta5,null);
		assertTrue(g12.getStato()==Stato.INIZIO);
		assertTrue(g13.getStato()==Stato.INIZIO);
		assertTrue(g12.getSituazione()==Situazione.ATTIVO);
		assertTrue(g13.getSituazione()==Situazione.ATTIVO);
		assertFalse(server.getPartite().values().isEmpty());
		
	}

}
