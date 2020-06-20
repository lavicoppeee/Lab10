package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;


public class Simulator {

	    //coda degli eventi
	    private PriorityQueue<Event> queue;
	
	    //PARAMETRI SIMULAZIONE
		private Integer percentualeOccupazione = 50;
		private int NUM_EVENTI = 2000;
		private int T_MIN_ARRIVO_MAX = 10;
		private int NUM_PERSONE_MAX = 10;
		private int DURATA_MIN = 60;
		private int DURATA_MAX = 120;
		private double TOLLERANZA_MAX = 0.9;
		
		//MODELLO MONDO
		private List<Tavolo> tavoli;
		
		//VALORI DA CALCOLARE
		private Statistiche statistiche;
		
		public void Simulator() {
			this.tavoli = new ArrayList<>();
			
			this.tavoli.add(new Tavolo(4));
			this.tavoli.add(new Tavolo(4));
			this.tavoli.add(new Tavolo(4));
			this.tavoli.add(new Tavolo(4));
			this.tavoli.add(new Tavolo(4));
			this.tavoli.add(new Tavolo(6));
			this.tavoli.add(new Tavolo(6));
			this.tavoli.add(new Tavolo(6));
			this.tavoli.add(new Tavolo(6));
			this.tavoli.add(new Tavolo(8));
			this.tavoli.add(new Tavolo(8));
			this.tavoli.add(new Tavolo(8));
			this.tavoli.add(new Tavolo(8));
			this.tavoli.add(new Tavolo(10));
			this.tavoli.add(new Tavolo(10));
		}
		
	
	public void run() {
		
		queue= new PriorityQueue<Event>();
		this.queue.clear();
		this.statistiche=new Statistiche(); 
		
		LocalDateTime oraArrivoClienti=LocalDateTime.now();
	
			for(int i = 0; i < 2000; i++) {
				Random random = new Random();
				
				Integer tIntervallo = random.nextInt(10) + 1; //durata intervallo da 1 a 10 minuti 
				Integer nPersone= random.nextInt(10) + 1; //massimo numero di persone
				Integer tPermanenza = random.nextInt((120 - 60) + 1) + 1; //permanenza ai tavoli in un massimo di 120 minuti minimo 60 minuti 
				float tolleranza = (float) (random.nextInt(10)/10.0);  //tolleranza nell'attesa 
				
				Event e = new Event(EventType.ARRIVO_GRUPPO_CLIENTI, oraArrivoClienti, nPersone, Duration.of(tPermanenza, ChronoUnit.MINUTES), tolleranza);
				oraArrivoClienti = oraArrivoClienti.plus(tIntervallo, ChronoUnit.MINUTES);
				this.queue.add(e);
			}
			
		while(!this.queue.isEmpty()) {
			
			Simulator();
			
			Event e = this.queue.poll();
			
			this.processEvent(e);
		}
	}
	
	
	private void processEvent(Event e) {
		// TODO Auto-generated method stub
		
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			
			Integer nPersone = e.getNumPersone();
			Tavolo tavolo = null;
			Collections.sort(tavoli);
			for(Tavolo t : this.tavoli) {
				if(t.getPosti() >= nPersone && nPersone >= t.getPosti()*(this.percentualeOccupazione/100.0)) {
					tavolo = t;
					this.tavoli.remove(t);
					break;
				}
			}
			
			if(tavolo == null) {
				Random random = new Random();
				float ran = (float) (random.nextInt(10)/10.0);
				if(ran <= e.getTolleranza()) {
					this.statistiche.plusSoddisfatti(nPersone);
					this.createEvent(e, tavolo);
				} else {
					this.statistiche.plusInsoddisfatti(nPersone);
				}
			} else {
				this.statistiche.plusSoddisfatti(nPersone);
				this.createEvent(e, tavolo);
			}
			break;
			
		case TAVOLO_LIBERATO:
			
			Tavolo tavoloLibero = e.getTavolo();
			if(tavoloLibero != null) {
				this.tavoli.add(tavoloLibero);
			}
			
			break;
			
		}
	}
	
	
	private void createEvent(Event e, Tavolo t) {
		LocalDateTime time = e.getTime().plus(e.getDurata());
		Event newEvent = new Event(EventType.TAVOLO_LIBERATO, time, t);
		this.queue.add(newEvent);
	}
	
	
	public Statistiche getStatistiche() {
		return this.statistiche;
	}


	public void setPercentualeOccupazione(Integer percentualeOccupazione) {
		this.percentualeOccupazione = percentualeOccupazione;
	}
	
}
