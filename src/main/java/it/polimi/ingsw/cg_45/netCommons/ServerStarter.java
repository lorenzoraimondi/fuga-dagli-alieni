package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.rmi.RMIServerMain;
import it.polimi.ingsw.cg_45.view.Server;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ServerStarter {

	public static void main(String[] args) throws RemoteException {
		String scelta;
		Scanner stdin = new Scanner(System.in);
		
		do{
			System.out.println("Quale protocollo di comunicazione utilizzare? [ RMI | SOCKET ]");
			scelta=stdin.nextLine().toLowerCase();
		} while (!(scelta.equals("rmi") || scelta.equals("socket")));
		
		
		if(scelta.equals("rmi")){
			RMIServerMain.main(null);
		} else Server.main(null);
		 
		
		stdin.close();
	}

}
