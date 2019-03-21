package onlus.manager.springbootvuejs.hibernate.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Voce {
	@Id
    @GeneratedValue
    private Integer idVoce;

	@NotBlank
    @Field(termVector = TermVector.YES)
    private String nomeVoce;

    @Field(termVector = TermVector.YES)
    private BigDecimal sommaAssociata;

    
    @Field(termVector = TermVector.YES)
    private LocalDate dataRiferimento;

    @Field(termVector = TermVector.YES)
	private String note;

	public Voce(){}

	public Voce(String nomeVoce, BigDecimal sommaAssociata, LocalDate dataRiferimento, String note) {
		super();
		this.nomeVoce = nomeVoce;
		this.sommaAssociata = sommaAssociata;
		this.dataRiferimento = dataRiferimento;
		this.note = note;
	}

	public Integer getIdVoce() {
		return this.idVoce;
	}
	public String getNomeVoce() {
		return this.nomeVoce;
	}
	public void setNomeVoce(String nomeVoce) {
		this.nomeVoce = nomeVoce;
	}
	public BigDecimal getSommaAssociata() {
		return this.sommaAssociata;
	}
	public void setSommaAssociata(BigDecimal sommaAssociata) {
		this.sommaAssociata = sommaAssociata;
	}
	public LocalDate getDataRiferimento() {
		return this.dataRiferimento;
	}
	public void setDataRiferimento(LocalDate dataRiferimento) {
		this.dataRiferimento = dataRiferimento;
	}
	public String getNote() {
		return this.note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getAnno(){
		return this.dataRiferimento.getYear();
	}

	@Override
	public String toString() {
		return "Voce [idVoce=" + idVoce + ", nomeVoce=" + nomeVoce + ", sommaAssociata=" + sommaAssociata
				+ ", dataRiferimento=" + dataRiferimento + ", note=" + note + "]";
	}
	
	

}
