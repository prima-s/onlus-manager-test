package onlus.manager.springbootvuejs.repository;

import onlus.manager.springbootvuejs.hibernate.domain.Socio;
import onlus.manager.springbootvuejs.hibernate.repository.LoginRepository;
import onlus.manager.springbootvuejs.hibernate.repository.SocioRepository;
import onlus.manager.springbootvuejs.hibernate.service.SocioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SocioRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private SocioRepository socioRepository;
    
    @Autowired
    private LoginRepository loginRepository;

    private SocioServiceImpl service;
    private Socio marioRossi;
    private Socio luigiVerdi;
    private Socio socioNull;
    
    @Before
    public void setup() {
        marioRossi = new Socio("mario", "rossi", "Via delle frasche", "00177", "Venezia", "Italia", "AAAAAA000000BBBBBB", "3390000123", "062343123", "mario@mail.com", "mario3@mail.com", "note di mario", new String[]{});
        luigiVerdi = new Socio("luigi", "verdi", "Via che non esiste", "23563", "Milano", "Romania", "AAAAAA000000BBBBBB", "3384345334", "069383883", "luigi@mail.com", "luigi3@mail.com", "note di luigi", new String[]{});
        socioNull = null;
        service = new SocioServiceImpl(socioRepository, loginRepository);
        
    }


    @Test
    public void testCreateSocio() throws Exception {

        service.createSocio(marioRossi);
        List<Socio> listaSoci = socioRepository.findAll();

        assertThat(listaSoci, contains(marioRossi));
    }


    @Test
    public void testCreateSocioConValoreNull() throws Exception {

        service.createSocio(marioRossi);
        List<Socio> listaSoci = socioRepository.findAll();

        assertEquals("Il metodo accetta soci con valore null", listaSoci.contains(socioNull), false);
    }


/*     @Test
    public void testGetSocioIdValido() throws Exception {
        
        Socio mar = service.createSocio(marioRossi);
        Socio mario = service.getSocio(1);
        
        String marCF = mar.getCodFisc();
        String marioCF = mario.getCodFisc();

        assertEquals("Il socio associato all'id non viene restituito correttamente", marioCF.equals(marCF), true);
    } */


    public static void main(String[] args) {
        
        Socio marioRossi = new Socio("mario", "rossi", "Via delle frasche", "00177", "Venezia", "Italia", "AAAAAA000000BBBBBB", "3390000123", "062343123", "mario@mail.com", "mario3@mail.com", "note di mario", new String[]{});
        System.out.println(marioRossi.getCodFisc());
    }


    @Test
    public void testEditSocio() throws Exception {

        Socio mario = service.createSocio(marioRossi);
        mario.setCognome("gialli");
        Socio socioModificato = service.editSocio(mario);
        List<Socio> listaSoci = socioRepository.findAll();
        int numeroSoci = listaSoci.size();

        assertEquals("Le informazioni del socio non vengono modificate correttamente", numeroSoci == 1, true);
        assertEquals("Il socio non viene modificaro correttamente", socioModificato.getCognome().equals("gialli"), true);
        assertEquals("Il socio non viene modificaro correttamente", socioModificato.equals(mario), true);
    }


    @Test
    public void testDeleteSocio() throws Exception {

        service.createSocio(marioRossi);
        service.deleteSocio(1);
        List<Socio> listaSoci = socioRepository.findAll();

        assertEquals("Il metodo accetta soci con valore null", listaSoci.contains(marioRossi), false);
    }


    @Test
    public void testGetAllSoci() throws Exception {

        service.createSocio(marioRossi);
        service.createSocio(luigiVerdi);
        List<Socio> listaSoci = socioRepository.findAll();
        int numeroSoci = listaSoci.size();

        assertEquals("La lista soci non viene restituita correttamente", numeroSoci == 2, true);
    }





}
