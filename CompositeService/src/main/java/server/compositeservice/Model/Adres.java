package server.compositeservice.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Stefan de Haan
 */
@Entity
public class Adres {
    /* Properties */
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String stad;

    /* Constructors */
    public Adres() {
    }

    /* Getters and setters */
    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getStad() {
        return stad;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getStraat() {
        return straat;
    }

    public int getId() {
        return id;
    }
}
