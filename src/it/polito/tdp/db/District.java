package it.polito.tdp.db;

public class District {

	private int districtId;
	private double avgGeoLon;
	private double avgGeoLat;
	
	
	
	public District(int districtId, double avgGeoLon, double avgGeoLat) {
		this.districtId = districtId;
		this.avgGeoLon = avgGeoLon;
		this.avgGeoLat = avgGeoLat;
	}



	public int getDistrictId() {
		return districtId;
	}



	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}



	public double getGeoLon() {
		return avgGeoLon;
	}



	public void setGeoLon(double geoLon) {
		this.avgGeoLon = geoLon;
	}



	public double getGeoLat() {
		return avgGeoLat;
	}



	public void setGeoLat(double geoLat) {
		this.avgGeoLat = geoLat;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + districtId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (districtId != other.districtId)
			return false;
		return true;
	}
	
	
	
	
	
	
}
