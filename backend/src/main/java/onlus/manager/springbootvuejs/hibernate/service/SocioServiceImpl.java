package onlus.manager.springbootvuejs.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;

import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;
import onlus.manager.springbootvuejs.hibernate.repository.CredenzialiRepository;

@Service
public class SocioServiceImpl implements SocioService {
	
	@Autowired
    private SocioRepository socioRepository;
    private CredenzialiRepository credenzialiRepository;

    @Autowired
    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }
    @Override
    public Socio createSocio(Socio socio) {
         
        credenzialiRepository.save(new Credenziali(socio.getNumTessera().toString(), socio.getNome()+"."+socio.getCognome(), socio.getNumTessera()));
        return socioRepository.save(socio);
    }

    @Override
    public Socio getSocio(Integer id) {
        return socioRepository.getOne(id);
    }

    @Override
    public Socio editSocio(Socio socio) {
        return socioRepository.save(socio);
    }

    @Override
    public void deleteSocio(int numTessera) {
        socioRepository.deleteById(numTessera);
    }

    @Override
    public List<Socio> getAllSoci() {
        return socioRepository.findAll();
    }

    @Override
    public void modificaPassword(String password) {

    }

    @Override
    public void modificaUtente(String nomeUtente) {

    }

    @Override
    public void inviaMailNuovoSocio(String credenziali, String mail) {

    }

    @Override
    public void salvaListaSoci() {

    }

    @Override
    public void visualizzaFileLog() {

    }

    @Override
    public void inviaMailAmministratore(String indirizzoMail) {

    }

    @Override
    public void ModificaOpzioniPllicazione() {

    }
 
    
}
