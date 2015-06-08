package it.polimi.ingsw.cg_45;
import java.util.*;

public abstract class Mazzo {
		protected int numCarte;
		List<Carta> mazzoIniziale=new ArrayList<Carta>();
		List<Carta> mazzoScarti=new ArrayList<Carta>();
		

		public Mazzo(){
			
		}
		
		public int getNumCarte() {
			return numCarte;
		}
		
		public boolean isEmpty(){
			if(numCarte==0)
				return true;
			return false;
		}

		public List<Carta> getMazzoIniziale() {
			return mazzoIniziale;
		}

		public List<Carta> getMazzoScarti() {
			return mazzoScarti;
		}
		
		public Carta pescaCarta(){
			if(mazzoIniziale.isEmpty()){
				if(!mazzoScarti.isEmpty()){
					this.getMazzoIniziale().addAll(this.getMazzoScarti());
					Collections.shuffle(this.getMazzoIniziale());
					this.getMazzoScarti().removeAll(this.getMazzoScarti());
				} else {
					return null;
				}
			}
			return this.getMazzoIniziale().remove(0);
		}
}
