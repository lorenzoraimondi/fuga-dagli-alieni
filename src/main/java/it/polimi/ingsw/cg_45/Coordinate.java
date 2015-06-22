package it.polimi.ingsw.cg_45;

/**Represents the coordinates of a map's sector
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Coordinate {
	
	private final int x;
	private final int y;
	private final int z;
	
	/**Create a coordinates variable by a 3 system variable.  
	 * 
	 * @param x the x coordinate variable
	 * @param y the y coordinate variable
	 * @param z the z coordinate variable
	 */
	public Coordinate(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
	}
	
	/**Create a coordinates variable by the relative sector's sign.
	 * 
	 * @param coord the sector's sign
	 */
	public Coordinate(String coord){
		coord=coord.toUpperCase();
		char[] nome= coord.toCharArray();
		x=nome[0]-65;
		int somma=(nome[1]-48)*10+(nome[2]-48);
	    y=somma+((-x/2)-1);
		z=-x-y;
	}
	 
	/**
	 * 
	 * @return the x value of the coordinate variable
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return the y value of the coordinate variable
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return the z value of the coordinate variable
	 */
	public int getZ() {
		return z;
	}
	
	/**Print the sector size starting from the three values coordinate variable.
	 * 
	 */
	@Override
	public String toString() {
		
		char[] coordinate = new char[3];
		
		coordinate[0]=(char)(x+65);
		int somma=y-((-x/2)-1);
		coordinate[1]=(char)((somma/10)+48);
		coordinate[2]=(char)((somma%10)+48);
		
		return new String(coordinate);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
	
}
