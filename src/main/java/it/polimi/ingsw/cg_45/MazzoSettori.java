package it.polimi.ingsw.cg_45;

import java.util.Collections;

public class MazzoSettori extends Mazzo {
		private final int numRumoreSettore=10;
		private final int numRumoreQualunque=10;
		private final int numSilenzio=5;
		private final int numSettOgg=4;
		private final int numQualOgg=4;
		
		public MazzoSettori(){
			for(int i=numRumoreSettore;i>0;i--){
				while(i>numSettOgg){
					MazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,true));
					i--;
				}
				MazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMORETUOSETTORE,false));
			}
			for(int i=numRumoreQualunque;i>0;i--){
				while(i>numQualOgg){
					MazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,true));
					i--;
				}
				MazzoIniziale.add(new CartaSettore(TipoCartaSettore.RUMOREQUALUNQUESETTORE,false));
			}
			for(int i=numSilenzio;i>0;i--){
					MazzoIniziale.add(new CartaSettore(TipoCartaSettore.SILENZIO,false));
			}
			Collections.shuffle(MazzoIniziale);
			numCarte=numRumoreSettore+numRumoreQualunque+numSilenzio;
		}

		@Override
		public String toString() {
			return "MazzoSettori [ MazzoIniziale=" + MazzoIniziale + "]\n";
		}
		
		
}
