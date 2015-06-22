package it.polimi.ingsw.cg_45.netCommons;

import java.io.Serializable;

public class Messaggio implements Serializable{
	
	private final String msg;
	
	public Messaggio(String msg){
		this.msg=msg;
	}
	
	public String getMessaggio(){
		return this.msg;
	}
}
