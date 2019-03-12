package onlus.manager.springbootvuejs.controller;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import onlus.manager.springbootvuejs.hibernate.domain.Socio;

import onlus.manager.springbootvuejs.hibernate.service.SocioService;

@RestController()
@RequestMapping("/api")
public class RestSocioController {

    private static final Logger LOG = LoggerFactory.getLogger(RestSocioController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private SocioService service;



    @RequestMapping(path = "/person", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)

    public @ResponseBody Socio insert (@RequestParam("nome") String nome, 
    @RequestParam("cognome") String cognome,@RequestParam("indirizzo") String indirizzo,
    @RequestParam("cap") String cap,@RequestParam("citta") String citta,@RequestParam("nazione") String nazione,
    @RequestParam("cf") String cf,@RequestParam("cellulare") String cellulare,
    @RequestParam("fisso") String fisso,@RequestParam("email") String email,@RequestParam("email2")String email2,
    @RequestParam("note") String note, @RequestParam("ruoli")  String[] ruoli) { 
    
        Socio requestSocio = new Socio(nome,cognome,indirizzo,cap,citta,nazione,cf,cellulare,fisso,email, email2, note, ruoli);

        Socio persistedSocio = service.createSocio(requestSocio);

        LOG.info(persistedSocio.toString() + " successfully saved into DB");

        return persistedSocio;
    }

  /*  @RequestMapping(path = "/callservice", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Socio update (@RequestBody Socio requestSocio) {
    	Socio persistedSocio = service.editSocio(requestSocio);
        LOG.info(persistedSocio.toString() + " successfully updated into DB");
        return persistedSocio;
    }*/

    @RequestMapping(path="/person/{numTessera}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSocioById(@PathVariable("numTessera") Integer numTessera) {
        LOG.info("Delete socio con numero tessera " + numTessera + " from database.");
        service.deleteSocio(numTessera);
    }
    
    @RequestMapping(path="/persons", method = RequestMethod.GET)
    public @ResponseBody List<Socio> getSoci() {
        return service.getAllSoci();
    }

}
