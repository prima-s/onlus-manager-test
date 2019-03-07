package onlus.manager.springbootvuejs.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Credenziali{
    @Id
    private Integer numTessera;
    @Field
    private String nomeUtente;
    @Field
    private String password;

    public Credenziali(Integer numTessera, String nome, String password){
        this.nomeUtente = nome;
        this.password = password;
        this.numTessera = numTessera;
    }

    
}