package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class Sala extends Thread{
	private String scelta;
	private Communicator client;
	private Server server;
	
	private List<Accettazione> giocatoriFermi=new ArrayList<Accettazione>();
	private List<Accettazione> giocatoriGalilei=new ArrayList<Accettazione>();
	private List<Accettazione> giocatoriGalvani=new ArrayList<Accettazione>();

	
	private Timer fermi=new Timer();
	private Timer galilei=new Timer();
	private Timer galvani=new Timer();
	
	public void cancelloTimer(String mappa){
		if(mappa=="Fermi")
			fermi.cancel();
		else if(mappa=="Galvani")
			galvani.cancel();
		else
			galilei.cancel();
	}
	private int seconds=3;
	
	public Sala(){
		
	}
	
	public void aggiungiGiocatore(String scelta,Communicator client, Server server){
		this.scelta=scelta;
		this.client=client;
		this.server=server;
		this.esegui();
	}
	
	private void esegui(){
		BrokerThread brokerThread = new BrokerThread(client);
		brokerThread.start();
		//server.getSubscribers().add(brokerThread);
		//System.out.println("added new subscribers");
		if(scelta.contentEquals("Fermi")){
			giocatoriFermi.add(new Accettazione(brokerThread,server.getCounter()));
			if(giocatoriFermi.size()==2){
				System.out.println("creo timerFermi");
				fermi.schedule(new creaPartita(giocatoriFermi,server,scelta,this),seconds*1000);}
			if(giocatoriFermi.size()==8){
				//Da sistemare???
				this.cancelloTimer(scelta);
				fermi.schedule(new creaPartita(giocatoriFermi,server,scelta,this),0);
			}
		}
		else if(scelta.contentEquals("Galilei")){
			giocatoriGalilei.add(new Accettazione(brokerThread,server.getCounter()));
			if(giocatoriGalilei.size()==2){
				System.out.println("creo timerGalilei");
				galilei.schedule(new creaPartita(giocatoriGalilei,server,scelta,this),seconds*1000);}
			if(giocatoriGalilei.size()==8){
				this.cancelloTimer(scelta);
				galilei.schedule(new creaPartita(giocatoriGalilei,server,scelta,this),0);
			}
		}
		else if(scelta.contentEquals("Galvani")){
			giocatoriGalvani.add(new Accettazione(brokerThread,server.getCounter()));
			if(giocatoriGalvani.size()==2){
				System.out.println("creo timerGalvani");
				galvani.schedule(new creaPartita(giocatoriGalvani,server,scelta,this),seconds*1000);}
			if(giocatoriGalvani.size()==8){
				this.cancelloTimer(scelta);
				galvani.schedule(new creaPartita(giocatoriGalvani,server,scelta,this),0);
			}
		}
		else
			System.out.println("scelta Sbagliata");
	}
	
	public List<Accettazione> getListaAccettazione(Mappa mappa) {
		if(mappa instanceof Fermi)
			return giocatoriFermi;
		if(mappa instanceof Galvani)
			return giocatoriGalvani;
		return giocatoriGalilei;
		
	}


	public void svuotaLista(String mappa){
		if(mappa.contentEquals("Fermi"))
			giocatoriFermi.removeAll(giocatoriFermi);
		else if(mappa.contentEquals("Galilei"))
			giocatoriGalilei.removeAll(giocatoriGalilei);
		else
			giocatoriGalvani.removeAll(giocatoriGalvani);
	}
	
	public void publish(Messaggio msg, int id){
		List<BrokerThread> threadSubs=new ArrayList<BrokerThread>();
		for(Accettazione a : giocatoriFermi){
			threadSubs.add(a.getbt());
		}
		
		for (BrokerThread sub : threadSubs) {
			sub.dispatchMessage(msg);
		}
			
		}
	
}
