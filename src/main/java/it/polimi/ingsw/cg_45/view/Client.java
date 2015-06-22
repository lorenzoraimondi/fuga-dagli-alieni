package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;



//funziona esattamente come esempio del lab, invio comandi e ricevo risposte in base a cosa ho scritto
public class Client {
	
	private int id=0;
		
	private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
	}
	
	public void startClient() throws ClassNotFoundException, ConnectException {
		try {
			//pubsub
			//new SubThread(ID).start();
            String command = "";
            String nome = "";
            Scanner stdin=new Scanner(System.in);
            Socket socket,subSocket;
            
            //Socket subSocket;
            SocketCommunicator server,subServer;
            //SocketCommunicator subServer;
            
            
            //subSocket = new Socket(ip, port);
        	//subServer = new SocketCommunicator(subSocket);
        	//new SubThread(subSocket).start();
        	
            try{
            	subSocket = new Socket(ip, port);
            } catch (ConnectException e){
            	throw new ConnectException();
            }
            
            System.out.println("Inserire il proprio nome");
            nome = stdin.nextLine();
            System.out.println("Scegliere la mappa dove giocare: FERMI || GALILEI || GALVANI");
            
        	subServer = new SocketCommunicator(subSocket);
            do{
            	command = stdin.nextLine().toLowerCase();
            //server.send(command);
            if(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")))
            	System.out.println("mappa inesistente");
            }while(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")));
            subServer.send(new PacchettoAzione(id,command+" "+nome));
            Messaggio response = (Messaggio)subServer.receiveO();
            this.setId(response.getMessaggio());
            System.out.println(response.getMessaggio().split("-")[1]);
            //System.out.println("Il tuo id è "+id);
            //
            System.out.println(nome+", sei "+response.getMessaggio().split("-")[2]);
            //
            //new SubThread(socket).start();
            new SubThread(subServer).start();
            //socket.close();
            //server.close();
            //
            subSocket.shutdownOutput();
            //

           do {
            	command = stdin.nextLine().toLowerCase();
                socket = new Socket(ip, port);
            	server = new SocketCommunicator(socket);
                //server.send(command);
                server.send(new PacchettoAzione(id,command));
                Messaggio rixp = (Messaggio)server.receiveO();
                if(rixp!=null)
                	System.out.println(rixp.getMessaggio());
                
                socket.close();
                server.close();
                

            } while (!"exit".equals(command));

            
            stdin.close();
		} catch (ConnectException e){
		 throw new ConnectException();
		} catch (IOException ex) {
            throw new AssertionError("Weird errors with I/O occured, please verify environment config", ex);
		}
		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, ConnectException { 
		Client client = new Client(args[0], 1337);
		//Client client = new Client("127.0.0.1", 1337);
		try{
			client.startClient();	
		} catch (ConnectException e){
			throw new ConnectException();
		}
        
         
    }

	public int getId() {
		return id;
	}

	public void setId(String msg) {
		String a=msg.split("-")[0];
		int numeroId=Integer.parseInt(a);
		this.id=numeroId;
	}
	
}
