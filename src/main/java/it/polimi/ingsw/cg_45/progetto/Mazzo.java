package it.polimi.ingsw.cg_45.progetto;
import java.util.*;

public abstract class Mazzo {
		protected int numCarte;
		ArrayList<Carta> MazzoIniziale=new ArrayList<Carta>();
		ArrayList<Carta> MazzoScarti=new ArrayList<Carta>();
		
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

		public ArrayList<Carta> getMazzoIniziale() {
			return MazzoIniziale;
		}

		public ArrayList<Carta> getMazzoScarti() {
			return MazzoScarti;
		}
}
