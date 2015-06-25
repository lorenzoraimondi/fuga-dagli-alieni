package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.rmi.RMIServerMain;
import it.polimi.ingsw.cg_45.view.Server;

import java.rmi.RemoteException;
import java.util.Scanner;

/**It's the game server starter class. This class starts a game console from which the RMI/Socket Servers 
 * are started and so permit users to connect. 
 * 
 * @author Lorenzo Raimondi
 *
 */
public class ServerStarter {
	
	private ServerStarter(){
	}

	/**Starts game server console. Then the user is guided to insert 
	 * what type of server start, choosing between RMI, Socket, or both of them.
	 * Once started the users can begin to connect and play.
	 * 
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		String scelta;
		Scanner stdin = new Scanner(System.in);
		
		do{
			System.out.println("Quale protocollo di comunicazione utilizzare? [ RMI | SOCKET | entrambi ]");
			scelta=stdin.nextLine().toLowerCase();
		} while (!("rmi".equals(scelta) || "socket".equals(scelta) || "entrambi".equals(scelta)));
		
		if("rmi".equals(scelta)){
			RMIServerMain.main(null);
		} else if("socket".equals(scelta)){
			Server.main(null);
		} else {
			RMIServerMain.main(null);
			Server.main(null);
		}
		
		stdin.close();
		
	}

}
