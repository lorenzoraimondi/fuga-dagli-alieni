package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoScialuppe extends Mazzo {
		private final int num=6;
		private final int numVerde=3;
		
		public MazzoScialuppe(){
			numCarte=num;
			for(int i=num;i>0;i--){
				while(i>numVerde){
					MazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.VERDE));
					i--;
				}
				MazzoIniziale.add(new CartaScialuppa(TipoCartaScialuppa.ROSSA));
			}
			Collections.shuffle(MazzoIniziale);
		}

		@Override
		public String toString() {
			return "MazzoScialuppe [MazzoIniziale=" + MazzoIniziale + "]\n";
		}
		
		
}
