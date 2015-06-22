package it.polimi.ingsw.cg_45.netCommons;

/**Represents a generic marker that store a client's subscription to a game in
 * a specific map by getting his {@code id} and {@code nome}.
 * 
 * @author Lorenzo Raimondi
 *
 */
public abstract class Accettazione {
	
	protected Integer id;
	protected String nomeGiocatore;

	/**
	 * 
	 * @return player's nickname.
	 */
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}
	
	/**
	 * 
	 * @return player's id.
	 */
	public Integer getId(){
		return id;
	}
	
}
