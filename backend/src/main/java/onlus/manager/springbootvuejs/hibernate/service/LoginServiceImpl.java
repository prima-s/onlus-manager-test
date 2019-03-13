package onlus.manager.springbootvuejs.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;
import onlus.manager.springbootvuejs.hibernate.domain.Ruolo;
import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;
import onlus.manager.springbootvuejs.hibernate.utility.HibernateUtil;

import onlus.manager.springbootvuejs.hibernate.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;
    private SocioRepository socioRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository, SocioRepository socioRepository) {
        this.loginRepository = loginRepository;
        this.socioRepository = socioRepository;
    }

    @Override
    public Integer verificaUtenze(String utente, String password) {

        List<Credenziali> credenziali = loginRepository.findAll();
        for (Credenziali credenziale : credenziali) {

            if (credenziale.getNomeUtente().equals(utente)) {

                if (credenziale.getPassword().equals(password)) {
                    return credenziale.getNumTessera();
                }

            }
        }
        return 0;

    }

    @Override
    public Socio getSocio(Integer id) {
        return socioRepository.getOne(id);
    }

    @Override
    public String getLivelloAmministrazione(Socio socio) {
        String[] ruoli = socio.getRuolo().split(",");
        for (String s : ruoli) {
            if (s.equals("amministratore")) {
                return "livello1";
            }
            if (s.equals("presidente") || s.equals("tesoriere")) {
                return "livello2";
            }

        }
        return "livello3";
    }

}