package it.polimi.ingsw.cg_45;

public class UsaTeletrasporto extends Azione {
	
	//private Mappa mappa; 
	private MazzoOggetti mazzo;
	private CartaOggetto carta;
	
	public UsaTeletrasporto(Giocatore g, StatoDiGioco p){
		super(g,p);
		//this.mappa=p.getMappa();
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
		
	}
	
	public void esegui(){
		if(controlli()){
			//giocatore.setPosizione(SettorePartenzaUmani);
			carta=giocatore.getCarta(TipoCartaOggetto.TELETRASPORTO);
			giocatore.getCarte().remove(carta);
			mazzo.mazzoScarti.add(carta);
		}
		
	}
	
	protected boolean controlli(){
		if(giocatore.getSituazione()==Situazione.ATTIVONASCOSTO){
			if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.TELETRASPORTO))){
				return true;
			} 
		} return false;
	}
}
