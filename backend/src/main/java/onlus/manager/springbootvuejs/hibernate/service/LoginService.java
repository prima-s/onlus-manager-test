package onlus.manager.springbootvuejs.hibernate.service;



import onlus.manager.springbootvuejs.hibernate.domain.Socio;

public interface LoginService {

 Integer verificaUtenze(String utente, String password);
 Socio getSocio(Integer id);
 String getLivelloAmministrazione(Socio socio);

}