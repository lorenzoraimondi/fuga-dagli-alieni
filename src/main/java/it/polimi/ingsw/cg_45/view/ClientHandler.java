package it.polimi.ingsw.cg_45.view;

import java.io.IOException;

import it.polimi.ingsw.cg_45.*;
import it.polimi.ingsw.cg_45.controller.*;

//
public class ClientHandler extends Thread {
	//ho aggiunto queste tre variabili che però devono essere recuperate in base al giocatore che sta giocando
	/*Mappa mappa=new Fermi();
	StatoDiGioco partita=new StatoDiGioco();
	Giocatore giocatore=new Alieno(1,1);*/
	
	Communicator client;
	Server server;
	int id;
	public ClientHandler(Server s, Communicator c) {
		client = c;
		server = s;
	}
	
	@Override
	public void run(){
		//String command ="";
		PacchettoAzione prova = null;
		
		
		//do {
            //command =client.receive();
			try {
				prova=(PacchettoAzione)client.receiveO();
				id=prova.getId();
				System.out.println("Mi è arrivato il pacchetto");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
            TraduttoreComandi tc=new TraduttoreComandi(prova,server,client);
            try {
				Azione prova1=(Azione)tc.traduci();
				System.out.println("Tradottoto il pacchetto");
				
				RispostaController risposta=prova1.esegui();
				System.out.println("Eseguito il pacchetto");
				
				client.send(new Messaggio(risposta.getMessaggioClient()));
				if(risposta.getMessaggioBroadcast()!=null)
					server.publish(new Messaggio(risposta.getMessaggioBroadcast()),id);
			} catch (ClassCastException e){
				try {
					client.send(new Messaggio("Comando non valido"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			/*client.send(server.getCounter());
			server.incCounter();
			client.close();*/
    }
	
	//prendo un comando alla volta perchè non ho ancora ben capito come usare la string tokenizer :)
	/*private void opera(String command){
		if(command.contentEquals("muovi")){
			client.send("scrivi settore");
			String settore=client.receive();
			try{if(mappa.getMappa().containsKey(new Coordinate(settore))){
				System.out.println("movimento in "+settore);
				//Azione azione=new Movimento(partita,giocatore,mappa.getMappa().get(new Coordinate(settore)));
				//azione.esegui();
				client.send("movimento effettuato");}
			else
				client.send("settore non valido, reinserire l'azione");
			}catch (ArrayIndexOutOfBoundsException e){
				client.send("settore non valido, reinserire l'azione");
			}
		}
		else if(command.contentEquals("exit"))
			client.send("arrivederci alla prossima!!");
		else
			client.send("azione non valida");
	}*/
}


