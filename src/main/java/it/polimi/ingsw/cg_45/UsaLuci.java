package it.polimi.ingsw.cg_45;

public class UsaLuci extends Azione {
	
	private Settore settore;
	private Settore[] vicini;
	
	public UsaLuci(StatoDiGioco p,Giocatore g,Settore s){
		super(g,p);
		this.settore=s;		
	}
	
	public void esegui(){
		if(controlli()){
			vicini=model.getMappa().mappa.get(settore.getCoordinate()).getVicini();
			for(Giocatore g :  model.getGiocatori()){
				for(Settore s : vicini){
					if(g.getPosizione()==s && g!=giocatore){
						
					}
						
				}
			}
			
		}
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.LUCI))){
			return true;
		} 
		return false;
	}

}
