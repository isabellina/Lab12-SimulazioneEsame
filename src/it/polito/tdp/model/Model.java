package it.polito.tdp.model;

import java.time.Month;
import java.time.Year;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.District;
import it.polito.tdp.db.EventsDao;

public class Model {
	
	private EventsDao eventsDao ;  
	private Graph grafo;
	
	public Model() {
		this.eventsDao = new EventsDao();
	}
	
	public List<Year> getAnni(){
		List<Year> lAnni = new LinkedList<Year>(eventsDao.getYear());
				return lAnni;
	}
	
	public List<Month> getMesi(Year y){
		List<Month> lMesi = new LinkedList<Month>(eventsDao.getMonth(y));
		return lMesi;
	}
	
	public List<Integer> getDay(Year y, Month m){
		List<Integer> lGiorni = new LinkedList<Integer>(eventsDao.getDay(y, m));
		return lGiorni;
	}
	
	
	public void creaGrafo(Year y) {
	this.grafo = new SimpleWeightedGraph<District, MyEdge>(MyEdge.class);
	
	for(District i : this.eventsDao.getDistricts(y)) {
		this.grafo.addVertex(i);
		
	}
	 for(Object o : this.grafo.vertexSet()) {
		 for(Object s : this.grafo.vertexSet()) {
			 if(!o.equals(s)) {
				 District a = (District) o;
				 District b = (District) s;
				 this.grafo.addEdge(a, b);
				 this.grafo.setEdgeWeight(a, b, this.distanzaCentri(a, b));
			 }
		 }
	 }
	
	}
	
	
	public double distanzaCentri(District d1 , District d2) {
		LatLng centro1 = new LatLng(d1.getGeoLat(), d1.getGeoLon());
		LatLng centro2 = new LatLng(d2.getGeoLat(), d2.getGeoLon());
		return LatLngTool.distance(centro1, centro2, LengthUnit.KILOMETER);
	}
	
	
	public String getAdiacenti(){
		//List<MyEdge> ltemp = new LinkedList<MyEdge>();
		
		String ret = "";
		
		List<District> ltemp = new LinkedList<District>();
		
		/*for(Object d : this.grafo.vertexSet()) {
			ret += ((District) d).getDistrictId() + " ";
			for(Object n : Graphs.neighborListOf(this.grafo, d)) {
				ltemp.add((MyEdge) this.grafo.getEdge(d, n));
			}
			for(MyEdge m : ltemp) {
				ret += ((District) m.getTarget()).getDistrictId();
			}
			ret += "\n";
			ltemp = new LinkedList<MyEdge>();
		}
		
		return ret; */
		
		for(Object o: this.grafo.vertexSet()) {
			District d = (District) o;
			ret+= "Elenco dei distretti adiacenti di: " + d.getDistrictId() + "\n " ;
			ltemp =  Graphs.neighborListOf(this.grafo, d);
			Collections.sort(ltemp , new Comparatore(d));
			for(District di: ltemp) {
				ret+= di.getDistrictId() + " \n" ;
			}
			ret = ret +"\n" ;
		}
		
		return ret;
		
	}
	
	
	
}
