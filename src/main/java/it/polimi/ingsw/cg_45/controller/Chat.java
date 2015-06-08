package it.polimi.ingsw.cg_45.controller;

import java.io.IOException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends Azione {
	private String messaggio;
	private int id;
	
	public Chat(String m, int i){
		this.messaggio=m;
		this.id=i;
	}

	@Override
	public RispostaController esegui() throws IOException {
		Date date= new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		//System.out.println(date.getTime());
	
		
		return new RispostaController("Messaggio inviato","["+sdf.format(date)+"] Giocatore "+id+" : "+messaggio);
	}

	@Override
	protected boolean controlli() {
		return true;
	}

}
