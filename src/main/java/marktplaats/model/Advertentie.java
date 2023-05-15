package marktplaats.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Bezorgwijze bezorgwijze;
    //    private List<File> fotos;
    private String linkVideo;
    private String omschrijving;
    private String vraagprijs;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Gebruiker aanbieder;

    public Advertentie(String titel, Soort soort, Categorie categorie, Bezorgwijze bezorgwijze, String linkVideo, String omschrijving, String vraagprijs, Gebruiker aanbieder) {
        this.titel = titel;
        this.soort = soort;
        this.categorie = categorie;
        this.bezorgwijze = bezorgwijze;
        this.linkVideo = linkVideo;
        this.omschrijving = omschrijving;
        this.vraagprijs = vraagprijs;
        this.aanbieder = aanbieder;
    }

}
