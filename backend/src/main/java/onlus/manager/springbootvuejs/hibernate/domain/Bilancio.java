package onlus.manager.springbootvuejs.hibernate.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bilancio {
	
	private ArrayList<Voce> voci;
	

	public Bilancio() {
		voci =new ArrayList<>();
	}
	
	public void aggiungiVoce(Voce v) {
		voci.add(v);
	}
	
	public BigDecimal getBilancioAnnuale(LocalDate data) {
		BigDecimal somma = new BigDecimal(0); 
		for (Voce voce : voci) {
			if (voce.getDataRiferimento().equals(data));
			somma.add(voce.getSommaAssociata());
		}
		return somma;
	}
	
	public BigDecimal getBilancioTotale() {
		BigDecimal somma = new BigDecimal(0); 
		for (Voce voce : voci) {
			somma =	somma.add(voce.getSommaAssociata());
		}
		return somma;
	}
	
	

}
