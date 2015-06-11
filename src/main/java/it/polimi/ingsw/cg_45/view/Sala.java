package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Fermi;
import it.polimi.ingsw.cg_45.Galvani;
import it.polimi.ingsw.cg_45.Mappa;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class Sala extends Thread{
	private String scelta;
	private Communicator client;
	private Server server;
	//
	private String nomeGiocatore;
	//
	
	private List<Accettazione> giocatoriFermi=new ArrayList<Accettazione>();
	private List<Accettazione> giocatoriGalilei=new ArrayList<Accettazione>();
	private List<Accettazione> giocatoriGalvani=new ArrayList<Accettazione>();
	
	private Timer fermi;
	private Timer galilei;
	private Timer galvani;
	
	public void cancelloTimer(String mappa){
		if(mappa.contentEquals("Fermi"))
			fermi.cancel();
		else if(mappa.contentEquals("Galvani"))
			galvani.cancel();
		else
			galilei.cancel();
	}
	private int seconds=15;
	
	public Sala(){
		
	}
	
	//public void aggiungiGiocatore(String scelta,Communicator client, Server server){
	public int aggiungiGiocatore(String scelta,Communicator client, Server server, String nome){
		this.scelta=scelta;
		this.client=client;
		this.server=server;
		this.nomeGiocatore=nome;
		return this.esegui();
	}
	
	//private void esegui(){
	private int esegui(){
		BrokerThread brokerThread = new BrokerThread(client);
		brokerThread.start();
		//
		int posizione=0;
		//
		//server.getSubscribers().add(brokerThread);
		//System.out.println("added new subscribers");
		if(scelta.contentEquals("Fermi")){
			//
			Accettazione accettazione=new Accettazione(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriFermi.add(accettazione);
			//giocatoriFermi.add(new Accettazione(brokerThread,server.getCounter()));
			//
			posizione=giocatoriFermi.indexOf(accettazione);
			//
			if(giocatoriFermi.size()==2){
				fermi=new Timer();
				System.out.println("creo timerFermi");
				fermi.schedule(new CreaPartita(giocatoriFermi,server,scelta,this),seconds*1000);
				}
			if(giocatoriFermi.size()==8){
				//Da sistemare???
				fermi.schedule(new CreaPartita(giocatoriFermi,server,scelta,this),0);
				
			}
		}
		else if(scelta.contentEquals("Galilei")){
			Accettazione accettazione=new Accettazione(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriGalilei.add(accettazione);
			posizione=giocatoriGalilei.indexOf(accettazione);
			if(giocatoriGalilei.size()==2){
				galilei=new Timer();
				System.out.println("creo timerGalilei");
				galilei.schedule(new CreaPartita(giocatoriGalilei,server,scelta,this),seconds*1000);}
			if(giocatoriGalilei.size()==8){
				galilei.schedule(new CreaPartita(giocatoriGalilei,server,scelta,this),0);
			}
		}
		else if(scelta.contentEquals("Galvani")){
			Accettazione accettazione=new Accettazione(brokerThread,server.getCounter(),nomeGiocatore);
			giocatoriGalvani.add(accettazione);
			posizione=giocatoriGalvani.indexOf(accettazione);
			if(giocatoriGalvani.size()==2){
				galvani=new Timer();
				System.out.println("creo timerGalvani");
				galvani.schedule(new CreaPartita(giocatoriGalvani,server,scelta,this),seconds*1000);}
			if(giocatoriGalvani.size()==8){
				galvani.schedule(new CreaPartita(giocatoriGalvani,server,scelta,this),0);
			}
		}
		else
			System.out.println("scelta Sbagliata");
		
		return posizione;
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
		List<BrokerThread> threadSubsFermi=new ArrayList<BrokerThread>();
		List<BrokerThread> threadSubsGalvani=new ArrayList<BrokerThread>();
		List<BrokerThread> threadSubsGalilei=new ArrayList<BrokerThread>();
		int s=0;
		
		System.out.println(giocatoriFermi);
		System.out.println(giocatoriGalvani);
		System.out.println(giocatoriGalilei);
		
		System.out.println(id);
		
		for(Accettazione a : giocatoriFermi){
			if(a.getId()==id){
				s=1;
				for(Accettazione a2: giocatoriFermi){
					threadSubsFermi.add(a2.getbt());
				}
			}
		}
		
		for(Accettazione a : giocatoriGalvani){
			
			if(a.getId()==id){
				s=2;
				for(Accettazione a2: giocatoriGalvani){
					threadSubsGalvani.add(a2.getbt());
				}
			}
		}
		
		System.out.println(threadSubsFermi);
		System.out.println(threadSubsGalvani);
		System.out.println(threadSubsGalilei);
		
		for(Accettazione a : giocatoriGalilei){
				threadSubsGalilei.add(a.getbt());
		}
		if(s==0){
			for (BrokerThread sub : threadSubsGalilei) {
				sub.dispatchMessage(msg);
			}
		}
		else if(s==1){
			for (BrokerThread sub : threadSubsFermi) {
				sub.dispatchMessage(msg);
			}
		}
		else
			for(BrokerThread sub : threadSubsGalvani){
				sub.dispatchMessage(msg);
			}
			
		}
	
}
