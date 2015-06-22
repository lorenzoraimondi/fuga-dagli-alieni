package it.polimi.ingsw.cg_45.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerMain {

	public static void main(String[] args) throws RemoteException {
        
        RMIServer handler=new RMIServer(29999);
		System.out.println("Starting the Broker Service");
		
		Registry registry = LocateRegistry.createRegistry(29999);
		
		RMIServerInterface stub = (RMIServerInterface)UnicastRemoteObject.exportObject(handler, 29999);
		
		registry.rebind("server", stub);
		System.out.println("Service Started");
		
		System.out.println("Publishing message...");
		
		
		
		

	}


	
}
