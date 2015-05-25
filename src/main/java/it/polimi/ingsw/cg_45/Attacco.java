package it.polimi.ingsw.cg_45;

public class Attacco extends Azione{
	private Settore settore;
	
	
	public Attacco(StatoDiGioco p, Giocatore g, Settore s){
		super(g,p);
		this.settore=s;
	}
	
	public void esegui(){
		if(controlli()){
			for(Giocatore g : model.getGiocatori()){
				if(g!=giocatore && g.getPosizione()==settore){
					if(g.getCarte().contains(new CartaOggetto(TipoCartaOggetto.DIFESA))){
						new UsaDifesa(g,model).esegui();
					} else g.setSituazione(Situazione.MORTO);
				}
			}
		}
	}
	
	protected boolean controlli(){
		//In comune a umani e alieni, basta che non sia stato Inizio o Terminato?
		if(stato!=Stato.INIZIO){
			if(giocatore.getPosizione()==settore){
				return true;
			}
		} return false;
	}
	
}
