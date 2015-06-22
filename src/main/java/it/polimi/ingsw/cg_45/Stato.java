package it.polimi.ingsw.cg_45;

/**Represents the different status that a player can assume during his turn.
 * 
 * @author Andrea Turconi
 *
 */
public enum Stato {
	/**
	 * @param if the player is at the beginning of his turn and hasn't performet any action.
	 */
	INIZIO,
	/**
	 * @param if the player has moved into a <i>Secure</i> Sector.
	 */
	SICURO,
	/**
	 * @param if the player has moved into a <i>Dangerous</i> Sector.
	 */
	PERICOLO,
	/**
	 * @param if the player has moved into an <i>Escape Hatch</i> Sector.
	 */
	SCIALUPPA,
	/**
	 * @param if the player has drawn a <i>Silence</i> Card.
	 */
	SILENZIO,
	/**
	 * @param if the player has drawn a <i>Noise in any Sector</i> Card with the Item Icon.
	 */
	CARTABLUFFOGGETTO,
	/**
	 * @param if the player has drawn a <i>Noise in any sector</i> Card.
	 */
	CARTABLUFF,
	/**
	 * @param if the player has drawn a <i>Noise in your sector</i> Card with the Item Icon.
	 */
	CARTARIVELAOGGETTO,
	/**
	 * @param if the player has drawn a <i>Noise in your sector</i> Card.
	 */
	CARTARIVELA,
	/**
	 * @param if the player has attacked.
	 */
	ATTACCATO,
	/**
	 * @param if the player has drawn a <i>Escape Hatch</i> Card.
	 */
	CARTASCIALUPPA,
	/**
	 * @param if the player has announced a fake position.
	 */
	BLUFFATO,
	/**
	 * @param if the player has announced his real position.
	 */
	RIVELATO,
	/**
	 * @param if the player has concluded the sector card's effect, so if he has announced a real/fake position or silence.  
	 */
	EFFETTOCONCLUSO,
	/**
	 * @param if the player has finished his turn.
	 */
	TURNOTERMINATO;
}
