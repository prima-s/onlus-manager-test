package onlus.manager.springbootvuejs.hibernate.service;
import java.util.List;

import onlus.manager.springbootvuejs.hibernate.domain.Person;

public interface PersonService {

    Person createPerson(Person person);
    Person getPerson(Long id);
    Person editPerson(Person person);
    void deletePerson(Person person);
    void deletePerson(Long id);
    List<Person> getAllPersons(int pageNumber, int pageSize);
    List<Person> getAllPersons();
    long countPersons();
}
