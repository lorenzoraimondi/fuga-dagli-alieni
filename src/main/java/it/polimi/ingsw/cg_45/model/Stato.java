package it.polimi.ingsw.cg_45.model;

/**Represents the different status that a player can assume during his turn.
 * 
 * @author Andrea Turconi
 *
 */
public enum Stato {
	/**
	 * If the player is at the beginning of his turn and hasn't performed any action.
	 */
	INIZIO,
	/**
	 * If the player has moved into a <i>Secure</i> Sector.
	 */
	SICURO,
	/**
	 * If the player has moved into a <i>Dangerous</i> Sector.
	 */
	PERICOLO,
	/**
	 * If the player has moved into an <i>Escape Hatch</i> Sector.
	 */
	SCIALUPPA,
	/**
	 * If the player has drawn a <i>Silence</i> Card.
	 */
	SILENZIO,
	/**
	 * If the player has drawn a <i>Noise in any Sector</i> Card with the Item Icon.
	 */
	CARTABLUFFOGGETTO,
	/**
	 * If the player has drawn a <i>Noise in any sector</i> Card.
	 */
	CARTABLUFF,
	/**
	 * If the player has drawn a <i>Noise in your sector</i> Card with the Item Icon.
	 */
	CARTARIVELAOGGETTO,
	/**
	 * If the player has drawn a <i>Noise in your sector</i> Card.
	 */
	CARTARIVELA,
	/**
	 * If the player has attacked.
	 */
	ATTACCATO,
	/**
	 * If the player has drawn a <i>Escape Hatch</i> Card.
	 */
	CARTASCIALUPPA,
	/**
	 * If the player has announced a fake position.
	 */
	BLUFFATO,
	/**
	 * If the player has announced his real position.
	 */
	RIVELATO,
	/**
	 * If the player has concluded the sector card's effect, so if he has announced a real/fake position or silence.  
	 */
	EFFETTOCONCLUSO,
	/**
	 * If the player has finished his turn.
	 */
	TURNOTERMINATO;
}
