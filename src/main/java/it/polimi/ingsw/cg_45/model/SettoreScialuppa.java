package it.polimi.ingsw.cg_45.model;

/**Represents an Escape Hatch Sector.
 * 
 * @author Lorenzo
 *
 */
public class SettoreScialuppa extends Settore{

	private boolean scoperta;
	
	/**Create a new Escape Hatch Sector by its three coordinate's values and sets it undiscovered.
	 * 
	 * @param x the x coordinate variable
	 * @param y the y coordinate variable
	 * @param z the z coordinate variable
	 */
	public SettoreScialuppa(int x, int y, int z) {
		super(x,y,z);
		this.scoperta=false;
	}

	/**
	 * 
	 * @return {@value true} if a human player has already arrived in this sector, {@value false} otherwise.
	 */
	public boolean isScoperta() {
		return scoperta;
	}

	/**Set the Escape Hatch Sector as discovered. A Sector is discovered if a player has concluded his movement
	 * in it.
	 * 
	 */
	public void setScoperta() {
		this.scoperta = true;
	}
	
	

}
