package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.controller.BiedingController;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Bieding;
import marktplaats.model.BiedingDAO;
import marktplaats.model.Gebruiker;

import java.math.BigDecimal;
import java.util.Scanner;

@Slf4j
@Singleton
public class AdvertentieBiedenScherm extends Scherm {

    private Gebruiker gebruikerSessie;
    @Inject
    private AdvertentieDAO advertentieDAO;
    @Inject
    private BiedingDAO biedingDAO;

    @Inject
    private BiedingController biedingController;

//    @Inject BiedingController ...

    @Override
    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuzeBieden;
        BigDecimal bedrag;

        int keuze = 0;
        do {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Alle Advertenties - Advertentie Bieden ---" + "\033[0m");
            System.out.println("Je bent ingelogd als " + gebruikerSessie.getVoornaam());
            System.out.println("Op welke advertentie wil je bieden? Als je terug wilt gaan kan je 0 intypen.");
            keuzeBieden = scanner.nextInt();
            if (keuzeBieden == 0) {
                break;
            }
            System.out.println("Wat is het bedrag dat je wilt bieden?");
            bedrag = scanner.nextBigDecimal();
            try {
                biedingController.insert(new Bieding(advertentieDAO.select(keuzeBieden), bedrag, this.gebruikerSessie));
                System.out.println("Het bod wordt vastgelegd.");
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException);
            }
        } while (keuzeBieden != 0);


    }
}
