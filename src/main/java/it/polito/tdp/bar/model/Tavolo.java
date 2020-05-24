package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo> {

	private Integer posti;

	public Tavolo(Integer posti) {
		this.posti = posti;
	}

	public Integer getPosti() {
		return this.posti;
	}

	public void setPosti(Integer posti) {
		this.posti = posti;
	}

	@Override
	public int compareTo(Tavolo o) {
		return this.posti - o.posti;
	}
}



