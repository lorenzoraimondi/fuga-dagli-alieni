package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.Giocatore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends Azione {
	private String messaggio;
	
	public Chat(String m, Giocatore giocatore){
		this.messaggio=m;
		this.giocatore=giocatore;
	}

	@Override
	public RispostaController esegui() throws IOException {
		Date date= new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		//System.out.println(date.getTime());
		
		System.out.println("["+sdf.format(date)+"] "+giocatore.getNome()+" : "+messaggio);
		return new RispostaController("Messaggio inviato","\n"+"["+sdf.format(date)+"] "+giocatore.getNome()+": "+messaggio);
	}

	@Override
	protected boolean controlli() {
		return true;
	}

}
