package it.polimi.ingsw.cg_45.netCommons;

import it.polimi.ingsw.cg_45.rmi.RMIClientMain;
import it.polimi.ingsw.cg_45.view.Client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientStarter {
	
	private static final String PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	private ClientStarter(){
	}

	public static void main(String[] args) throws ClassNotFoundException, NotBoundException, IOException {
		
		stampaIntro("rsc"+File.separatorChar+"intro.txt");
		
				
		String[] ip=new String[1];
		String scelta="";
		boolean connesso=false;
		
		Scanner stdin = new Scanner(System.in);
		
		do{
			System.out.println("Inserire l'indirizzo ip del server");
			ip[0]=stdin.nextLine();
		} while (!(validate(ip[0])));
		
		while(!connesso){
			connesso=true;
			do{
				System.out.println("Quale protocollo di comunicazione utilizzare? [ RMI | SOCKET ]");
				
				scelta=stdin.nextLine().toLowerCase();
					
			} while (!("rmi".equals(scelta) || "socket".equals(scelta)));
			
			if("rmi".equals(scelta)){
				try{
					RMIClientMain.main(ip);
				} catch (java.rmi.ConnectException e){
					System.out.println("Server RMI non disponibile");
					connesso=false;
				}
			} else {
				try {
					Client.main(ip);
				} catch (java.net.ConnectException e){
					System.out.println("Server SOCKET non disponibile");
					connesso=false;
				}
			
			}
	
		}
			stdin.close();
	}
	
	protected static boolean validate(final String ip){          

	      Pattern pattern = Pattern.compile(PATTERN);
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}
		
	protected static void stampaIntro(String percorsoFile) throws IOException{ 
	    
		List<String> lines = Files.readAllLines(Paths.get(percorsoFile), Charset.defaultCharset());
		    
	    for(String stringa : lines){
	    	System.out.print(stringa+"\n");
	    }
	}

}
