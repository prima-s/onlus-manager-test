package onlus.manager.springbootvuejs.controller;

import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;
import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.domain.TipoVoce;
import onlus.manager.springbootvuejs.hibernate.domain.Voce;
import onlus.manager.springbootvuejs.hibernate.service.BilancioService;
import onlus.manager.springbootvuejs.hibernate.service.LoginService;
import onlus.manager.springbootvuejs.hibernate.service.SocioService;
import onlus.manager.springbootvuejs.hibernate.service.VoceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private VoceService voceService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private BilancioService bilancioService;

    @RequestMapping(path = "/restlogin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String accessoSocio (@RequestParam("utente") String requestNomeUtente, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller utente: "+ requestNomeUtente);
        Socio socio = loginService.getSocio(loginService.verificaUtenze(requestNomeUtente, requestPassword));
        if(socio.getNumTessera()!=0){
            System.out.println(loginService.getLivelloAmministrazione(socio));
            System.out.println(Arrays.toString(socio.getRuolo().split(",")));
            return loginService.getLivelloAmministrazione(socio);
        }
        else{
            System.out.println("nome utente o password errati");
            return "nome utente o password errati";
        }
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)

    public @ResponseBody Socio insert (@RequestParam("nome") String nome,
                                       @RequestParam("cognome") String cognome,@RequestParam("indirizzo") String indirizzo,
                                       @RequestParam("cap") String cap,@RequestParam("citta") String citta,@RequestParam("nazione") String nazione,
                                       @RequestParam("cf") String cf,@RequestParam("cellulare") String cellulare,
                                       @RequestParam("fisso") String fisso,@RequestParam("email") String email,@RequestParam("email2")String email2,
                                       @RequestParam("note") String note, @RequestParam("Ruolo")  String[] ruoli) {

        Socio requestSocio = new Socio(nome,cognome,indirizzo,cap,citta,nazione,cf,cellulare,fisso,email, email2, note, ruoli);

        Socio persistedSocio = socioService.createSocio(requestSocio);
        LOG.info(persistedSocio.toString() + " successfully saved into DB");
        System.out.println(socioService.getAllSoci().toString());

        return persistedSocio;
    }


    @RequestMapping(path="/aggiornaPassword", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean updatePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        
        Credenziali cred = socioService.aggiornaPassword(oldPassword, newPassword);
        if (cred != null) {
            LOG.info("Cambiata password: da '" + oldPassword + "' a '" + newPassword + "'" );
            return true;
        } else {
            LOG.info("La password '" + oldPassword + "' non è stata trovata" );
            return false;
        }
       
    }

    @RequestMapping(path="/soci", method = RequestMethod.GET)
    public @ResponseBody
    List<Socio> getSoci() {
        return socioService.getAllSoci();
    }


  

}
