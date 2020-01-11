package it.polito.tdp.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento> {
	
	public enum TipoEvento {
		OtherCrimes,
		GeneralCrimes
		
	}

	
	private LocalTime ora;
	private TipoEvento tipo;
	@Override
	public int compareTo(Evento altro) {
		
		return this.ora.compareTo(altro.getOra());
	}
	
}
