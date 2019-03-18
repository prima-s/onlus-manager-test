package onlus.manager.springbootvuejs.hibernate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import onlus.manager.springbootvuejs.hibernate.domain.TipoVoce;
import onlus.manager.springbootvuejs.hibernate.domain.Voce;


public interface VoceService {

    Voce modificaVoceInBilancio(Voce voce);
    TipoVoce aggiungiVoceInBilancio(TipoVoce voce);
    void rimuoviVoceInBilancio(Integer id);
    //List<Voce> salvaBilancio();
    //BigDecimal visualizzaBilancioAnnuale(int anno);
    //BigDecimal visualizzaBilancioTotale();

    //List<Voce> visualizzaVociInBilancio();
    //List<Voce> visualizzaVociTemporale(LocalDate data);

}