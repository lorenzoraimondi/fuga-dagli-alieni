package it.polimi.ingsw.cg_45;

public class Coordinate {
	private final int x;
	private final int y;
	private final int z;
	
	public Coordinate(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
	}

	public Coordinate(String coord){
		char[] nome= coord.toCharArray();
		x=nome[0]-65;
		int somma=(nome[1]-48)*10+(nome[2]-48);
	    y=somma+((-x/2)-1);
		z=-x-y;
	}
	 
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
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
