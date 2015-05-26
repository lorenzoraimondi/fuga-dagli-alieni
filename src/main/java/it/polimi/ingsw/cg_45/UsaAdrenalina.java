package it.polimi.ingsw.cg_45;

public class UsaAdrenalina extends Azione {

	private CartaOggetto carta;
	private MazzoOggetti mazzo;
	
	public UsaAdrenalina(Giocatore g, StatoDiGioco p){
		super(g,p);
		this.mazzo=(MazzoOggetti) p.getMazzoOggetti();
	}
	
	public void esegui(){
		System.out.println("1");
		if(controlli()){
			System.out.println("2");
			giocatore.setPortata(2);
			carta=giocatore.getCarta(TipoCartaOggetto.ADRENALINA);
			giocatore.getCarte().remove(carta);
			mazzo.getMazzoScarti().add(carta);
		}
		
	}
	
	protected boolean controlli(){
		if(giocatore.getCarte().contains(new CartaOggetto(TipoCartaOggetto.ADRENALINA))){
				return true;
			} 
		return false;
	}
}
