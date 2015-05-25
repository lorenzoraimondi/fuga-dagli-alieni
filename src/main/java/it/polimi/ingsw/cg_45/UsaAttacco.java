package it.polimi.ingsw.cg_45;

public class UsaAttacco extends Azione {
	
	Settore settore;
	Attacco attacco;
	Carta carta;
	
	public UsaAttacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		attacco=new Attacco(p,g,s);
		this.settore=s;
		this.model=p;
		this.giocatore=g;
	}
	
	public void esegui(){
		if(controlli()){
			attacco.esegui();
			carta=giocatore.getCarta(TipoCartaOggetto.ATTACCO);
			giocatore.getCarte().remove(carta);
			model.getMazzoOggetti().getMazzoScarti().add(carta);
		}
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ATTACCO))){
			return true;
		} 
		return false;
	}
	
}
