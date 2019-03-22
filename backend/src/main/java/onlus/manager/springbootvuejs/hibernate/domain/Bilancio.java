package onlus.manager.springbootvuejs.hibernate.domain;

import java.util.ArrayList;

public class Bilancio {
	
	private ArrayList<Voce> voci;
	private ArrayList<String> tipoVoci;
	

	public Bilancio() {
		voci = new ArrayList<>();
		tipoVoci = new ArrayList<>();
		tipoVoci.add("Tesseramento");
		tipoVoci.add("Donazione");
		tipoVoci.add("Affitto sedi");
		tipoVoci.add("Rimborso spese attività");
		tipoVoci.add("Quota mensile attività");
		tipoVoci.add("Assicurazione");
		tipoVoci.add("Acquisto materiali");
	}

	/**
	 * @return the voci
	 */
	public ArrayList<Voce> getVoci() {
		return voci;
	}

	/**
	 * @return the tipoVoci
	 */
	public ArrayList<String> getTipoVoci() {
		return tipoVoci;
	}
	

}
