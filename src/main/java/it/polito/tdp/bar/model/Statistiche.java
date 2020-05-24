package it.polito.tdp.bar.model;

public class Statistiche {

	//VALORI DA CALCOLARE
	private Integer clientiTOT;
	private Integer soddisfatti;
	private Integer insoddisfatti;
	
	
	public Statistiche(Integer clientiTOT, Integer soddisfatti, Integer insoddisfatti) {
		super();
		this.clientiTOT = 0;
		this.soddisfatti = 0;
		this.insoddisfatti = 0;
	}
	
	public Integer getClientiTOT() {
		return clientiTOT;
	}
	
	public Integer getSoddisfatti() {
		return soddisfatti;
	}

	public Integer getInsoddisfatti() {
		return insoddisfatti;
	}
	
	public void plusSoddisfatti(Integer plus) {
		this.soddisfatti += plus;
		this.clientiTOT += plus;
	}
	
	public void plusInsoddisfatti(Integer plus) {
		this.insoddisfatti += plus;
		this.clientiTOT += plus;
	}
}
