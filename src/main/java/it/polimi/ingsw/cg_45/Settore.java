package it.polimi.ingsw.cg_45;

public abstract class Settore {
	
	private final Coordinate coordinate;
	private final Settore[] vicini= new Settore [6];
	
		
	public Settore(int x, int y, int z) {
		this.coordinate=new Coordinate(x,y,z);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Settore[] getVicini() {
		return vicini;
	}
	
	public void setVicini(Settore xu, Settore xd, Settore yu, Settore yd, Settore zu, Settore zd){
		this.vicini[0]=xu;
		this.vicini[1]=xd;
		this.vicini[2]=yu;
		this.vicini[3]=yd;
		this.vicini[4]=zu;
		this.vicini[5]=zd;
	}	
}
