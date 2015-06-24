package it.polimi.ingsw.cg_45.netCommons;

import java.io.Serializable;

/**Represents an action packet of the command to be executed on the server. It can be sent on the
 * net, so once received by the server it can translate it and execute the relative action.  
 * 
 * @author Lorenzo Raimondi
 *
 */
public class PacchettoAzione implements Serializable{

	private final int id;
	private final String comando;
	
	/**Create a new action packet starting from client's command and id, so the server
	 * can translate it into an action and associate it to the relative player.
	 * 
	 * @param id client's id number.
	 * @param comando client's input command.
	 */
	public PacchettoAzione(int id, String comando){
		this.id=id;
		this.comando=comando;
	}

	/**
	 * 
	 * @return the id number of the client that has sent the packet.
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return the client's command {@code String} input. 
	 */
	public String getComando() {
		return comando;
	}
	
}
