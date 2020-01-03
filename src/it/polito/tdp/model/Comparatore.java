package it.polito.tdp.model;

import java.util.Comparator;

import it.polito.tdp.db.District;

public class Comparatore implements Comparator {

	

	@Override
	public int compare(Object o1, Object o2) {
		MyEdge e = (MyEdge) o1;
		MyEdge a = (MyEdge) o2;
		if(e.getWeight()==a.getWeight()) {
			return 0;
		}
		else if(e.getWeight()<a.getWeight()) {
			return -1;
		}
		
		return 1;
	}

}
