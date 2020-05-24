package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.event.EventType;

public class Event implements Comparable<Event> {
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO;
	}
	
	private EventType type;
	private LocalDateTime time;
	private Integer numPersone;
	private Duration durata;
	private float tolleranza;
	private Tavolo tavolo;
	
	public Event(EventType type, LocalDateTime time, Integer numPersone, Duration durata, float tolleranza) {
		super();
		this.type = type;
		this.time = time;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	
	public Event(EventType type, LocalDateTime time, Tavolo tavolo) {
		super();
		this.type = type;
		this.time = time;
		this.tavolo = tavolo;
	}
	
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Integer getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(Integer numPersone) {
		this.numPersone = numPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}
	
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}
	
}
