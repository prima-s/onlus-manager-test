package onlus.manager.springbootvuejs.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import onlus.manager.springbootvuejs.hibernate.domain.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {
}