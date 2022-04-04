package server.compositeservice.Model;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Stefan de Haan
 */

@Entity
@NamedQueries(
        @NamedQuery(name = "get_all_Klanten", query = "select k from Klant k")
)
public class Klant {
    /* Properties */
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;
    private String naam;
    private LocalDate geboortedatum;

    /* Relations */
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "adres_id")
    private Adres adres;

    /* Constructors */
    public Klant() {
    }

    public Klant(String naam, LocalDate geboortedatum, Adres adres) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.adres = adres;
    }

    public Klant(String naam, LocalDate geboortedatum) {
        this(naam, geboortedatum, null);
    }

    /* Getters and setters */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                '}';
    }
}
