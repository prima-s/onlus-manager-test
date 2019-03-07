package onlus.manager.springbootvuejs.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import onlus.manager.springbootvuejs.hibernate.domain.Voce;

@Repository
public interface BilancioRepository extends JpaRepository<Voce, Integer> {
}