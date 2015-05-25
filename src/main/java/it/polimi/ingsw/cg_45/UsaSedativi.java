package it.polimi.ingsw.cg_45;

public class UsaSedativi extends Azione{

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	public UsaSedativi(Giocatore g, StatoDiGioco p){
		super(g,p);
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
	}
	
	public void esegui(){
		if(controlli()){
			Umano g=(Umano)giocatore;
			g.setSedato(true);
			carta=giocatore.getCarta(TipoCartaOggetto.SEDATIVI);
			giocatore.getCarte().remove(carta);
			mazzo.mazzoScarti.add(carta);
		}
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.SEDATIVI))){
				return true;
		} 
		return false;
	}
	
	
}
