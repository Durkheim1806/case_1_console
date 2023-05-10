package marktplaats.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AdvertentieSubCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naam;
    @ManyToOne
    private AdvertentieCategorie categorie;

    public AdvertentieSubCategorie(String naam, AdvertentieCategorie categorie) {
        if (categorie == null) {
            throw new IllegalArgumentException("categorie moet worden ingevuld");
        }
        this.naam = naam;
        this.categorie = categorie;
    }
}
