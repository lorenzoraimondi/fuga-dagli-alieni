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
			System.out.println("Quale protocollo di comunicazione utilizzare? [ RMI | SOCKET | entrambi ]");
			scelta=stdin.nextLine().toLowerCase();
		} while (!(scelta.equals("rmi") || scelta.equals("socket") || scelta.equals("entrambi")));
		//} while (!("rmi".equals(scelta) || "socket".equals("scelta")));
		
		if(scelta.equals("rmi")){
			RMIServerMain.main(null);
		} else if(scelta.equals("socket")){
			Server.main(null);
		} else {
			RMIServerMain.main(null);
			Server.main(null);
		}
		 
		
		stdin.close();
	}

}
