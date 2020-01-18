package it.polito.tdp.model;

import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import it.polito.tdp.db.District;
import it.polito.tdp.model.Evento.TipoEvento;

public class Simulatore {
	
	//coda degli eventi
	private PriorityQueue<Evento> queue = new PriorityQueue();
	
	private Graph<District, MyEdge> grafo;
	
	private Map<Integer, Integer> mappaDistretti = new TreeMap<Integer, Integer>();
	 private Model model;
	
	
	
	public Simulatore(Graph grafo, Model model) {
		this.grafo = grafo;
		this.model = model;
	}
	
	
	//statistiche da calcorare
	int eventoMalGestito;
	
	
	
	
	
	public void init(int agenti, int d , Month m, Year y) {
		eventoMalGestito =0;
		for(Crime c: this.model.getEventi(y, m, d)) {
		//	queue.add(new Evento())
		}
		
	}
	
	
	public void run() {
		while(!queue.isEmpty()) {
			Evento e = (Evento) queue.poll();
			District posIniziale = this.distrettoVicino(e.getDistretto());
			switch(e.getTipo()) {
			
			case GeneralCrimes:
				if(posIniziale!=null) {
					int a = mappaDistretti.get(posIniziale.getDistrictId());
					a  = a-1;
					this.mappaDistretti.put(posIniziale.getDistrictId(), a);
					long tempoArrivo = this.tempoArrivo(posIniziale, e.getDistretto());
					if((tempoArrivo + e.getRitardoAccumulato()) >= 15) {
						eventoMalGestito++;
					}
					int durata = this.getDurataEvento();
					long tot = tempoArrivo+durata;
					queue.add(new Evento(e.getOra().plusMinutes(tot), TipoEvento.AgenteLibero, e.getDistretto(), 0));
				}
				else {
					queue.add(new Evento(e.getOra().plusMinutes(1), TipoEvento.GeneralCrimes, e.getDistretto(), 1));
				}
				break;
				
			case OtherCrimes:
				if(posIniziale!=null) {
					int a = mappaDistretti.get(posIniziale.getDistrictId());
					a  = a-1;
					this.mappaDistretti.put(posIniziale.getDistrictId(), a);
					long tempoArrivo = this.tempoArrivo(posIniziale, e.getDistretto());
					if((tempoArrivo + e.getRitardoAccumulato()) >= 15) {
						eventoMalGestito++;
					}
					int durata = 120;
					long tot = tempoArrivo+durata;
					queue.add(new Evento(e.getOra().plusMinutes(tot), TipoEvento.AgenteLibero, e.getDistretto(), 0));
				}
				else {
					queue.add(new Evento(e.getOra().plusMinutes(1), TipoEvento.GeneralCrimes, e.getDistretto(), 1));
				}
				break;
				
			case AgenteLibero:
				int a = mappaDistretti.get(posIniziale.getDistrictId());
				a  = a+1;
				mappaDistretti.put(posIniziale.getDistrictId(), a);
				break;
			}
			
		}
	}
	
	
	public long tempoArrivo(District a, District b) {
		MyEdge e =  this.grafo.getEdge(a, b);
		double distanza = e.getWeight();
		double tempo = 60/distanza;
		return Math.round(tempo  * 60);
				
	}
	
	public int getDurataEvento() {
		Random r = new Random();
		return r.nextInt(2)*60;
	}
	
	
	public District distrettoVicino(District a) {
		List<District> listaVicini = Graphs.neighborListOf(this.grafo, a);
		Collections.sort(listaVicini, new Comparatore(a));
		for(District d : listaVicini) {
			if(this.mappaDistretti.get(d.getDistrictId())>0) {
				return d;
			}
			
		}
		return null;
	}

}
