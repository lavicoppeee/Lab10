package it.polito.tdp.bar.model;

public class Model {

	private Simulator simulator;
	
	public void runSimulator() {
		this.simulator = new Simulator();
		this.simulator.run();
	}
	
	public Statistiche getStatistiche() {
		return this.simulator.getStatistiche();
	}
}
