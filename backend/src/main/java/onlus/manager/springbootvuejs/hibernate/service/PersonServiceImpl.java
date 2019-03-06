package onlus.manager.springbootvuejs.hibernate.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlus.manager.springbootvuejs.hibernate.domain.Person;
import onlus.manager.springbootvuejs.hibernate.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
    		Person retValue = null;
        	try {
        		retValue = personRepository.findById(id).get();
        	}
        	catch (NoSuchElementException e) {
        		//no element found
				retValue = null;
			}
        	return retValue;
    }

    @Override
    public Person editPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getAllPersons(int pageNumber, int pageSize) {
        return personRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
    @Override
    public long countPersons() {
        return personRepository.count();
    }
    
    
}
