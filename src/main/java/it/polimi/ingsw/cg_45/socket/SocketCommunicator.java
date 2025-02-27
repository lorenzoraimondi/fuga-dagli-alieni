package it.polimi.ingsw.cg_45.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**This class implements the methods that permit a socket communication between client and server,
 * granting the possibility to send and receive {@code Object} on the net.
 * 
 * @author Lorenzo
 *
 */
public class SocketCommunicator implements Communicator {

	Socket socket;
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
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void close() {
        try {
            socket.close();
        } catch (IOException e) {
        } finally {
        		socket = null;
        }
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
	@Override
	public Object receiveO() throws IOException {
		
		try {
			return inO.readObject();
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Socket getSocket(){
		return socket;
	}	
	
}
