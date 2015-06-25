package it.polimi.ingsw.cg_45.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**Represents a generic game map, with all its sectors and relationships between them.
 * 
 * @author Lorenzo Raimondi
 *
 */
public abstract class Mappa {
	
	/**
	 * Number of rows in every map. 
	 */
	protected static final int ROW=14;
	
	/**
	 * Number of columns in every map.
	 */
	protected static final int COL=23;
	
	/**Represents the proper map, linking every sector to its coordinates. So it's possible to get a sector of the
	 * map simply passing its coordinates.
	 * 
	 */
	protected Map<Coordinate, Settore> settori = new HashMap<Coordinate, Settore>();
		
	/**Create a new game map, generating all its sectors and the links between them.
	 * <p>
	 * This constructor reads a text file where are specified coordinates and type of sector and build
	 * the game map relative to the file by putting all the sectors in {@link mappa}. 
	 * 
	 * @param percorso the file path of the text file used to build the map.
	 */
	public Mappa(String percorso){
	    
		int[][] m=importaFile(percorso,ROW,COL);		
		    	
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				int y=i-((j-(j%2))/2);
				int z=-j-y;
				
				switch(m[i][j]){
				case 0:
					settori.put(new Coordinate(j,y,z), new SettoreVuoto(j,y,z));
					break;
				case 1:
					settori.put(new Coordinate(j,y,z), new SettoreSicuro(j,y,z));
					break;
				case 2:
					settori.put(new Coordinate(j,y,z), new SettorePericoloso(j,y,z));
					break;
				case 3:
					settori.put(new Coordinate(j,y,z), new SettoreScialuppa(j,y,z));
					break;
				case 4:
					settori.put(new Coordinate(j,y,z), new SettorePartenzaAlieni(j,y,z));
					break;
				case 5:
					settori.put(new Coordinate(j,y,z), new SettorePartenzaUmani(j,y,z));
					break;
				case 9:
					break;
				default: 
					break;
				}
				
				
			}	
					
		}
			
		for(Settore s : settori.values()){
			calcolaAdiacenze(s);
		}
	}
	
	protected int[][] importaFile(String percorso,int row,int col){
		int righe=row;
	    int colonne=col;
	    int[][] m= new int[righe][colonne];
	    int p=0;
		Scanner s;
	    String[] r=new String[righe];
		
		
		try {
			s = new Scanner(new File(percorso));
		} catch (FileNotFoundException e) {
			s=null;
		}
	
	   	    
	    while(s.hasNextLine()){
	    	r[p]=s.nextLine();
	    	p++;
	    }
	    s.close();
	    
	    	for(int k=0;k<righe;k++){
	    		for(int j=0;j<colonne;j++){
	    	            m[k][j]=(r[k].charAt(j)-48);
	    	    }
	    	}
	  
	    return m;
	    
	}
	
	/**Calculates all the sectors adjacent to the given one and sets all its six neighbors in its attributes.    
	 * 
	 * @param settore the sector of which will be calculated the neighbors.
	 */
	public void calcolaAdiacenze(Settore settore){
		int x=settore.getCoordinate().getX();
		int y=settore.getCoordinate().getY();
		int z=settore.getCoordinate().getZ();
		
		Settore[] a=new Settore[6];
		
		a[0]=settori.get(new Coordinate(x+1,y-1,z));
		a[1]=settori.get(new Coordinate(x-1,y+1,z));
		a[2]=settori.get(new Coordinate(x,y+1,z-1)); 
		a[3]=settori.get(new Coordinate(x,y-1,z+1));
		a[4]=settori.get(new Coordinate(x-1,y,z+1));
		a[5]=settori.get(new Coordinate(x+1,y,z-1));
		
		settore.setVicini(a[0], a[1], a[2], a[3], a[4], a[5]);
	}
	
	/**Calculate if a move from a sector to another can be performed given a maximum number of steps,
	 * following the games rules:
	 * <ul>
	 * <li> it's forbidden to move to an Alien/Human sector or transit over them;
	 * <li> it's forbidden to move to an Escape Hatch sector already discovered;
	 * <li> it's forbidden for an Alien to move into an Escape Hatch sector;
	 * <li> it's forbidden to move to the current sector, so it's forbidden not to move during a move;
	 * <li> it's forbidden to transit over Empty sectors.
	 * 	
	 * @param partenza the sector in which the move starts.
	 * @param arrivo the sector in which the player wishes to move.
	 * @param portata the number of steps a player can cover.
	 * @return {@code true} if the player is capable to perform the move, {@code false} otherwise.
	 */
	public boolean mossaValida(Settore partenza, Settore arrivo, int portata){
		
		if(arrivo instanceof SettorePartenzaAlieni || arrivo instanceof SettorePartenzaUmani || partenza.equals(arrivo)){
			return false;
		}
		
		if(arrivo instanceof SettoreScialuppa){
			SettoreScialuppa scialuppa=(SettoreScialuppa)arrivo;
			if(scialuppa.isScoperta())
				return false;			
		}
				
		final Map<Integer,ArrayList<Settore>> m = new HashMap<Integer,ArrayList<Settore>>();
		m.put(0,new ArrayList<Settore>());
		m.get(0).add(partenza);
		
		for(int i=1;i<=portata;i++){
			m.put(i, new ArrayList<Settore>());
			for(Settore s : m.get(i-1)){
				for(int j=0;j<6;j++){
					//if(s.getVicini()[j]==null || s.getVicini()[j] instanceof SettorePartenzaAlieni || s.getVicini()[j] instanceof SettorePartenzaUmani){
					//} else 
						//if(!(s.getVicini()[j] instanceof SettoreVuoto)){
						if(!(s.getVicini()[j] instanceof SettoreVuoto) && s.getVicini()[j]!=null){
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

	/**
	 * 
	 * @return the data structure which contains sectors and links between them. 
	 */
	public Map<Coordinate, Settore> getMappa() {
		return settori;
	}
	
	
}
