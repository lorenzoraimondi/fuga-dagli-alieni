package it.polimi.ingsw.cg_45.rmi;

import java.rmi.RemoteException;

public class RMIClient implements RMIClientInterface {

	private int id=0;
	
	public RMIClient(String string, int i) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inviaMessaggio(String messaggio) throws RemoteException{
		if(messaggio!=null)
			
			System.out.println(messaggio);
		
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
