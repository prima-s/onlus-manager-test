package onlus.manager.springbootvuejs.controller;

import onlus.manager.springbootvuejs.domain.User;
import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.repository.SocioRepo;
import onlus.manager.springbootvuejs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocioRepo socioRepository;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @RequestMapping(path = "/socio", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewSocio (@RequestParam String nome, 
                                           @RequestParam String cognome, 
                                           @RequestParam String codFisc, 
                                           @RequestParam String indirizzo, 
                                           @RequestParam String citta,
                                           @RequestParam String cap, 
                                           @RequestParam String nazione, 
                                           @RequestParam String numCellulare, 
                                           @RequestParam String numFisso, 
                                           @RequestParam String mail1, 
                                           @RequestParam String mail2, 
                                           @RequestParam String campoNote) {
        Socio socio = new Socio(nome, cognome, codFisc, indirizzo, citta, cap, 
                                nazione, numCellulare, numFisso, mail1, mail2, campoNote);
        socioRepository.save(socio);

        LOG.info(socio.toString() + " successfully saved into DB");

        return socio.getNumTessera();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

}
