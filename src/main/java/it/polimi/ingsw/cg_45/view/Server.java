package it.polimi.ingsw.cg_45.view;

import it.polimi.ingsw.cg_45.Giocatore;
import it.polimi.ingsw.cg_45.StatoDiGioco;
import it.polimi.ingsw.cg_45.netCommons.Messaggio;
import it.polimi.ingsw.cg_45.netCommons.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Represents a RMI server, the system that hosts all the games and 
 * answers to all players requests. It stores all the games and the associations between
 * them and their players.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Server implements ServerInterface {
	
	private Map<Integer, StatoDiGioco> Partite = new HashMap<Integer, StatoDiGioco>();
	private Map<Integer, ArrayList<BrokerThread>> idSub = new HashMap<Integer, ArrayList<BrokerThread>>();
	//private List<StatoDiGioco> partite = new ArrayList<StatoDiGioco>();
		
	private SalaSocket sala=new SalaSocket();
	
	private Map<Integer, Thread> timers=new HashMap<Integer, Thread>();
	
	private int counter=1;
	private int port;
	private ServerSocket serverSocket;
	
	//Questa lista non Ã¨ inutile???
	private List<BrokerThread> subscribers = new ArrayList<BrokerThread>();
	
	/**Create a game socket server on the specified port.
	 * 
	 * @param port the port on which open the server.
	 */
	public Server(int port) {
		this.port = port;
	}
	
	/**Server's main method, create a new server on 1337 port and starts it.
	 * 
	 */
	public static void main(String[] args) { 
		Server server = new Server(1337);
        server.startServer();
	}
		
	/**Starts the server and all its services, granting clients to connect and 
	 * answering to their requests opening a {@code ClientHandler} thread that will
	 * fulfill each request.
	 * 
	 */
	public void startServer() {
		try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server ready");
            while (isStopped()) {
            	
                Socket socket = serverSocket.accept();
                socket.getOutputStream();
                socket.getInputStream();
                SocketCommunicator sc= new SocketCommunicator(socket);
                new ClientHandler(this,sc).start();
            }
            serverSocket.close();
        } catch (IOException ex) {
            throw new AssertionError("Weird errors with I/O occured, please verify environment config", ex);
        }
	}
		

	private boolean isStopped() {
		return true;
	}

	
	//INUTILE?
	public List<BrokerThread> getSubscribers() {
		return subscribers;
	}

	 
	
	
	
	
	

	/**{@inheritDoc}
	 * 
	 */
	@Override
	public int getCounter() {
		return counter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void incCounter() {
		this.counter++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Integer, StatoDiGioco> getPartite() {
		return Partite;
	}

	/**Send a message to all the players involved in a certain game. Given the id of the player from
	 * which an action starts, is found the relative game and the message published to all of its
	 * players.
	 * 
	 * @param messaggio the message to send.
	 * @param id the id number of the player from which the action starts.
	 */
	@Override
	public void publish(Messaggio msg, int id){
		List<BrokerThread> subs=idSub.get(id);
		
		if(subs!=null){
			System.out.println("Publishing message");
			for (BrokerThread sub : subs) {
				sub.dispatchMessage(msg);
			}
		}else{
			/////
			System.out.println("Non c'Ã¨ iscritto nessuno...");
			//System.out.println("Devo pubblicare all'id "+id);
			//sala.publish(msg, id);
			
		}
	
	}

	public Map<Integer, Thread> getTimers(){
		return timers;
	}
	
	public void startTimer(StatoDiGioco partita, Giocatore giocatore){
		Timer timerTurno=new Timer(partita,giocatore,this);
		Thread t=new Thread(timerTurno);
		timers.put(giocatore.getID(), t);
		t.start();
	}
	
	/**
	 * 
	 * @return the waiting player's room.
	 */
	public SalaSocket getSala() {
		return sala;
	}
	
	/*public void addPartita(StatoDiGioco partita){
		partite.add(partita);
	}*/

	/**
	 * 
	 * @return the map that associate every client's id to the relative client's connection interface.
	 */
	public Map<Integer, ArrayList<BrokerThread>> getIdSub() {
		return idSub;
	}

}

