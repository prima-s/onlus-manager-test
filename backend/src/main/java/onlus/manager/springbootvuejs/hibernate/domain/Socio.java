package onlus.manager.springbootvuejs.hibernate.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Indexed
public class Socio {

    @Id
    @GeneratedValue
    private Integer numTessera;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String nome;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String cognome;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String codFisc;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String indirizzo;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String citta;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String cap;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String nazione;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String numCellulare;

    
    @Field(termVector = TermVector.YES)
    private String numFisso;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String mail1;

    @Field(termVector = TermVector.YES)
    private String mail2;

    // @Field(termVector = TermVector.YES)
    // private LocalDate scadenzaTessera;

    @Field(termVector = TermVector.YES)
    private String campoNote;

    // @NotBlank
    // @Field(termVector = TermVector.YES)
    // private ArrayList<Ruolo> ruoli;

    // @NotBlank
    // @Field(termVector = TermVector.YES)
    // private Credenziali credenziali;

    //@NotBlank
    @Field(termVector = TermVector.YES)
    private boolean isAttivo;

    public Socio() {
    }

    public Socio(String nome, String cognome, String codFisc, String indirizzo, String citta, String cap,
    String nazione, String numCellulare, String numFisso, String mail1, String mail2, String campoNote) {

this.nome = nome;
this.cognome = cognome;
this.codFisc = codFisc;
this.indirizzo = indirizzo;
this.citta = citta;
this.cap = cap;
this.nazione = nazione;
this.numCellulare = numCellulare;
this.numFisso = numFisso;
this.mail1 = mail1;
this.mail2 = mail2;
//this.scadenzaTessera = "";
this.campoNote = campoNote;
this.isAttivo = true;
// ruoli = new ArrayList<>(ruolo);
// ruoli.add(Ruolo.Ordinario);
//this.credenziali = new Credenziali(""+this id, this.nome+ "."+this.cognome+"."+ this id);
}

public Boolean getIsAttivo() {
return isAttivo;
}
public void setIsAttivo(Boolean isAttivo) {
this.isAttivo = isAttivo;
}
public String getNome() {
return nome;
}
public void setNome(String nome) {
this.nome = nome;
}
public String getCognome() {
return cognome;
}
public void setCognome(String cognome) {
this.cognome = cognome;
}
public String getIndirizzo() {
return indirizzo;
}
public void setIndirizzo(String indirizzo) {
this.indirizzo = indirizzo;
}
public String getCap() {
return cap;
}
public void setCap(String cap) {
this.cap = cap;
}
public String getCitta() {
return citta;
}
public void setCitta(String citta) {
this.citta = citta;
}
public String getNazione() {
return nazione;
}
public void setNazione(String nazione) {
this.nazione = nazione;
}
public String getCodFisc() {
return codFisc;
}
public void setCodFisc(String codFisc) {
this.codFisc = codFisc;
}
public String getNumCellulare() {
return numCellulare;
}
public void setNumCellulare(String numCellulare) {
this.numCellulare = numCellulare;
}
public String getNumFisso() {
return numFisso;
}
public void setNumFisso(String numFisso) {
this.numFisso = numFisso;
}
public String getMail1() {
return mail1;
}
public void setMail1(String mail1) {
this.mail1 = mail1;
}
public String getMail2() {
return mail2;
}
public void setMail2(String mail2) {
this.mail2 = mail2;
}
public Integer getNumTessera() {
    return numTessera;
}

public void setNumTessera(Integer numTessera) {
    this.numTessera = numTessera;
}

// public LocalDate getScadenzaTessera() {
// return scadenzaTessera;
// }
// public void setScadenzaTessera(LocalDate scadenzaTessera) {
// this.scadenzaTessera = scadenzaTessera;
// }
public String getCampoNote() {
return campoNote;
}
public void setCampoNote(String campoNote) {
this.campoNote = campoNote;
}
// public ArrayList<Ruolo> getRuolo() {
// return ruoli;
// }

@Override
public String toString() {
return "Socio [isAttivo=" + isAttivo + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo
        + ", cap=" + cap + ", citta=" + citta + ", nazione=" + nazione + ", codFisc=" + codFisc
        + ", numCellulare=" + numCellulare + ", numFisso=" + numFisso + ", mail1=" + mail1 + ", mail2=" + mail2
        + ", id=" + numTessera + ", campoNote=" + campoNote + "]";
}
}
