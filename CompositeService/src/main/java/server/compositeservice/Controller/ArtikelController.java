package server.compositeservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.compositeservice.Model.Artikel;
import server.compositeservice.repository.ArtikelRepository;

import java.util.List;

/**
 * @author Stefan de Haan
 */
@RestController
public class ArtikelController {

    @Autowired
    private ArtikelRepository artikelRepository;

    @GetMapping("/artikelen/list")
    public List<Artikel> artikelList() {
        return artikelRepository.getAll();
    }
    @PostMapping("/artikelen/update")
    public Artikel createArtikel(@RequestBody Artikel artikel) {
        return artikelRepository.update(artikel);
    }
    @GetMapping("/artikelen/find/{id}")
    public Artikel findById(@PathVariable int id) {
        return artikelRepository.find(id);
    }

    @GetMapping("/artikelen/find/naam/{naam}")
    public Artikel findByName(@PathVariable String naam) {
        return artikelRepository.find(naam);
    }

    @DeleteMapping("/artikelen/delete")
    public void deleteArtikel(@RequestBody Artikel artikel) {
        artikelRepository.delete(artikel);
    }
}
