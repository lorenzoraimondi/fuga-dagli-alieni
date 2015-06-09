package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.cg_45.*;

public class Server {
	
	//palesemente uguale a quello del lab, cambia come lavora il clientHandler
	private Map<Integer, StatoDiGioco> Partite = new HashMap<Integer, StatoDiGioco>();
	private Map<Integer, ArrayList<BrokerThread>> idSub = new HashMap<Integer, ArrayList<BrokerThread>>();
	private List<StatoDiGioco> partite = new ArrayList<StatoDiGioco>();
	
	private Sala sala=new Sala();
	
	private List<BrokerThread> clientConnessi = new ArrayList<BrokerThread>();
	
	/*
	private StatoDiGioco partitaProva;
	private ArrayList<Giocatore> listaGiocatori=new ArrayList<Giocatore>();
	private Giocatore g1;
	private Giocatore g2;
	private Giocatore g3;
	private Fermi mappa=new Fermi();
	
	*/
	
	private List<BrokerThread> subscribers = new ArrayList<BrokerThread>();
		
	public ArrayList<BrokerThread> getSubscribers() {
		return (ArrayList<BrokerThread>) subscribers;
	}

	private int counter=1;
	private int port;
	private ServerSocket serverSocket; 
	
	public Server(int port) {
		this.port = port;
		/*
		
		g1=new Alieno(1,0);
		g2=new Alieno(2,0);
		g3=new Umano(3,1);
		
		g1.setPosizione(mappa.getMappa().get(new Coordinate("L09")));		
		g2.setPosizione(mappa.getMappa().get(new Coordinate("L09")));
		g3.setPosizione(mappa.getMappa().get(new Coordinate("L10")));
		g1.setStato(Stato.INIZIO);
		g2.setStato(Stato.TURNOTERMINATO);
		g3.setStato(Stato.TURNOTERMINATO);
		listaGiocatori.add(g1);
		listaGiocatori.add(g2);
		listaGiocatori.add(g3);
		partitaProva=new StatoDiGioco(listaGiocatori,mappa);
		Partite.put(1, partitaProva);
		Partite.put(2, partitaProva);
		Partite.put(3, partitaProva);
		
		*/
	}
	
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

	public static void main(String[] args) { 
		Server server = new Server(1337);
        server.startServer();
	}

	public int getCounter() {
		return counter;
	}

	public void incCounter() {
		this.counter++;
	}

	public Map<Integer, StatoDiGioco> getPartite() {
		return Partite;
	}

	public void publish(Messaggio msg, int id){
		ArrayList<BrokerThread> subscribers=idSub.get(id);
		
		if(subscribers!=null){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.dispatchMessage(msg);
			}
		}else{
			//System.err.println("No subscribers!!");
			sala.publish(msg, id);
			
		}
	
	}

	public Sala getSala() {
		return sala;
	}
	
	public void addPartita(StatoDiGioco partita){
		partite.add(partita);
	}

	public Map<Integer, ArrayList<BrokerThread>> getIdSub() {
		return idSub;
	}

}

