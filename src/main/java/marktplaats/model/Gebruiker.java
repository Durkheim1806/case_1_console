package marktplaats.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String voornaam;
    private String achternaam;
    private String wachtwoord;
    @ElementCollection(targetClass = Bezorgwijze.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "Afhandelingsopties")
    @Column(name = "Bezorgwijze")
    private List<Bezorgwijze> afhandelingsopties;
    private String favorieteProgrammeertaal;
//    private File foto;


    public Gebruiker(String voornaam, String achternaam, List<Bezorgwijze> afhandelingsopties) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.afhandelingsopties = afhandelingsopties;
    }
}
