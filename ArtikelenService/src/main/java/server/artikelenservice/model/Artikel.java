package server.artikelenservice.model;

import javax.persistence.*;
/**
 * @author Stefan de Haan
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "get_all_artikelen", query = "select a from Artikel a")
)
public class Artikel {
    /* Properties */
    @Id
    @GeneratedValue
    private int id;
    private String titel;
    private String omschrijving;
    private double prijs;

    /* Constructors */

    public Artikel() {}

    public Artikel(String titel, String omschrijving, double prijs) {
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
    }

    /* Getters and setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
