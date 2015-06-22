package it.polimi.ingsw.cg_45.view;


public class Accettazione {

	private BrokerThread bt;
	private Integer id;
	private String nomeGiocatore;
	
	public Accettazione(BrokerThread bt, Integer id, String nomeGiocatore){
		this.bt=bt;
		this.id=id;
		this.nomeGiocatore=nomeGiocatore;
	}
	
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	
	public Integer getId(){
		return id;
	}
	
	public BrokerThread getbt(){
		return bt;
	}
}
