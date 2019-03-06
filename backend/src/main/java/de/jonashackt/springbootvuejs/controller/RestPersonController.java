package de.jonashackt.springbootvuejs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jonashackt.springbootvuejs.hibernate.domain.Person;
import de.jonashackt.springbootvuejs.hibernate.service.PersonService;

@RestController()
@RequestMapping("/rest")
public class RestPersonController {

    private static final Logger LOG = LoggerFactory.getLogger(RestPersonController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
	private PersonService service;

    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Person insert (@RequestBody Person requestPerson) {
        
    	Person persistedPerson = service.createPerson(requestPerson);
    	
        LOG.info(persistedPerson.toString() + " successfully saved into DB");

        return persistedPerson;
    }
    
    @RequestMapping(path = "/person", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Person update (@RequestBody Person requestPerson) {
    	Person persistedPerson = service.editPerson(requestPerson);
        LOG.info(persistedPerson.toString() + " successfully updated into DB");
        return persistedPerson;
    }
    

    @RequestMapping(path="/person/{id}", method = RequestMethod.GET)
    public @ResponseBody Person getPersonById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return service.getPerson(id);
    }
    
    @RequestMapping(path="/person/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePersonById(@PathVariable("id") long id) {
        LOG.info("Delete user with id " + id + " from database.");
        service.deletePerson(id);
    }
    
    @RequestMapping(path="/persons", method = RequestMethod.GET)
    public @ResponseBody List<Person> getPersons() {
        return service.getAllPersons();
    }

}
