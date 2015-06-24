package it.polimi.ingsw.cg_45.netCommons;

import java.io.Serializable;

/**Represents a message object that can be sent on the net.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Messaggio implements Serializable{
	
	private final String msg;
	
	/**Create a new message with the {@code String} to send. 
	 * 
	 * @param msg {@code String } line to send.
	 */
	public Messaggio(String msg){
		this.msg=msg;
	}
	
	/**
	 * 
	 * @return the String line that compose the message.
	 */
	public String getMessaggio(){
		return this.msg;
	}
}
