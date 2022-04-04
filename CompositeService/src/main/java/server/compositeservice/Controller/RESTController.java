package server.compositeservice.Controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import server.compositeservice.Model.Artikel;
import server.compositeservice.Model.Bestelling;
import server.compositeservice.Model.Klant;
import server.compositeservice.repository.ArtikelRepository;
import server.compositeservice.repository.BestellingRepository;
import server.compositeservice.repository.KlantRepository;

import java.time.LocalDate;

/**
 * @author Stefan de Haan
 */
@Controller
public class RESTController {
    @Autowired
    private ArtikelRepository artikelRepository;
    @Autowired
    private KlantRepository klantRepository;
    @Autowired
    private BestellingRepository bestellingRepository;

    @PostMapping("/order/place")
    public ResponseEntity<String> placeOrder(@RequestBody ObjectNode request) {
        String validation = validate(request);
        System.out.println(validation);
        if (!validation.isEmpty()) {
            return ResponseEntity.badRequest().body(validation);
        }
        String productTitel = request.findValue("bestelling")
                .findValue("titel")
                .textValue();
        String klantNaam = request.findValue("klant").findValue("naam").textValue();
        String geboortedatumString = request.findValue("klant").findValue("geboortedatum").textValue();
        LocalDate geboortedatum = LocalDate.parse(geboortedatumString);

        RestTemplate restTemplate = new RestTemplate();
        ObjectNode klant = restTemplate.getForObject("http://localhost:8080/find/" + klantNaam + "/" + geboortedatum, ObjectNode.class);
        ObjectNode product = restTemplate.getForObject("http://localhost:8081/find/naam/" + productTitel, ObjectNode.class);

        int klantID = klant.get("id").asInt();
        int productID = product.get("id").asInt();

        ObjectNode req = new ObjectNode(JsonNodeFactory.instance);
        req.put("klant_id", klantID)
                .put("artikel_id", productID)
                .put("bestelNummer", request.findValue("bestelnummer").textValue())
                .put("bestelDatum", String.valueOf(LocalDate.now()));

        restTemplate.put("http://localhost:8082/save", req);

        return ResponseEntity.ok(klant + "\n" + product + "\n" + req);
    }
    @PostMapping("/order/place/monolithic")
    public ResponseEntity<String> placeOrderMonolithic(@RequestBody ObjectNode request) {
        String validation = validate(request);
        if (!validation.isEmpty()) {
            return ResponseEntity.badRequest().body(validation);
        }
        String productTitel = request.findValue("bestelling")
                .findValue("titel")
                .textValue();
        String klantNaam = request.findValue("klant").findValue("naam").textValue();
        String geboortedatumString = request.findValue("klant").findValue("geboortedatum").textValue();
        LocalDate geboortedatum = LocalDate.parse(geboortedatumString);

        Klant klant = klantRepository.find(klantNaam, geboortedatum);
        Artikel artikel = artikelRepository.find(productTitel);
        Bestelling bestelling = new Bestelling(klant, artikel, request.findValue("bestelnummer").intValue());
        return ResponseEntity.ok(bestellingRepository.save(bestelling).toString());
    }


    private String validate(ObjectNode request) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!request.has("bestelling")) {
            stringBuilder.append("Field \"bestelling\" is missing in API request<br>");
        } else if (!request.findValue("bestelling").has("titel")) {
            stringBuilder.append("Field \"titel\" is missing in \"bestelling\"<br>");
        }

        if (!request.has("klant")) {
            stringBuilder.append("Field \"klant\" is missing in API request<br>");
        } else if (!request.findValue("klant").has("naam")) {
            stringBuilder.append("Field \"naam\" is missing in \"klant\"<br>");
        } else if (!request.findValue("klant").has("geboortedatum")) {
            stringBuilder.append("Field \"geboortedatum\" is missing in \"klant\"<br>");
        }

        if (!request.has("bestelnummer")) {
            stringBuilder.append("Field \"bestelnummer\" is missing in API request<br>");
        }
        return stringBuilder.toString();
    }

}
