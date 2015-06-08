package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.net.Socket;

public interface Communicator {
	
	//stesso del lab...si potrebbeero aggiungere le due funz per invio/ricezione di interi, quando avremo capito se sono necessarie
	void send(String msg);
	String receive();
	void close();
	void send(Object msg) throws IOException;
	Object receiveO() throws IOException, ClassNotFoundException;
	//Aggiunta per prova pubsub
	public Socket getSocket();
}
