package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.controller.RispostaController;

import java.io.BufferedReader;
import java.io.IOException;
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
	
	public void startClient() throws ClassNotFoundException {
		try {
			//pubsub
			//new SubThread(ID).start();
            String command = "";
            Scanner stdin = new Scanner(System.in);
            Socket socket,subSocket;
            
            //Socket subSocket;
            SocketCommunicator server,subServer;
            //SocketCommunicator subServer;
            System.out.println("Scegliere la mappa dove giocare: FERMI || GALILEI || GALVANI");
            
            //subSocket = new Socket(ip, port);
        	//subServer = new SocketCommunicator(subSocket);
        	//new SubThread(subSocket).start();
        	
        	
        	subSocket = new Socket(ip, port);
        	subServer = new SocketCommunicator(subSocket);
            command = stdin.nextLine();
            //server.send(command);
            subServer.send(new PacchettoAzione(id,command));
            Messaggio response = (Messaggio)subServer.receiveO();
            this.setId(response.getMessaggio());
            System.out.println(response.getMessaggio().split("-")[1]);
            System.out.println("Il tuo id è "+id);
            //new SubThread(socket).start();
            new SubThread(subServer).start();
            //socket.close();
            //server.close();

           do {
            	socket = new Socket(ip, port);
            	server = new SocketCommunicator(socket);
                command = stdin.nextLine();
                //server.send(command);
                server.send(new PacchettoAzione(id,command));
                Messaggio rixp = (Messaggio)server.receiveO();
                if(rixp!=null)
                	System.out.println(rixp.getMessaggio());
                
                socket.close();
                server.close();
                

            } while (!command.equals("exit"));

            
            stdin.close();
        } catch (IOException ex) {
            throw new AssertionError("Weird errors with I/O occured, please verify environment config", ex);
        }
		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException { 
	    Client client = new Client("127.0.0.1", 1337);
        client.startClient();
         
    }

	public int getId() {
		return id;
	}

	public void setId(String msg) {
		String a=msg.split("-")[0];
		int id=Integer.parseInt(a);
		this.id=id;
	}
	
}
