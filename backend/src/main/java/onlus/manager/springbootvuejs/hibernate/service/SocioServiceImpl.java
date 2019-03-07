package onlus.manager.springbootvuejs.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;

@Service
public class SocioServiceImpl implements SocioService {
	
	@Autowired
	private SocioRepository socioRepository;

    @Autowired
    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    @Override
    public Socio createSocio(Socio socio) {
        return socioRepository.save(socio);
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
