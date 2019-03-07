package onlus.manager.springbootvuejs.hibernate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.repository.BilancioRepository;

import onlus.manager.springbootvuejs.hibernate.domain.Voce;

@Service
public class BilancioServiceImpl implements BilancioService {

    @Autowired
    private BilancioRepository bilancioRepository;

    @Autowired
    public BilancioServiceImpl(BilancioRepository bilancioRepository) {
        this.bilancioRepository = bilancioRepository;
    }

    @Override
    public Voce modificaVoceInBilancio(Voce voce) {
        return bilancioRepository.save(voce);
    }

    @Override
    public Voce aggiungiVoceInBilancio(Voce voce) {
        return bilancioRepository.save(voce);

    }

    @Override
    public void rimuoviVoceInBilancio(Integer idVoce) {
        bilancioRepository.deleteById(idVoce);
    }

    @Override
    public void salvaBilancio(List<Voce> bilancio) {
       // List<Voce> bil = bilancioRepository.saveAll(bilancio);



    }

    @Override
    public BigDecimal visualizzaBilancioAnnuale(int anno) {


       // Criteria criteria = session.createCriteria(Voce.class);

        //List<Voce> list = criteria.add(Restrictions.eq("anno", anno)).list();

        BigDecimal result = BigDecimal.ZERO;
       /* for (Voce v : list) {
            result = result.add(v.getSommaAssociata());
        }*/
        return null;
    }

    @Override
    public BigDecimal visualizzaBilancioTotale() {
        return null;
    }

    @Override
    public List<Voce> visualizzaVociInBilancio() {
        return null;
    }

    @Override
    public List<Voce> visualizzaVociTemporale(LocalDate data){
        return null;
    }
  
}