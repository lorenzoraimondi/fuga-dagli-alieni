package it.polimi.ingsw.cg_45.rmi;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIClientMain {
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, ConnectException,NotBoundException, IOException { 
	    
		
		RMIServerInterface server;
		RMIClient client;
		Registry registry;
		String command = "";
        String nome = "";
        String ip = "";
        ip = args[0];
        //ip="127.0.0.1";
        
	   	client = new RMIClient(ip, 29999);
	   	registry = LocateRegistry.getRegistry(29999);
	   	server = (RMIServerInterface) registry.lookup("server");	
	   
	   	Scanner stdin=new Scanner(System.in);
	   	
	    System.out.println("Inserire il proprio nome");
	    nome = stdin.nextLine();
	    System.out.println("Scegliere la mappa dove giocare: FERMI || GALILEI || GALVANI");
	       
	    do{command = stdin.nextLine().toLowerCase();
	         
	    if(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")))
		    System.out.println("mappa inesistente");
	    }while(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")));
		
		
		
		String conferma=server.registrazione((RMIClientInterface)UnicastRemoteObject.exportObject(client,0), command+"-"+nome);
		
		client.setId(conferma);
	
		System.out.println(conferma.split("-")[1]);
		System.out.println(nome+", sei "+conferma.split("-")[2]);
		
			
		
		
		
        /*   */
        //subServer.send(new PacchettoAzione(id,command+" "+nome));
         //Messaggio response = (Messaggio)subServer.receiveO();
         //this.setId(response.getMessaggio());
         //System.out.println(response.getMessaggio().split("-")[1]);
         //System.out.println("Il tuo id è "+id);
         //
         //System.out.println(nome+", sei "+response.getMessaggio().split("-")[2]);
         //
         //new SubThread(socket).start();
         //new SubThread(subServer).start();
         //socket.close();
         //server.close();

        do {
         	command = stdin.nextLine().toLowerCase();
             //socket = new Socket(ip, port);
         	//server = new SocketCommunicator(socket);
             //server.send(command);
             //server.send(new PacchettoAzione(id,command));
         	String risposta=server.svolgiAzione(client.getId(), command);
         	if(risposta!=null)
         		System.out.println(risposta);
             //Messaggio rixp = (Messaggio)server.receiveO();
             //if(rixp!=null)
             	//System.out.println(rixp.getMessaggio());
             
             //socket.close();
             //server.close();
             

         } while (!command.equals("exit"));
        stdin.close();

    }

	
}
