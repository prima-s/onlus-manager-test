package onlus.manager.springbootvuejs.hibernate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import onlus.manager.springbootvuejs.hibernate.domain.Voce;


public interface BilancioService {

    Voce modificaVoceInBilancio(Voce voce);
    Voce aggiungiVoceInBilancio(Voce voce);
    void rimuoviVoceInBilancio(Integer id);
    void salvaBilancio(List<Voce> listaVoci);
    BigDecimal visualizzaBilancioAnnuale(int anno);
    BigDecimal visualizzaBilancioTotale();
    List<Voce> visualizzaVociInBilancio();
    List<Voce> visualizzaVociTemporale(LocalDate data);

}