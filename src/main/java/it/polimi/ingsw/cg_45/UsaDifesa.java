package it.polimi.ingsw.cg_45;

public class UsaDifesa extends Azione{
	
	private CartaOggetto carta;
	
	
	public UsaDifesa(Giocatore g, StatoDiGioco p){
		super(g,p);
	}
	
	public void esegui(){
		carta=giocatore.getCarta(TipoCartaOggetto.DIFESA);
		giocatore.getCarte().remove(carta);
		model.getMazzoOggetti().mazzoScarti.add(carta);
	}

	
	protected boolean controlli() {
		return true;
	}
	
}
