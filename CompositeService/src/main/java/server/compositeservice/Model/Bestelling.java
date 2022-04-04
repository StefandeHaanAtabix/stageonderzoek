package server.compositeservice.Model;

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

    @ManyToOne
    @JoinColumn(name = "klant_id")
    private Klant klant;
    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;
    private int bestelNummer;
    private Date bestelDatum;


    /* Constructors */
    public Bestelling() {
    }

    public Bestelling(Klant klant, Artikel artikel, int bestelNummer) {
        this.klant = klant;
        this.artikel = artikel;
        this.bestelNummer = bestelNummer;
    }

    /* Getters and setters */
    public Klant getKlant() {
        return klant;
    }

    public Artikel getArtikel() {
        return artikel;
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
                ", klant=" + klant +
                ", artikel=" + artikel +
                ", bestelNummer=" + bestelNummer +
                ", bestelDatum=" + bestelDatum +
                '}';
    }
}
