package onlus.manager.springbootvuejs.hibernate.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import onlus.manager.springbootvuejs.hibernate.repository.VoceRepository;
import onlus.manager.springbootvuejs.hibernate.domain.Voce;

@Service
public class VoceServiceImpl implements VoceService {

    @Autowired
    private VoceRepository voceRepository;


    @Autowired
    public VoceServiceImpl(VoceRepository voceRepository) {
        this.voceRepository = voceRepository;
    }

    public Voce aggiungiVoceInBilancio(Voce voce) {
        Voce v =  voceRepository.save(voce);
        return v;
    }

    public void rimuoviVoceInBilancio(Integer id) {
        voceRepository.deleteById(id);
    }

    public List<Voce> salvaBilancio() {
        return voceRepository.findAll();
    }

    public BigDecimal visualizzaBilancioAnnuale(int anno) {
        BigDecimal somma = new BigDecimal(0); 
        List<Voce> voci = voceRepository.findAll();
		for (Voce voce : voci) {
            if(anno == voce.getAnno()){
                somma = somma.add(voce.getSommaAssociata());
            }
		}
		return somma;
    }

    public BigDecimal visualizzaBilancioTotale() {
        BigDecimal somma = new BigDecimal(0); 
        List<Voce> voci = voceRepository.findAll();
		for (Voce voce : voci) {
            somma = somma.add(voce.getSommaAssociata());
		}
		return somma;
    }

    public Voce modificaVoceInBilancio(Voce voce) {
        return voceRepository.save(voce);
    }
    
}