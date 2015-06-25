package it.polimi.ingsw.cg_45.model;

import java.io.File;

/**Represents <i>Galilei</i> game map, with all its sectors and relationships between them.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Galilei extends Mappa {
	
	/**Create a new <i>Galilei</i> map, generating all its sectors and the links between them.
	 * <p>
	 * This constructor reads a text file where are specified coordinates and type of sector and build
	 * the game map relative to the <i>Galilei</i> file by putting all the sectors in {@link mappa}. 
	 * 
	 * @param percorso the file path of the text file used to build the <i>Galilei</i> map.
	 */
	public Galilei(){
		super("rsc"+File.separatorChar+"galilei.txt");
	}
}
