package it.polito.tdp.bar.model;

public class Model {

	private Simulator simulator;
	
	public void runSimulator() {
		this.simulator = new Simulator();
	}
	
	public Statistiche getStatistiche() {
		this.simulator.run();
		return this.simulator.getStatistiche();
	}
}
