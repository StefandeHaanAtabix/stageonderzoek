package server.artikelenservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.artikelenservice.Repository.ArtikelRepository;
import server.artikelenservice.model.Artikel;

import java.util.List;

/**
 * @author Stefan de Haan
 */
@RestController
public class ArtikelController {

    @Autowired
    private ArtikelRepository artikelRepository;

    @GetMapping("/list")
    public List<Artikel> artikelList() {
        return artikelRepository.getAll();
    }
    @PostMapping("/update")
    public Artikel createArtikel(@RequestBody Artikel artikel) {
        return artikelRepository.update(artikel);
    }
    @GetMapping("/find/{id}")
    public Artikel findById(@PathVariable int id) {
        return artikelRepository.find(id);
    }

    @GetMapping("/find/naam/{naam}")
    public Artikel findByName(@PathVariable String naam) {
        return artikelRepository.find(naam);
    }

    @DeleteMapping("/delete")
    public void deleteArtikel(@RequestBody Artikel artikel) {
        artikelRepository.delete(artikel);
    }
}
