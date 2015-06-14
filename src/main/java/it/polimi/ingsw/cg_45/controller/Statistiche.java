package it.polimi.ingsw.cg_45.controller;

import it.polimi.ingsw.cg_45.*;

import java.io.IOException;

public class Statistiche extends Azione {

	public Statistiche(Giocatore giocatore, StatoDiGioco partita){
		this.giocatore=giocatore;
		this.model=partita;
	}
	
	@Override
	public RispostaController esegui() throws IOException {
		// TODO Auto-generated method stub	
		
		String messaggio=new String("\nsei un "+giocatore.getClass().getSimpleName()+"\nportata: "+
				giocatore.getPortata()+"\nti trovi in posizione: "+
				giocatore.getPosizione().getCoordinate()+"\nle tue carte sono: "+giocatore.getCarte()
				+"\n\n");
		
		switch(giocatore.getStato()){
		case INIZIO :
			return new RispostaController(messaggio+"mosse valide:\neffettuare il movimento\nusare una carta oggetto (solo se umano)",null);
		case PERICOLO :
			return new RispostaController(messaggio+"mosse valide:\npescare una carta settore\nattaccare (solo se alieno o umano con la carta attacco)\nusare una carta oggetto (solo se umano)",null);
		case SICURO :
			return new RispostaController(messaggio+"mosse valide:\nusare una carta oggetto (solo se umano)\nterminare il turno",null);
		case CARTASCIALUPPA : 
			return new RispostaController(messaggio+"mossa obbligata:\npescare una carta scialuppa",null);
		case ATTACCATO : 
			return new RispostaController(messaggio+"mosse valide:\nusare carta (solo se umano)\nterminare il turno",null);
		case SILENZIO :
			return new RispostaController(messaggio+"mossa obbligata:\nannunicare silenzio",null);
		case CARTABLUFF : 
			return new RispostaController(messaggio+"mossa obbligata:\nannuncia un settore in cui vuoi far rumore",null);
		case CARTABLUFFOGGETTO:
			return new RispostaController(messaggio+"mossa obbligata:\nannuncia un settore in cui vuoi far rumore",null);
		case CARTARIVELA:
			return new RispostaController(messaggio+"mossa obbligata:\nannuncia il tuo settore in cui fare rumore",null);
		case CARTARIVELAOGGETTO:
			return new RispostaController(messaggio+"mossa obbligata:\nannuncia il tuo settore un cui fare rumore",null);
		case SCIALUPPA:
			return new RispostaController(messaggio+"mosse valide:\nusare carta (solo se umano)\nterminare il turno",null);
		case RIVELATO:
			return new RispostaController(messaggio+"mossa obbligata:\npescare una carta oggetto",null);
		case BLUFFATO:
			return new RispostaController(messaggio+"mossa obbligata:\npescare una carta oggetto",null);
		case EFFETTOCONCLUSO:
			return new RispostaController(messaggio+"mosse valide:\nusare una carta (solo se umano)\nterminare il turno",null);
		case TURNOTERMINATO:
			return new RispostaController(messaggio+"non è il tuo turno",null);
		default:
			break; 
		}
		return new RispostaController("comando errato",null);
	}

	@Override
	protected boolean controlli() {
		// TODO Auto-generated method stub
		return false;
	}

}
