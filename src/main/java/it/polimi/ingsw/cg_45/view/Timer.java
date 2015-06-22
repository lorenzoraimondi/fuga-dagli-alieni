package it.polimi.ingsw.cg_45.view;

import java.io.IOException;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.Situazione;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.Umano;
import it.polimi.ingsw.cg_45.controller.Azione;
import it.polimi.ingsw.cg_45.controller.Disconnessione;
import it.polimi.ingsw.cg_45.controller.RispostaController;
import it.polimi.ingsw.cg_45.controller.TerminaPartita;

public class Timer implements Runnable{
	private StatoDiGioco partita;
	private Giocatore giocatore;
	private Azione azione;
	private int secondi;
	private RispostaController risp;
	private Server server;
	private int flag;
	
	public Timer(StatoDiGioco partita, Giocatore giocatore,Server server){
		this.giocatore=giocatore;
		this.partita=partita;
		this.server=server;
		secondi=30;
		flag=0;
	}

	public void setFlag(){
		this.flag=1;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("timer partito");
		try {
			Thread.sleep((long)secondi*1000);
			if(flag==0){
				azione=new Disconnessione(giocatore,partita,server);
				try {
					risp=azione.esegui();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("comando non valido");
				}
				
				int[] ordine=new int[partita.getGiocatori().size()];
				for(int i=0;i<ordine.length;i++){
					ordine[i]=partita.getGiocatori().get(i).getID();
				}
				int swap;
				for(int j=0;j<ordine.length-1;j++){
					for(int i=0;i<ordine.length-1;i++){
						if(ordine[i]>ordine[i+1]){
							swap=ordine[i+1];
							ordine[i+1]=ordine[i];
							ordine[i]=swap;
							}
					}
				}
				System.out.println(server.getIdSub());
				for(int i=0;i<ordine.length-1;i++){
					if(ordine[i]==giocatore.getID()){
						System.out.println("posizione da rimuovere: "+i);
						System.out.println("posizione "+i);
						server.getIdSub().get(giocatore.getID()).remove(i);
					}	
				}
				System.out.println(server.getIdSub());
				server.publish(new Messaggio(risp.getMessaggioBroadcast()), giocatore.getID());
				//if(partita.getGiocatori().get(0).getSituazione()==Situazione.ATTIVO)
					//server.startTimer(partita, partita.getGiocatori().get(0));
				for(Giocatore g:partita.getGiocatori()){
					if(g.getSituazione()==Situazione.ATTIVO && g instanceof Umano)
						server.startTimer(partita, partita.getGiocatori().get(0));
				}
				try {
					server.publish(new Messaggio(new TerminaPartita(giocatore, partita,server).esegui().getMessaggioBroadcast()), giocatore.getID());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("timer interrotto");
		}
	}
}
