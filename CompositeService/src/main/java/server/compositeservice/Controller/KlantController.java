package server.compositeservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.compositeservice.Model.Klant;
import server.compositeservice.repository.KlantRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Stefan de Haan
 */
@RestController
public class KlantController {

    @Autowired
    private KlantRepository klantRepository;

    @GetMapping("/klant/list")
    public List<Klant> klantList() {
        return klantRepository.getAll();
    }
    @PostMapping("/klant/update")
    public Klant createKlant(@RequestBody Klant klant) {
        return klantRepository.update(klant);
    }
    @GetMapping("/klant/find/{id}")
    public Klant findById(@PathVariable int id) {
        return klantRepository.find(id);
    }

    @GetMapping("/klant/find/{naam}/{geboortedatumString}")
    public Klant findByName(@PathVariable String naam, @PathVariable String geboortedatumString) {
        LocalDate geboortedatum = LocalDate.parse( geboortedatumString);
        return klantRepository.find(naam, geboortedatum);
    }

    @DeleteMapping("/klant/delete")
    public void deleteKlant(@RequestBody Klant klant) {
        klantRepository.delete(klant);
    }
}
