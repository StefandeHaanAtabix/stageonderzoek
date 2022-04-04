package server.klantservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.klantservice.model.Klant;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefan de Haan
 */
@Component
@Transactional
public class KlantRepository {
    @Autowired
    private EntityManager em;

    public List<Klant> getAll() {
        return em.createNamedQuery("get_all_Klanten", Klant.class).getResultList();
    }
    public Klant save(Klant klant) {
        return em.merge(klant);
    }
    public Klant update(Klant klant) {
        return this.save(klant);
    }
    public Klant find(int id) {
        return em.find(Klant.class, id);
    }

    public Klant find(String naam, LocalDate geboortedatum) {
        List<Klant> all = getAll();
        List<Klant> match = all.stream()
                .filter(klant -> klant.getNaam().equals(naam) && klant.getGeboortedatum().equals(geboortedatum))
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(match.toArray()));
        return match.get(0);
    }

    public void delete(Klant klant) {
        em.remove(klant);
    }
}
