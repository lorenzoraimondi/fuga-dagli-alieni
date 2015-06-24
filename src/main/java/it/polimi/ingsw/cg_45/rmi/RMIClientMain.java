package it.polimi.ingsw.cg_45.rmi;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


/**Represents the starting class of the RMI client. Is used to create
 * a new client and export it on the RMI registry, to get it available to the server, and then permit
 * to the player to take part of a game.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class RMIClientMain {
	
	/**Creates a new RMI client and starts its service by creating a new registry and
	 * importing server stub on the port 29999. Once done it, the client ask to the player for a name 
	 * and in which map he wants to play, in way to register to the server; making this request the 
	 * client exports its stub to the server, so it can call client's methods. From server confirm 
	 * the client obatin its id number, and once started the game can send commands calling server methods.
	 * 
	 * @param args {@code String} array which contains server's ip address in the firs slot.
	 * 
	 * @throws ClassNotFoundException
	 * @throws ConnectException
	 * @throws NotBoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, ConnectException,NotBoundException, IOException { 
	    
		
		RMIServerInterface server;
		RMIClient client;
		Registry registry;
		String command = "";
        String nome = "";
        String ip = "";
        ip = args[0];
        //ip="127.0.0.1";
        
        client = new RMIClient();
	   	//client = new RMIClient(ip, 29999);
	   	registry = LocateRegistry.getRegistry(29999);
	   	server = (RMIServerInterface) registry.lookup("server");	
	   
	   	Scanner stdin=new Scanner(System.in);
	   	
	   	System.out.println("Inserire il proprio nome");
        
        do{
        	nome = stdin.nextLine();
        	if(nome.isEmpty())
        		System.out.println("Inserire il proprio nome");
        }while(nome.isEmpty());
        
	    System.out.println("Scegliere la mappa dove giocare: FERMI || GALILEI || GALVANI");
	       
	    do{
	    	command = stdin.nextLine().toLowerCase();
	         
	    if(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")))
		    System.out.println("mappa inesistente");
	    }while(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")));
		
		
		
		String conferma=server.registrazione((RMIClientInterface)UnicastRemoteObject.exportObject(client,0), command+"-"+nome);
		
		client.setId(conferma);
	
		System.out.println(conferma.split("-")[1]);
		System.out.println(nome+", sei "+conferma.split("-")[2]);
		

        do {
         	command = stdin.nextLine().toLowerCase();
         	String risposta=server.svolgiAzione(client.getId(), command);
         	if(risposta!=null)
         		System.out.println(risposta);             

         } while (!"exit".equals(command));
        stdin.close();

	}
	
}
