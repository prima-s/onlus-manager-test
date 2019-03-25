package onlus.manager.springbootvuejs.hibernate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import onlus.manager.springbootvuejs.hibernate.repository.VoceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.repository.BilancioRepository;

import onlus.manager.springbootvuejs.hibernate.domain.Voce;

@Service
public class BilancioServiceImpl implements BilancioService {

    @Autowired
    private BilancioRepository bilancioRepository;

    @Autowired
    private VoceRepository voceRepository;

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
    public BigDecimal visualizzaBilancioTotale() {
        BigDecimal somma = BigDecimal.ZERO;
        List<Voce> voci = visualizzaVociInBilancio();
        for (Voce voce : voci) {
            somma = somma.add(voce.getSommaAssociata());
        }
        return somma;
    }

    @Override
    public List<Voce> visualizzaVociInBilancio() {
        return voceRepository.findAll();
    }

    @Override
    public List<Voce> visualizzaVociTemporale(int anno){
        List<Voce> voci = visualizzaVociInBilancio();
        return voci.stream().filter(voce -> anno == voce.getAnno()).collect(Collectors.toList());
    }

    @Override
    public BigDecimal modificaBilancio(BigDecimal newImporto) {
        BigDecimal importo = visualizzaBilancioTotale();
        importo = newImporto; 
        return importo;
    }


    public BigDecimal visualizzaBilancioAnnuale(int anno) {
        BigDecimal somma = BigDecimal.ZERO;
        List<Voce> voci = visualizzaVociInBilancio();
        for (Voce voce : voci) {
            if(anno == voce.getAnno()){
                somma = somma.add(voce.getSommaAssociata());
            }
        }
        return somma;
    }

    @Override
    public String aggiungiVoce(String voce) {
        return null;
    }

    @Override
    public String rimuoviVoce(String voce) {
        return null;
    }

    @Override
    public List<String> listaTipologieVoci() {
        return null;
    }
    }
  
