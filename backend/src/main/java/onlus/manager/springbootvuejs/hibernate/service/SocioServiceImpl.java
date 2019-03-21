package onlus.manager.springbootvuejs.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;
import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;
import onlus.manager.springbootvuejs.hibernate.repository.CredenzialiRepository;
import onlus.manager.springbootvuejs.hibernate.repository.LoginRepository;

@Service
public class SocioServiceImpl implements SocioService {

    @Autowired
    private SocioRepository socioRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private CredenzialiRepository credenzialiRepository;

    @Autowired
    public SocioServiceImpl(SocioRepository socioRepository, LoginRepository loginRepository, CredenzialiRepository credenzialiRepository) {
        this.socioRepository = socioRepository;
        this.loginRepository = loginRepository;
        this.credenzialiRepository = credenzialiRepository;
    }

    @Override
    public Socio createSocio(Socio socio) {
        Socio s = socioRepository.save(socio);
        Credenziali cd = new Credenziali(s.getNumTessera().toString(), s.getNome() + "." + s.getCognome(),
                s.getNumTessera());
        loginRepository.save(cd);
        return s;
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
    public Credenziali aggiornaPassword(String oldPassword, String newPassword) {
        Credenziali cred = credenzialiRepository.findByPassword(oldPassword);
        if (cred != null) {
            cred.setPassword(newPassword);
            loginRepository.save(cred);
        }
        return cred;
    }

    @Override
    public void modificaUtente(String nomeUtente) {
        // TODO
    }

    @Override
    public void inviaMailNuovoSocio(String credenziali, String mail) {
        // TODO
    }

    @Override
    public void salvaListaSoci() {
        // TODO
    }

    @Override
    public void visualizzaFileLog() {
        // TODO
    }

    @Override
    public void inviaMailAmministratore(String indirizzoMail) {
        // TODO
    }

    @Override
    public void ModificaOpzioniApplicazione() {
        // TODO
    }

}
