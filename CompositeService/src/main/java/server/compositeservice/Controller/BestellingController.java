package server.compositeservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.compositeservice.Model.Bestelling;
import server.compositeservice.repository.BestellingRepository;

import java.util.List;

/**
 * @author Stefan de Haan
 */
@RestController
public class BestellingController {
    @Autowired
    private BestellingRepository bestellingRepository;

    @GetMapping("/bestelling/list")
    public List<Bestelling> bestellingList() {
        return bestellingRepository.getAll();
    }
    @PostMapping("/bestelling/update")
    public Bestelling updateBestelling(Bestelling bestelling) {
        return bestellingRepository.update(bestelling);
    }
    @PutMapping("/bestelling/save")
    public Bestelling createBestelling(@RequestBody Bestelling bestelling) {
        return bestellingRepository.save(bestelling);
    }
    @GetMapping("/bestelling/find/{id}")
    public Bestelling findById(int id) {
        return bestellingRepository.find(id);
    }

    @DeleteMapping("/bestelling/delete")
    public void deleteBestelling(Bestelling bestelling) {
        bestellingRepository.delete(bestelling);
    }
}
