package it.polimi.ingsw.cg_45;

/**Represents an Empty map's sector. An Empty Sector is a sector in which no one can arrive or transit upon.
 * 
 * @author Lorenzo
 *
 */

public class SettoreVuoto extends Settore {

	/**Create a new Empty Sector by its three coordinate's values
	 * 
	 * @param x the x coordinate variable
	 * @param y the y coordinate variable
	 * @param z the z coordinate variable
	 */
	public SettoreVuoto(int x, int y, int z) {
		super(x,y,z);
	}

}
