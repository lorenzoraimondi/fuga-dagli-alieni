package it.polimi.ingsw.cg_45;

import java.io.File;

/**Represents <i>Galvani</i> game map, with all its sectors and relationships between them.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Galvani extends Mappa{

	/**Create a new <i>Galvani</i> map, generating all its sectors and the links between them.
	 * <p>
	 * This constructor reads a text file where are specified coordinates and type of sector and build
	 * the game map relative to the <i>Galvani</i> file by putting all the sectors in {@link mappa}. 
	 * 
	 * @param percorso the file path of the text file used to build the <i>Galvani</i> map.
	 */
	public Galvani(){
		super("rsc"+File.separatorChar+"galvani.txt");
	}

}
