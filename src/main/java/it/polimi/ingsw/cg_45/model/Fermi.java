package it.polimi.ingsw.cg_45.model;

import java.io.File;

/**Represents <i>Fermi</i> game map, with all its sectors and relationships between them.
 * 
 * @author Lorenzo Raimondi
 *
 */
public class Fermi extends Mappa{
	
	/**Create a new <i>Fermi</i> map, generating all its sectors and the links between them.
	 * <p>
	 * This constructor reads a text file where are specified coordinates and type of sector and build
	 * the game map relative to the <i>Fermi</i> file by putting all the sectors in {@link mappa}. 
	 * 
	 * @param percorso the file path of the text file used to build the <i>Fermi</i> map.
	 */
	public Fermi(){
		
		super("rsc"+File.separatorChar+"fermi.txt");
	}
}
