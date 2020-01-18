package it.polito.tdp.model;

public class Crime {
	
	private int idEvento;
	private int idDistretto;
	private String categoriaEvento;
	private boolean otherCrimes;
	
	
	public Crime(int idEvento, int idDistretto,String categoriaEvento, boolean otherCrimes) {
		
		this.idEvento = idEvento;
		this.idDistretto = idDistretto;
		this.categoriaEvento = categoriaEvento;
		this.otherCrimes = otherCrimes;
	}


	public int getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}


	public int getIdDistretto() {
		return idDistretto;
	}


	public void setIdDistretto(int idDistretto) {
		this.idDistretto = idDistretto;
	}


	public boolean isOtherCrimes() {
		if(categoriaEvento.compareTo("all-other-crimes")==0) {
		return true;
		}
		return false;
	}


	public void setOtherCrimes(boolean otherCrimes) {
		this.otherCrimes = otherCrimes;
	}
	
	
	

}
