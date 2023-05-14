package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuzeBieden;
        BigDecimal bedrag;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Alle Advertenties - Advertentie Bieden ---" + "\033[0m");

        System.out.println("Dit zijn de advertenties waarop je kan bieden:");
        printLijstAdvertenties(advertentieDAO.findAll());
        System.out.println("Op welke advertentie wil je bieden?");
        keuzeBieden = scanner.nextInt();
        System.out.println("Wat is het bedrag dat je wilt bieden?");
        bedrag = scanner.nextBigDecimal();
        System.out.println("Het bod wordt vastgelegd.");
        biedingDAO.insert(new Bieding(advertentieDAO.select(keuzeBieden), bedrag, this.gebruikerSessie));
        System.out.println("Dit zijn de biedingen:");
    }
}
