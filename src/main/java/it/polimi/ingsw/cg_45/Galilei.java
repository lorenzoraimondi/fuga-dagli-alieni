package it.polimi.ingsw.cg_45;

//import java.io.File;
//import java.util.Scanner;

public class Galilei extends Mappa {
	
	public Galilei(){
		super("galilei.txt");
	}
	/*
	private int[][] importaFile(String percorso){
		int righe=14;
	    int colonne=23;
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
	
	/*
	 * 0 SettoreVuoto
	 * 1 SettoreSicuro
	 * 2 SettorePericoloso
	 * 3 SettoreScialuppa
	 * 4 SettorePartenzaAlieni
	 * 5 SettorePartenzaUmani
	 * 9 Il metodo non istanzia alcun settore, usato nei settori esterni
	 *   che "riempiono lo spazio" tra i settori istanziati e i bordi della 
	 *   matrice (vedi "fermi.txt" per esempio)
	 */
	/*public Galilei(){
			    
		int m[][]=importaFile("galilei.txt");		
		    	
		for(int i=0;i<14;i++){
			for(int j=0;j<23;j++){
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
	}*/
}
