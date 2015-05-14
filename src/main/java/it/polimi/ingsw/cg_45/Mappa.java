package it.polimi.ingsw.cg_45;
import java.util.*;
public abstract class Mappa {
	
	protected Map<Coordinate, Settore> mappa = new HashMap<Coordinate, Settore>();
		
	public void calcolaAdiacenze(Settore settore){
		int x=settore.getCoordinate().getX();
		int y=settore.getCoordinate().getY();
		int z=settore.getCoordinate().getZ();
		
		Settore[] a=new Settore[6];
		
		a[0]=mappa.get(new Coordinate(x+1,y-1,z));
		a[1]=mappa.get(new Coordinate(x-1,y+1,z));
		a[2]=mappa.get(new Coordinate(x,y+1,z-1)); 
		a[3]=mappa.get(new Coordinate(x,y-1,z+1));
		a[4]=mappa.get(new Coordinate(x-1,y,z+1));
		a[5]=mappa.get(new Coordinate(x+1,y,z-1));
		
		settore.setVicini(a[0], a[1], a[2], a[3], a[4], a[5]);
	}
	
	
	public boolean mossaValida(Settore partenza, Settore arrivo, int portata){
		if(arrivo instanceof SettorePartenzaAlieni || arrivo instanceof SettorePartenzaUmani)
			return false;
		
		final Map<Integer,ArrayList<Settore>> m = new HashMap<Integer,ArrayList<Settore>>();
		m.put(0,new ArrayList<Settore>());
		m.get(0).add(partenza);
		
		for(int i=1;i<=portata;i++){
			m.put(i, new ArrayList<Settore>());
			for(Settore s : m.get(i-1)){
				for(int j=0;j<6;j++){
					if(s.getVicini()[j]==null || s.getVicini()[j] instanceof SettorePartenzaAlieni || s.getVicini()[j] instanceof SettorePartenzaUmani){
					} else if(!(s.getVicini()[j] instanceof SettoreVuoto)){
						if(s.getVicini()[j]==arrivo){
							return true;
						} else {
							m.get(i).add(s.getVicini()[j]);
						} 
					} 
				}
			}
		}
		return false;
	}
}
