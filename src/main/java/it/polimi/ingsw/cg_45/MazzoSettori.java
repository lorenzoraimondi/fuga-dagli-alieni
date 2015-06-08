package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoSettori extends Mazzo {
		private final static int NUMRUMORESETTORE=10;
		private final static int NUMRUMOREQUALUNQUE=10;
		private final static int NUMSILENZIO=5;
		private final static int NUMSETTOGG=4;
		private final static int NUMQUALOGG=4;
		
		public MazzoSettori(){
			numCarte=0;
			for(int i=NUMRUMORESETTORE;i>0;i--,numCarte++){
				if(i<NUMSETTOGG){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,true));
				}
				else
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,false));
			}
			for(int i=NUMRUMOREQUALUNQUE;i>0;i--,numCarte++){
				if(i<NUMQUALOGG){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,true));
				}
				else
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,false));
			}
			for(int i=NUMSILENZIO;i>0;i--,numCarte++){
					mazzoIniziale.add(new CartaSettore(TipoCartaSettore.SILENZIO,false));
			}
			Collections.shuffle(mazzoIniziale);
		}

		@Override
		public String toString() {
			return "MazzoSettori [ MazzoIniziale=" + mazzoIniziale + "]\n";
		}

		@Override
		public Carta pescaCarta() {
			if(mazzoIniziale.isEmpty()){
				if(!mazzoScarti.isEmpty()){
					this.getMazzoIniziale().addAll(this.getMazzoScarti());
					Collections.shuffle(this.getMazzoIniziale());
					this.getMazzoScarti().removeAll(this.getMazzoScarti());
				}
			}
			Carta pescata;
			pescata=this.mazzoIniziale.remove(0);
			this.mazzoScarti.add(pescata);
			return pescata;		
		}
		
		
		
}
