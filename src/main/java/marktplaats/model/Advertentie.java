package marktplaats.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Advertentie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    @Enumerated(EnumType.STRING)
    private AdvertentieSoort soort;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AdvertentieCategorie categorie;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AdvertentieSubCategorie subCategorie;
    @Enumerated(EnumType.STRING)
    private Bezorgwijze bezorgwijze;
    //    private List<File> fotos;
    private String linkVideo;
    private String omschrijving;
    private String vraagprijs;

    public Advertentie(String titel, AdvertentieSoort soort, Bezorgwijze bezorgwijze, String omschrijving, String vraagprijs) {
        this.titel = titel;
        this.soort = soort;
        this.bezorgwijze = bezorgwijze;
        this.omschrijving = omschrijving;
        this.vraagprijs = vraagprijs;
    }

    public Advertentie(String titel, AdvertentieSoort soort, AdvertentieCategorie categorie, AdvertentieSubCategorie subCategorie, Bezorgwijze bezorgwijze, String omschrijving, String vraagprijs) {
        this.titel = titel;
        this.soort = soort;
        this.categorie = categorie;
        this.subCategorie = subCategorie;
        this.bezorgwijze = bezorgwijze;
        this.omschrijving = omschrijving;
        this.vraagprijs = vraagprijs;
    }
}
