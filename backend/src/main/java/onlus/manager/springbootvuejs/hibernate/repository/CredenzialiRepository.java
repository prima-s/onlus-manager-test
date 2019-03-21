package onlus.manager.springbootvuejs.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlus.manager.springbootvuejs.hibernate.domain.Credenziali;

@Repository
public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {
    Credenziali findByPassword(String password);
}