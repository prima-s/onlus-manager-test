package onlus.manager.springbootvuejs.hibernate.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Voce {

	@Id
    @GeneratedValue
    private Integer idVoce;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private TipoVoce nomeVoce;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private BigDecimal sommaAssociata;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private LocalDate dataRiferimento;

    @NotBlank
    @Field(termVector = TermVector.YES)
	private String note;
	
	int anno;

	public Voce(){

	}

	public Voce(TipoVoce nomeVoce, BigDecimal sommaAssociata, LocalDate dataRiferimento, String note) {
		super();
		this.nomeVoce = nomeVoce;
		this.sommaAssociata = sommaAssociata;
		this.dataRiferimento = dataRiferimento;
		this.note = note;
		anno=dataRiferimento.getYear();
	}
	public Integer getIdVoce() {
		return idVoce;
	}

	public TipoVoce getNomeVoce() {
		return nomeVoce;
	}
	public void setNomeVoce(TipoVoce nomeVoce) {
		this.nomeVoce = nomeVoce;
	}
	public BigDecimal getSommaAssociata() {
		return sommaAssociata;
	}
	public void setSommaAssociata(BigDecimal sommaAssociata) {
		this.sommaAssociata = sommaAssociata;
	}
	public LocalDate getDataRiferimento() {
		return dataRiferimento;
	}
	public void setDataRiferimento(LocalDate dataRiferimento) {
		this.dataRiferimento = dataRiferimento;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Voce [idVoce=" + idVoce + ", nomeVoce=" + nomeVoce + ", sommaAssociata=" + sommaAssociata
				+ ", dataRiferimento=" + dataRiferimento + ", note=" + note + "]";
	}
	
	

}
