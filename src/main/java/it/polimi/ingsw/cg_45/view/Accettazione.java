package it.polimi.ingsw.cg_45.view;


public class Accettazione {

	private BrokerThread bt;
	private Integer id;
	
	public Accettazione(BrokerThread bt, Integer id){
		this.bt=bt;
		this.id=id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public BrokerThread getbt(){
		return bt;
	}
}
