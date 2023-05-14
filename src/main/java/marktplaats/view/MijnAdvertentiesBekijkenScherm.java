package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.List;

@Singleton
public class MijnAdvertentiesBekijkenScherm extends Scherm {

    @Inject
    AdvertentieDAO advertentieDAO;

    Gebruiker gebruikerSessie;

    @Override
    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        System.out.println("Dit zijn je advertenties:");
        List<Advertentie> lijstAdvertenties = advertentieDAO.vindAdvertentiesPerGebruiker(this.gebruikerSessie.getId());
        printLijstAdvertenties(lijstAdvertenties);

    }
}
