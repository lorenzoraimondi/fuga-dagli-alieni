package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoScialuppe extends Mazzo {
		private final static int NUM=6;
		private final static int NUMVERDE=3;
		
		public MazzoScialuppe(){
			numCarte=NUM;
			for(int i=NUM;i>0;i--){
				if(i>NUMVERDE){
					mazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.VERDE));
				}
				else
					mazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.ROSSA));
			}
			Collections.shuffle(mazzoIniziale);
		}

		@Override
		public String toString() {
			return "MazzoScialuppe [MazzoIniziale=" + mazzoIniziale + "]\n";
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
