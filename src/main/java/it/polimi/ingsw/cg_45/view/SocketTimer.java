package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaPartita;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.Timer;

import java.io.IOException;

public class SocketTimer extends Timer implements Runnable{
	private Azione azione;
	private RispostaController risp;
	
	public SocketTimer(StatoDiGioco partita, Giocatore giocatore,Server server){
		super(partita,giocatore,server);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			int ordine[]=ordinamento();
			
			try{Thread.sleep((long)SECONDI*1000);
				azione=new Disconnessione(giocatore,partita,server);
				try {
					risp=azione.esegui();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("comando non valido");
				}
				
				
				for(int i=0;i<ordine.length;i++){
					if(ordine[i]==giocatore.getID()){
						((Server) server).getIdSub().get(giocatore.getID()).remove(i);
					}	
				}
				((Server)server).publish(new Messaggio(risp.getMessaggioBroadcast()), giocatore.getID());
				
				Giocatore primo=partita.getGiocatori().get(0);
				
				if(partita.numeroUmaniInGioco()>0)
					server.startTimer(partita, primo);
				else{
					try {
						server.publish(new Messaggio(new TerminaPartita(giocatore, partita,server).esegui().getMessaggioBroadcast()), giocatore.getID());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("comando non valido");
					}
				}
		} catch (InterruptedException e) {
		}
	}
}