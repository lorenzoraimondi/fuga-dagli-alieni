package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**This class implements the methods that permit a socket communication between client and server,
 * granting the possibility to send and receive {@code Object} on the net.
 * 
 * @author Lorenzo
 *
 */
public class SocketCommunicator implements Communicator {

	Socket socket;
	//Scanner in;
	ObjectInputStream inO;
	ObjectOutputStream out;
	
	/**Create a new communicator for send and receive {@code Object} from the {@code Socket}
	 * that represents the corresponding client.
	 * 
	 * @param s the {@code Socket} to be connected to for {@code Object} send/receive.
	 * @throws IOException
	 */
	public SocketCommunicator(Socket s) throws IOException {
		
		this.socket=s;
		out=new ObjectOutputStream(socket.getOutputStream());
		inO = new ObjectInputStream(socket.getInputStream());
    
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(Object msg) throws IOException{
		out.writeObject(msg); 
	}

    /*@Override
	public String receive(){
		return in.nextLine();
	}*/

    /*aggiunto per ricevere numeri
    public int receiveInt(){
    	return in.nextInt();
    }*/
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("something wrong happened while closing a socket, who cares? I don't need it anymore: " + e.getMessage());
        } finally {
        		socket = null;
        }
    }

	/*@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
	
	}*/

    /**
     * {@inheritDoc}
     */
	@Override
	public Object receiveO() throws IOException, ClassNotFoundException{
		return inO.readObject();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Socket getSocket(){
		return socket;
	}	
	
}
