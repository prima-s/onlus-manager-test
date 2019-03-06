package de.jonashackt.springbootvuejs.hibernate.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.jonashackt.springbootvuejs.hibernate.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
