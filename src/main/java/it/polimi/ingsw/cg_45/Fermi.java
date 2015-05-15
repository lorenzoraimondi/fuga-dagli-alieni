package it.polimi.ingsw.cg_45;

import java.io.File;
import java.util.Scanner;

public class Fermi extends Mappa{
	
	private int[][] importaFile(String percorso){
		Scanner scanner;
		Scanner scannerb;
		String a = new String();
		int i=0;
		try{
			scanner = new Scanner(new File(percorso));
			scannerb = new Scanner(new File(percorso));
		} catch (Exception FileNotFoundException){
			scanner=null;
			scannerb=null;
		}
	    
	    while (scanner.hasNextLine()) {
	    	a=scanner.nextLine();
	    	i++;
	    }
	    scanner.close();
	    int righe=i;
	    int colonne=a.length();
	    
	    int m[][]= new int[righe][colonne];
	    int p=0;
	    String r[] =new String[righe];
	    
	    while(scannerb.hasNextLine()){
	    	r[p]=scannerb.nextLine();
	    	p++;
	    }
	    scannerb.close();
	    	for(int k=0;k<righe;k++){
	    		for(int j=0;j<colonne;j++){
	    	            m[k][j]=(int)(r[k].charAt(j)-48);
	    	    }
	    	}
	    	
	    return m;
	    
	}
/*	
	public Fermi(){
		
	    
		int m[][]=importaFile("p.txt");		
		
		for(int i=0;i<3;i++){
			for(int j=0;j<10;j++){
				int y=-j/2-j+1;
				int z=-j-y;
				
				switch(m[i][j]){
					case 0:
						mappa.put(new Coordinate(j,y,z), new SettoreVuoto(j,y,z));
					case 1:
						mappa.put(new Coordinate(j,y,z), new SettoreSicuro(j,y,z));
					case 2:
						mappa.put(new Coordinate(j,y,z), new SettorePericoloso(j,y,z));
					case 3:
						mappa.put(new Coordinate(j,y,z), new SettoreScialuppa(j,y,z));
					case 4:
						mappa.put(new Coordinate(j,y,z), new SettorePartenzaAlieni(j,y,z));
					case 5:
						mappa.put(new Coordinate(j,y,z), new SettorePartenzaUmani(j,y,z));
					
				}
					
			}
			
		}
	}*/
	
	
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
	
	
}
