package it.polimi.ingsw.cg_45;

import java.io.File;

//import java.io.File;
//import java.util.Scanner;

public class Fermi extends Mappa{

	/*private int[][] importaFile(String percorso){
		int righe=12;
	    int colonne=9;
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
	    
	}*/
	
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
	
	public Fermi(){
		
		super("rsc"+File.separatorChar+"fermi.txt");
		/*int m[][]=importaFile("fermi.txt",12,9);		
		    	
		for(int i=0;i<12;i++){
			for(int j=0;j<9;j++){
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
		}*/
	}

	/*public Fermi(String percorso, int row, int col) {
		super(percorso, row, col);
		// TODO Auto-generated constructor stub
	}
	
	/*
	public Fermi(){
		mappa.put(new Coordinate(0,0,0), new SettoreSicuro(0,0,0));		
		mappa.put(new Coordinate(0,1,-1), new SettoreSicuro(0,1,-1));
		mappa.put(new Coordinate(0,2,-2), new SettoreSicuro(0,2,-2));
		mappa.put(new Coordinate(0,3,-3), new SettorePericoloso(0,3,-3) );
		mappa.put(new Coordinate(0,4,-4), new SettorePericoloso(0,4,-4) );
		mappa.put(new Coordinate(0,-1,1), new SettoreSicuro(0,-1,1) );
		mappa.put(new Coordinate(0,-2,2), new SettorePartenzaAlieni(0,-2,2));
		mappa.put(new Coordinate(0,-3,3), new SettorePartenzaUmani(0,-3,3));
		mappa.put(new Coordinate(0,-4,4), new SettoreSicuro(0,-4,4));
		mappa.put(new Coordinate(0,-5,5), new SettoreSicuro(0,-5,5));
		
		mappa.put(new Coordinate(-1,6,-5), new SettoreSicuro(-1,6,-5));
		mappa.put(new Coordinate(-1,5,-4), new SettoreSicuro(-1,5,-4));
		mappa.put(new Coordinate(-1,4,-3), new SettorePericoloso(-1,4,-3));
		mappa.put(new Coordinate(-1,3,-2), new SettoreVuoto(-1,3,-2));
		mappa.put(new Coordinate(-1,2,-1), new SettoreVuoto(-1,2,-1));
		mappa.put(new Coordinate(-1,1,0), new SettoreVuoto(-1,1,0));
		mappa.put(new Coordinate(-1,0,1), new SettoreSicuro(-1,0,1));
		mappa.put(new Coordinate(-1,-1,2), new SettoreVuoto(-1,-1,2));
		mappa.put(new Coordinate(-1,-2,3), new SettoreVuoto(-1,-2,3));
		mappa.put(new Coordinate(-1,-3,4), new SettoreSicuro(-1,-3,4));
		mappa.put(new Coordinate(-1,-4,5), new SettoreVuoto(-1,-4,5));
		mappa.put(new Coordinate(-1,-5,6), new SettorePericoloso(-1,-5,6));
				
		mappa.put(new Coordinate(1,5,-6), new SettoreSicuro(1,5,-6));
		mappa.put(new Coordinate(1,4,-5), new SettoreSicuro(1,4,-5));
		mappa.put(new Coordinate(1,3,-4), new SettorePericoloso(1,3,-4));
		mappa.put(new Coordinate(1,2,-3), new SettoreVuoto(1,2,-3));
		mappa.put(new Coordinate(1,1,-2), new SettoreVuoto(1,1,-2));
		mappa.put(new Coordinate(1,0,-1), new SettoreVuoto(1,0,-1));
		mappa.put(new Coordinate(1,-1,0), new SettoreSicuro(1,-1,0));
		mappa.put(new Coordinate(1,-2,1), new SettoreVuoto(1,-2,1));
		mappa.put(new Coordinate(1,-3,2), new SettoreVuoto(1,-3,2));
		mappa.put(new Coordinate(1,-4,3), new SettoreSicuro(1,-4,3));
		mappa.put(new Coordinate(1,-5,4), new SettoreVuoto(1,-5,4));
		mappa.put(new Coordinate(1,-6,5), new SettorePericoloso(1,-6,5));
		
		mappa.put(new Coordinate(-2,7,-5), new SettoreScialuppa(-2,7,-5));
		mappa.put(new Coordinate(-2,5,-3), new SettorePericoloso(-2,5,-3));
		mappa.put(new Coordinate(-2,4,-2), new SettoreSicuro(-2,4,-2));
		mappa.put(new Coordinate(-2,3,-1), new SettoreScialuppa(-2,3,-1));
		mappa.put(new Coordinate(-2,2,0), new SettoreVuoto(-2,2,0));
		mappa.put(new Coordinate(-2,1,1), new SettoreSicuro(-2,1,1));
		mappa.put(new Coordinate(-2,0,2), new SettoreSicuro(-2,0,2) );
		mappa.put(new Coordinate(-2,-1,3), new SettoreVuoto(-2,-1,3));
		mappa.put(new Coordinate(-2,-2,4), new SettoreSicuro(-2,-2,4));
		mappa.put(new Coordinate(-2,-3,5), new SettoreSicuro(-2,-3,5));
		mappa.put(new Coordinate(-2,-4,6), new SettoreSicuro(-2,-4,6));
		
		
		
		mappa.put(new Coordinate(2,5,-7), new SettoreScialuppa(2,5,-7));
		mappa.put(new Coordinate(2,3,-5), new SettorePericoloso(2,3,-5));
		mappa.put(new Coordinate(2,2,-4), new SettoreSicuro(2,2,-4));
		mappa.put(new Coordinate(2,1,-3), new SettoreScialuppa(2,1,-3));
		mappa.put(new Coordinate(2,0,-2), new SettoreVuoto(2,0,-2));
		mappa.put(new Coordinate(2,-1,-1), new SettorePericoloso(2,-1,-1));
		mappa.put(new Coordinate(2,-2,0), new SettoreSicuro(2,-2,0) );
		mappa.put(new Coordinate(2,-3,1), new SettoreVuoto(2,-3,1));
		mappa.put(new Coordinate(2,-4,2), new SettoreSicuro(2,-4,2));
		mappa.put(new Coordinate(2,-5,3), new SettoreSicuro(2,-5,3));
		mappa.put(new Coordinate(2,-6,4), new SettorePericoloso(2,-6,4));
		
		mappa.put(new Coordinate(-3,3,0), new SettoreSicuro(-3,3,0));
		mappa.put(new Coordinate(-3,2,1), new SettorePericoloso(-3,2,1));
		mappa.put(new Coordinate(-3,0,3), new SettorePericoloso(-3,0,3));
		mappa.put(new Coordinate(-3,-1,4), new SettoreSicuro(-3,-1,4));
		mappa.put(new Coordinate(-3,-3,6), new SettorePericoloso(-3,-3,6));
		mappa.put(new Coordinate(-3,-2,5), new SettoreVuoto(-3,-2,5));
		
		mappa.put(new Coordinate(3,0,-3), new SettoreSicuro(3,0,-3));
		mappa.put(new Coordinate(3,-1,-2), new SettoreSicuro(3,-1,-2));
		mappa.put(new Coordinate(3,-3,0), new SettoreSicuro(3,-3,0));
		mappa.put(new Coordinate(3,-4,1), new SettorePericoloso(3,-4,1));
		mappa.put(new Coordinate(3,-5,2), new SettoreVuoto(3,-5,2));
		mappa.put(new Coordinate(3,-6,3), new SettoreSicuro(3,-6,3));
		
		mappa.put(new Coordinate(-4,-1,5), new SettoreSicuro(-4,-1,5));
		mappa.put(new Coordinate(-4,-2,6), new SettoreSicuro(-4,-2,6));
		
		mappa.put(new Coordinate(4,-5,1), new SettoreSicuro(4,-5,1));
		mappa.put(new Coordinate(4,-6,2), new SettorePericoloso(4,-6,2));
		
		for(Settore s : mappa.values()){
			calcolaAdiacenze(s);
		}
		
	}
	*/
	
}
