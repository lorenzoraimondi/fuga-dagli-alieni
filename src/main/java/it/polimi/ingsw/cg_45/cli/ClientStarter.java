package it.polimi.ingsw.cg_45.cli;

import it.polimi.ingsw.cg_45.rmi.RMIClientMain;
import it.polimi.ingsw.cg_45.socket.SocketClient;

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

/**It's the game client starter class. This class starts a game console from which the player
 * can connect to an RMI or Socket Server and join a game.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class ClientStarter {
	
	private static final String PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	private ClientStarter(){
	}

	/**Starts game console for the player. At the beginning is viewed an introduction file
	 * {@link intro.txt} where are specified instructions for the commands. Then the user is
	 * guided to insert server's IP address and type. Once connected he can begin to play.
	 * If the server is unavailable he can try to connect to another type of server.
	 *
	 * @throws ClassNotFoundException
	 * @throws NotBoundException
	 * @throws IOException
	 */
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
					SocketClient.main(ip);
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
