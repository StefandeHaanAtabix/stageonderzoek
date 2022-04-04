package server.bestellingservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.bestellingservice.Model.Bestelling;
import server.bestellingservice.Repository.BestellingRepository;

import java.util.List;

/**
 * @author Stefan de Haan
 */
@RestController
public class BestellingController {
    @Autowired
    private BestellingRepository bestellingRepository;

    @GetMapping("/list")
    public List<Bestelling> bestellingList() {
        return bestellingRepository.getAll();
    }
    @PostMapping("/update")
    public Bestelling updateBestelling(Bestelling bestelling) {
        return bestellingRepository.update(bestelling);
    }
    @PutMapping("/save")
    public Bestelling createBestelling(@RequestBody Bestelling bestelling) {
        return bestellingRepository.save(bestelling);
    }
    @GetMapping("/find/{id}")
    public Bestelling findById(int id) {
        return bestellingRepository.find(id);
    }

    @DeleteMapping("/delete")
    public void deleteBestelling(Bestelling bestelling) {
        bestellingRepository.delete(bestelling);
    }
}
