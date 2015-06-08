package it.polimi.ingsw.cg_45.view;

import java.io.Serializable;

public class PacchettoAzione implements Serializable{

	private final int id;
	private final String comando;
	
	public PacchettoAzione(int id, String comando){
		this.id=id;
		this.comando=comando;
	}

	public int getId() {
		return id;
	}

	public String getComando() {
		return comando;
	}
	
}
