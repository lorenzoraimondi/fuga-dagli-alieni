package it.polimi.ingsw.cg_45.socket;

import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.PacchettoAzione;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/**Represents the client player, permits the contact and the connection to a socket server
 * and, once connected and subscribed to a game, permits to send command to be executed
 * on the server, getting back its response.  
 * 
 * @author Lorenzo Raimondi
 *
 */
public class SocketClient {
	
	private int id=0;
		
	private String ip;
    private int port;

    /**Creates a new game client ready to be connected to the server on the specified ip address and
     * accessing to the specified port number. 
     * 
     * @param ip server ip address String. 
     * @param port integer number of the port from which access to the server.
     */
    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
	}
	
	protected void startClient() throws ConnectException {
		try {
			
            String command = "";
            String nome="";
            Scanner stdin=new Scanner(System.in);
            Socket socket,subSocket;
            SubThread st;
            SocketCommunicator server,subServer;
        	
            try{
            	subSocket = new Socket(ip, port);
            } catch (ConnectException e){
            	throw new ConnectException();
            }
            
            System.out.println("Inserire il proprio nome");
            
            do{
            	nome = stdin.nextLine();
            	if(nome.isEmpty())
            		System.out.println("Inserire il proprio nome");
            }while(nome.isEmpty());
           
            System.out.println("Scegliere la mappa dove giocare: FERMI || GALILEI || GALVANI");
               
        	subServer = new SocketCommunicator(subSocket);
            
        	do{
            	command = stdin.nextLine().toLowerCase();
            	if(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")))
            		System.out.println("mappa inesistente");
            }while(!(command.contentEquals("fermi")||command.contentEquals("galilei")||command.contentEquals("galvani")));
            
        	subServer.send(new PacchettoAzione(id,command+" "+nome));
            Messaggio response = (Messaggio)subServer.receiveO();
            this.setId(response.getMessaggio());
            
            System.out.println(response.getMessaggio().split("-")[1]);
            System.out.println(nome+", sei "+response.getMessaggio().split("-")[2]);
           
            st=new SubThread(subServer);
            st.start();
           
            subSocket.shutdownOutput();
            
           do {
            	command = stdin.nextLine().toLowerCase();
                socket = new Socket(ip, port);
            	server = new SocketCommunicator(socket);
                server.send(new PacchettoAzione(id,command));
                
               
               	Messaggio messaggioRisposta = (Messaggio)server.receiveO();
               	if(messaggioRisposta!=null)
               	System.out.println(messaggioRisposta.getMessaggio());
             
                socket.close();
                server.close();
                

            } while (!"exit".equals(command));

            
            stdin.close();
		} catch (ConnectException e){			
			throw new ConnectException();
		} catch (IOException ex) {
			System.out.println("Qualcosa è andato storto, riprova.");
		}
		
	}
	
	/**Starts the client service. After creating a new client, on 1337 port and on the ip address
	 * given from {@code args}, the client is started: it request to the player a nickname and the map in which
	 * he desires to play, and then tries to contact the server. Once subscribed the clients wait for the game
	 * to start. Started the game the client continuously accept commands from the player and send them
	 * to the server, to be fulfilled by it.
	 * 
	 * @param args a String array where in the first slot is stored server ip address.
	 * 
	 * @throws ClassNotFoundException
	 * @throws ConnectException
	 */
	public static void main(String[] args) throws ClassNotFoundException, ConnectException { 
		SocketClient client = new SocketClient(args[0], 1337);
		try{
			client.startClient();	
		} catch (ConnectException e){
			System.out.println("Impossibile connettersi al server all'indirizzo e sulla porta desiderati.");
			throw new ConnectException();
		}
         
    }

	/**
	 * 
	 * @return client's integer identification number.
	 */
	public int getId() {
		return id;
	}

	/**From server's confirm of client's subscription is obtained the unique id number
	 * that will identify the client. It is the first characters sequence in the received
	 * String, directly followed by a "-". For example this method can receive the message
	 * <i>13-Iscritto alla partita come alieno</i> and will set 13 as client's id.
	 * 
	 * @param msg the confirm String for client's subscription.
	 */
	public void setId(String msg) {
		String a=msg.split("-")[0];
		int numeroId=Integer.parseInt(a);
		this.id=numeroId;
	}
	
}
