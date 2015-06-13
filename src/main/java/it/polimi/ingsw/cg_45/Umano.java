package it.polimi.ingsw.cg_45;


public class Umano extends Giocatore {

	private boolean sedato;
	
	public Umano(int id, int ordine){
		super(id,ordine);
		this.setPortata(1);
		//this.setPosizioneIniziale(mappa);
		this.setSituazione(Situazione.ATTIVO);
	}
	
	public Umano(int id, int ordine,Mappa mappa, String nome) {
		super(id, ordine, mappa, nome);
		this.portata=1;
		this.setPosizioneIniziale(mappa);
		//this.setStato(Stato.INIZIO);
		//this.setSituazione(Situazione.ATTIVO);
	}

	@Override
	public void setPosizioneIniziale(Mappa mappa) {
		if(mappa instanceof Fermi)
			posizione=mappa.mappa.get(new Coordinate("L10"));
		else if(mappa instanceof Galilei)
			posizione=mappa.mappa.get(new Coordinate("L08"));
		else if(mappa instanceof Galvani)
			posizione=mappa.mappa.get(new Coordinate("L08"));
	}

	public boolean isSedato() {
		return sedato;
	}

	public void setSedato(boolean sedato) {
		this.sedato = sedato;
	}

}
