package marktplaats.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Bieding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Advertentie advertentie;

    private BigDecimal bedragBieding;

    @ManyToOne
    private Gebruiker bieder;

    public Bieding(Advertentie advertentie, BigDecimal bedragBieding, Gebruiker bieder) {
        this.advertentie = advertentie;
        this.bedragBieding = bedragBieding;
        this.bieder = bieder;
    }

}
