package onlus.manager.springbootvuejs.hibernate.domain;

import org.hibernate.search.annotations.Field;

import org.hibernate.search.annotations.TermVector;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Table
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
    private String indirizzo;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String cap;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String citta;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String nazione;

    @NotBlank
    @Field(termVector = TermVector.YES)
    private String codFisc;

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

    
    @Field(termVector = TermVector.YES)
    private LocalDate scadenzaTessera;

   
    @Field(termVector = TermVector.YES)
    private String campoNote;

   // @NotBlank
   // @Field(termVector = TermVector.YES)
    //private ArrayList<String> ruoli;
    @Field(termVector = TermVector.YES)
    private String ruoli;

    @Field(termVector = TermVector.YES)
    private boolean isAttivo;

    public Socio() {
    }

    public Socio(String nome, String cognome, String indirizzo, String cap, String citta,
    String nazione, String codFisc, String numCellulare, String numFisso, String mail1, String mail2,
    String campoNote, String[] ruolo) {
super();
this.isAttivo = true;
this.nome = nome;
this.cognome = cognome;
this.indirizzo = indirizzo;
this.cap = cap;
this.citta = citta;
this.nazione = nazione;
this.codFisc = codFisc;
this.numCellulare = numCellulare;
this.numFisso = numFisso;
this.mail1 = mail1;
this.mail2 = mail2;
this.scadenzaTessera = LocalDate.of(LocalDate.now().getYear(), 12, 31);
this.campoNote = campoNote;
ArrayList<String> r = new ArrayList<>(elaboraRuoli(ruolo));
r.add(Ruolo.Ordinario.toString());
this.ruoli = r.toString().replace("[", "").replace("]", "").replace(",", " ");
}

public Boolean getIsAttivo() {
return isAttivo;
}

public ArrayList<String> elaboraRuoli(String[] ruolo){
    ArrayList<String> ruoli = new ArrayList<>();
for (String s : ruolo) {
    ruoli.add(s);
}
 return ruoli;
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

public LocalDate getScadenzaTessera() {
return scadenzaTessera;
}
public void setScadenzaTessera(LocalDate scadenzaTessera) {
this.scadenzaTessera = scadenzaTessera;
}
public String getCampoNote() {
return campoNote;
}
public void setCampoNote(String campoNote) {
this.campoNote = campoNote;
}
public String getRuolo() {
return ruoli;
}

@Override
public String toString() {
return "Socio [isAttivo=" + isAttivo + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo
        + ", cap=" + cap + ", citta=" + citta + ", nazione=" + nazione + ", codFisc=" + codFisc
        + ", numCellulare=" + numCellulare + ", numFisso=" + numFisso + ", mail1=" + mail1 + ", mail2=" + mail2
        + ", numTessera=" + numTessera + ", campoNote=" + campoNote + "]";
}
}
