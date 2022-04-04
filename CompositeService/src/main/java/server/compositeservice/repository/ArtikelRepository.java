package server.compositeservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.compositeservice.Model.Artikel;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stefan de Haan
 */
@Component
@Transactional
public class ArtikelRepository {
    @Autowired
    private EntityManager em;

    public List<Artikel> getAll() {
        return em.createNamedQuery("get_all_artikelen", Artikel.class).getResultList();
    }
    public Artikel save(Artikel artikel) {
        return em.merge(artikel);
    }
    public Artikel update(Artikel artikel) {
        return this.save(artikel);
    }
    public Artikel find(int id) {
        return em.find(Artikel.class, id);
    }
    public Artikel find(String naam) {
        List<Artikel> artikelen = getAll();

        List<Artikel> matches = artikelen.stream()
                .filter(artikel -> artikel.getTitel().equals(naam))
                .collect(Collectors.toList());
        return matches.get(0);
    }
    public void delete(Artikel artikel) {
        em.remove(artikel);
    }
}
