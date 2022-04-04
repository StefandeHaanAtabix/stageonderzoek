package server.bestellingservice.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Stefan de Haan
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "get_all_bestellingen", query = "select b from Bestelling b")
)
public class Bestelling {
    /* Properties */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    private int klant_id;
    private int artikel_id;
    private int bestelNummer;
    private Date bestelDatum;

    /* Constructors */
    public Bestelling() {
    }

    public Bestelling(int klant_id, int artikel_id, int bestelNummer) {
        this.klant_id = klant_id;
        this.artikel_id = artikel_id;
        this.bestelNummer = bestelNummer;
    }

    /* Getters and setters */
    public int getKlant_id() {
        return klant_id;
    }

    public void setKlant_id(int klant_id) {
        this.klant_id = klant_id;
    }

    public int getArtikel_id() {
        return artikel_id;
    }

    public void setArtikel_id(int artikel_id) {
        this.artikel_id = artikel_id;
    }

    public int getBestelNummer() {
        return bestelNummer;
    }

    public void setBestelNummer(int bestelNummer) {
        this.bestelNummer = bestelNummer;
    }

    public Date getBestelDatum() {
        return bestelDatum;
    }

    public void setBestelDatum(Date bestelDatum) {
        this.bestelDatum = bestelDatum;
    }

    @Override
    public String toString() {
        return "Bestelling{" +
                "id=" + id +
                ", klant_id=" + klant_id +
                ", artikel_id=" + artikel_id +
                ", bestelNummer=" + bestelNummer +
                ", bestelDatum=" + bestelDatum +
                '}';
    }
}
