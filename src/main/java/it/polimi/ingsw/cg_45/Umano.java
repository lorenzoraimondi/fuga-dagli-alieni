package it.polimi.ingsw.cg_45;

public class Umano extends Giocatore {

	private boolean sedato;
	
	public Umano(int id, int ordine){
		super(id,ordine);
	}
	
	public Umano(int id, int ordine,Mappa mappa) {
		super(id, ordine, mappa);
		this.portata=1;
		this.setPosizioneIniziale();
	}

	@Override
	protected void setPosizioneIniziale() {
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
