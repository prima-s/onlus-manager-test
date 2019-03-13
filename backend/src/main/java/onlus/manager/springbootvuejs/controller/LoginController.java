package onlus.manager.springbootvuejs.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;
import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;
import onlus.manager.springbootvuejs.hibernate.service.LoginService;
import onlus.manager.springbootvuejs.hibernate.service.SocioService;

@RestController()
@RequestMapping("/api")
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;



    @RequestMapping(path = "/restlogin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String accessoSocio (@RequestParam("utente") String requestNomeUtente, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller utente: "+ requestNomeUtente);
      Socio socio = service.getSocio(service.verificaUtenze(requestNomeUtente, requestPassword));
      if(socio.getNumTessera()!=0){
      System.out.println(service.getLivelloAmministrazione(socio));
      System.out.println(Arrays.toString(socio.getRuolo().split("")));
        return service.getLivelloAmministrazione(socio);
      }
      else{
        System.out.println("nome utente o password errati");
        return "nome utente o password errati";
      }
    }

    /*@RequestMapping(path = "/restlogin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer accessoSocio (@RequestParam("utente") String requestNomeUtente, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller utente: "+ requestNomeUtente);
      Integer id = service.verificaUtenze(requestNomeUtente, requestPassword);
      Socio socio = service.getSocio(id);
      System.out.println(id.toString());
      System.out.println(socio.toString());
      System.out.println(service.getLivelloAmministrazione(socio));
        return id;
    }*/
}