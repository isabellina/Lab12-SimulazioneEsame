package it.polito.tdp.model;

import java.time.LocalTime;

import it.polito.tdp.db.District;

public class Evento implements Comparable<Evento> {
	
	public enum TipoEvento {
		OtherCrimes,
		GeneralCrimes,
		AgenteLibero
		
	}

	
	private LocalTime ora;
	private TipoEvento tipo;
	private District distretto;
	private int ritardoAccumulato;
	
	
	
	
	
	
	public Evento(LocalTime ora, TipoEvento tipo, District distretto, int ritardoAccumulato) {
		super();
		this.ora = ora;
		this.tipo = tipo;
		this.distretto = distretto;
		this.ritardoAccumulato = ritardoAccumulato;
	}
	
	public int getRitardoAccumulato() {
		return this.ritardoAccumulato;
	}



	public LocalTime getOra() {
		return ora;
	}



	public void setOra(LocalTime ora) {
		this.ora = ora;
	}



	public TipoEvento getTipo() {
		return tipo;
	}



	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}



	public District getDistretto() {
		return distretto;
	}



	public void setDistretto(District distretto) {
		this.distretto = distretto;
	}



	@Override
	public int compareTo(Evento altro) {
		
		return this.ora.compareTo(altro.getOra());
	}
	
}
