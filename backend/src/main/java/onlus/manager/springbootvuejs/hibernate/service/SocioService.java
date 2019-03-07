package onlus.manager.springbootvuejs.hibernate.service;

import java.util.List;


import onlus.manager.springbootvuejs.hibernate.domain.Socio;

public interface SocioService {

    Socio createSocio(Socio socio);
    void modificaPassword(String password);
    void modificaUtente(String nomeUtente);
    Socio editSocio(Socio person);
    void inviaMailNuovoSocio(String credenziali, String mail);
    void deleteSocio(int numTessera);
    List<Socio> getAllSoci();
    void salvaListaSoci();
    void visualizzaFileLog();
    void inviaMailAmministratore(String indirizzoMail);
    void ModificaOpzioniPllicazione();
}
