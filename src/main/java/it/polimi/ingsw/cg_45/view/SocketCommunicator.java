package it.polimi.ingsw.cg_45.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommunicator implements Communicator {

	Socket socket;
	Scanner in;
	ObjectInputStream inO;
	//PrintWriter out;
	ObjectOutputStream out;
	
	public SocketCommunicator(Socket s) throws IOException {
		
		this.socket=s;
		
            //in = new Scanner(socket.getInputStream());
		out=new ObjectOutputStream(socket.getOutputStream());
		
        	inO = new ObjectInputStream(socket.getInputStream());
        
        	
            //out = new PrintWriter(socket.getOutputStream());
            
            
        
	}

	public void send(Object msg) throws IOException{
		out.writeObject(msg);
		//out.flush(); 
	}

	//aggiunto per inviare numeri
	/*public void sendInt(int num){
		out.print(num);
		out.flush();
	}*/
    @Override
	public String receive(){
		return in.nextLine();
	}

    //aggiunto per ricevere numeri
    public int receiveInt(){
    	return in.nextInt();
    }
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

	@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object receiveO() throws IOException, ClassNotFoundException{
		return inO.readObject();
		
	}
	
	//Per prova pub sub, perchè nel registraClient ho il client e non il socket
	public Socket getSocket(){
		return socket;
	}
	

	
	
	
}
