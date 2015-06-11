package it.polimi.ingsw.cg_45.controller;

import java.io.Serializable;

public class RispostaController implements Serializable{

	private final String messaggioClient;
	private final String messaggioBroadcast;
	
	
	public RispostaController(String c, String b){
		this.messaggioClient=c;
		this.messaggioBroadcast=b;
	}


	public String getMessaggioClient() {
		return messaggioClient;
	}


	public String getMessaggioBroadcast() {
		return messaggioBroadcast;
	}


}
