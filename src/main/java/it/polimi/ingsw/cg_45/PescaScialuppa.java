package it.polimi.ingsw.cg_45;

public class PescaScialuppa extends Azione{
	
	private CartaScialuppa carta;
	
	public PescaScialuppa(Giocatore gioc,StatoDiGioco model){
		super(gioc,model);
	}
	
	public void esegui(){
		if(this.controlli()){
			carta=(CartaScialuppa) model.getMazzoScialuppe().mazzoIniziale.get(0);
			model.getMazzoScialuppe().mazzoIniziale.remove(0);
			if(carta.getTipo()==TipoCartaScialuppa.ROSSA)
				//{player.setSituazione(st.VINTO);}
			giocatore.setStato(Stato.CARTASCIALUPPA);
		}
	}

	protected boolean controlli() {
		if(giocatore.getStato()==Stato.SCIALUPPA){
			if(!model.getMazzoScialuppe().mazzoIniziale.isEmpty())
				return true;
		}
		return false;
	}
}
