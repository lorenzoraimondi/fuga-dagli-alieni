package it.polimi.ingsw.cg_45.model;

/**Represents a generic map's sector.
 * 
 * @author Lorenzo
 *
 */
public abstract class Settore {
	
	private final Coordinate coordinate;
	private final Settore[] vicini= new Settore [6];
	
	/**Create a new sector by its three coordinate's values
	 * 
	 * @param x the x coordinate variable
	 * @param y the y coordinate variable
	 * @param z the z coordinate variable
	 */
	public Settore(int x, int y, int z) {
		this.coordinate=new Coordinate(x,y,z);
	}
	
	/**
	 * 
	 * @return the sector's coordinates variable 
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * 
	 * @return the six sectors adjacent to this one.  
	 */
	public Settore[] getVicini() {
		return vicini;
	}
	
	/**Sets the six sectors adjacent to this one.
	 * 
	 * @param xu the upper sector on the {@code x} axis.
	 * @param xd the lower sector on the {@code x} axis.
	 * @param yu the upper sector on the {@code y} axis.
	 * @param yd the lower sector on the {@code y} axis.
	 * @param zu the upper sector on the {@code z} axis.
	 * @param zd the lower sector on the {@code z} axis.
	 */
	public void setVicini(Settore xu, Settore xd, Settore yu, Settore yd, Settore zu, Settore zd){
		this.vicini[0]=xu;
		this.vicini[1]=xd;
		this.vicini[2]=yu;
		this.vicini[3]=yd;
		this.vicini[4]=zu;
		this.vicini[5]=zd;
	}	
}
