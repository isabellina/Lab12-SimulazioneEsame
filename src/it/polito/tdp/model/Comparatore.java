package it.polito.tdp.model;

import java.util.Comparator;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.District;

public class Comparatore implements Comparator<District> {

	private District centro;

	public Comparatore(District centro) {
		this.centro = centro;
	}

	@Override
	public int compare(District d1, District d2) {
		LatLng centroAssoluto = new LatLng(this.centro.getGeoLat(), this.centro.getGeoLon());
		LatLng centro1 = new LatLng(d1.getGeoLat(), d1.getGeoLon());
		LatLng centro2 = new LatLng(d2.getGeoLat(), d2.getGeoLon());
		double distanza = LatLngTool.distance(centro1, centroAssoluto, LengthUnit.KILOMETER);
		double distanza2 = LatLngTool.distance(centro2, centroAssoluto, LengthUnit.KILOMETER);
		if(distanza<distanza2) {
			return -1;
		}
		else if(distanza>distanza2) {
			return 1;
		}
		return 0;
	}
}
