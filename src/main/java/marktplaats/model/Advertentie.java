package marktplaats.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Advertentie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    @Enumerated(EnumType.STRING)
    private Soort soort;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categorie categorie;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categorie subCategorie;
    @Enumerated(EnumType.STRING)
    private Bezorgwijze bezorgwijze;
    //    private List<File> fotos;
    private String linkVideo;
    private String omschrijving;
    private String vraagprijs;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Gebruiker aanbieder;

    public Advertentie(String titel, Soort soort, Bezorgwijze bezorgwijze, String omschrijving, String vraagprijs) {
        this.titel = titel;
        this.soort = soort;
        this.bezorgwijze = bezorgwijze;
        this.omschrijving = omschrijving;
        this.vraagprijs = vraagprijs;
    }

    public Advertentie(String titel, Soort soort, Categorie categorie, Categorie subCategorie, Bezorgwijze bezorgwijze, String omschrijving, String vraagprijs) {
        this.titel = titel;
        this.soort = soort;
        this.categorie = categorie;
        this.subCategorie = subCategorie;
        this.bezorgwijze = bezorgwijze;
        this.omschrijving = omschrijving;
        this.vraagprijs = vraagprijs;
    }

    public Advertentie(String titel, Soort soort, String omschrijving) {
        this.titel = titel;
        this.soort = soort;
        this.omschrijving = omschrijving;
    }
}
