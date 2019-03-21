package onlus.manager.springbootvuejs.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table
@Indexed
public class Credenziali {
    @Id
    private Integer numTessera;
    @Field
    private String nomeUtente;
    @Field
    private String password;

    public Credenziali() {

    }

    public Credenziali(String nome, String password, Integer numTessera) {
        this.nomeUtente = nome;
        this.password = password;
        this.numTessera = numTessera;

    }

    public Integer getNumTessera() {
        return this.numTessera;
    }

    public void setNomeUtente(String nome) {
        this.nomeUtente = nome;
    }

    public String getNomeUtente() {
        return this.nomeUtente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return numTessera + ": " + nomeUtente + ", " + password;
    }

    
}