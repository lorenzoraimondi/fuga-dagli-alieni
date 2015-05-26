package it.polimi.ingsw.cg_45;
import java.io.File;
import java.util.*;
public abstract class Mappa {
	protected final static int ROW=14;
	protected final static int COL=23;
	protected Map<Coordinate, Settore> mappa = new HashMap<Coordinate, Settore>();
		
	public Mappa(String percorso){
	    
		int m[][]=importaFile(percorso,ROW,COL);		
		    	
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				int y=i-((j-(j%2))/2);
				int z=-j-y;
				
				switch(m[i][j]){
				case 0:
					mappa.put(new Coordinate(j,y,z), new SettoreVuoto(j,y,z));
					break;
				case 1:
					mappa.put(new Coordinate(j,y,z), new SettoreSicuro(j,y,z));
					break;
				case 2:
					mappa.put(new Coordinate(j,y,z), new SettorePericoloso(j,y,z));
					break;
				case 3:
					mappa.put(new Coordinate(j,y,z), new SettoreScialuppa(j,y,z));
					break;
				case 4:
					mappa.put(new Coordinate(j,y,z), new SettorePartenzaAlieni(j,y,z));
					break;
				case 5:
					mappa.put(new Coordinate(j,y,z), new SettorePartenzaUmani(j,y,z));
					break;
				case 9:
					break;
				}
				
				
			}	
					
		}
			
		for(Settore s : mappa.values()){
			calcolaAdiacenze(s);
		}
	}
	
	protected int[][] importaFile(String percorso,int row,int col){
		int righe=row;
	    int colonne=col;
	    int m[][]= new int[righe][colonne];
	    int p=0;
		Scanner s;
	    String r[]=new String[righe];
		
		try{
			s = new Scanner(new File(percorso));
		} catch (Exception FileNotFoundException){
		
			s=null;
		}
	   	    
	    while(s.hasNextLine()){
	    	r[p]=s.nextLine();
	    	p++;
	    }
	    s.close();
	    
	    	for(int k=0;k<righe;k++){
	    		for(int j=0;j<colonne;j++){
	    	            m[k][j]=(int)(r[k].charAt(j)-48);
	    	    }
	    	}
	  
	    return m;
	    
	}
	
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
		if(arrivo instanceof SettorePartenzaAlieni || arrivo instanceof SettorePartenzaUmani || partenza.equals(arrivo))
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

	public Map<Coordinate, Settore> getMappa() {
		return mappa;
	}
	
	
}
