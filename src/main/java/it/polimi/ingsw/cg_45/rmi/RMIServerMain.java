package it.polimi.ingsw.cg_45.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**Represents the starting class of the RMI Server. Is used to create
 * a new server and export it on the RMI registry, to get it available to the client.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class RMIServerMain {
	
	private RMIServerMain(){
		
	}

	/**Creates a new RMI server, and starts its service by creating a new registry and
	 * exporting server stub on the port 29999. Once done it, the server is ready to 
	 * provide his methods to the requesting clients.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
        
        RMIServer handler=new RMIServer();
		
		Registry registry = LocateRegistry.createRegistry(29999);
		
		RMIServerInterface stub = (RMIServerInterface)UnicastRemoteObject.exportObject(handler, 29999);
		
		registry.rebind("server", stub);
		
		System.out.println("RMI Server ready");
		
		
		
		
		
		

	}


	
}
