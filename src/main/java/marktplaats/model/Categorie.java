package marktplaats.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @ManyToOne
    private Categorie parent;

    public Categorie(String naam) {
        this.naam = naam;
    }

    public Categorie(String naam, Categorie parent) {
        this.naam = naam;
        this.parent = parent;
    }


}
