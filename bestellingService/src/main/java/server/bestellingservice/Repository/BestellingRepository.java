package server.bestellingservice.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.bestellingservice.Model.Bestelling;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Stefan de Haan
 */
@Component
@Transactional
public class BestellingRepository {
    @Autowired
    private EntityManager em;

    public List<Bestelling> getAll() {
        return em.createNamedQuery("get_all_bestellingen", Bestelling.class).getResultList();
    }
    public Bestelling save(Bestelling bestelling) {
        return em.merge(bestelling);
    }
    public Bestelling update(Bestelling bestelling) {
        return this.save(bestelling);
    }
    public Bestelling find(int id) {
        return em.find(Bestelling.class, id);
    }
    public void delete(Bestelling bestelling) {
        em.remove(bestelling);
    }
}
