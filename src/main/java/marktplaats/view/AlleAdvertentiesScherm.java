package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.Scanner;


@Slf4j
@Singleton
public class AlleAdvertentiesScherm extends Scherm {

    private Gebruiker gebruikerSessie;

    @Inject
    private AdvertentieDAO advertentieDAO;

    @Inject
    private AdvertentieZoekenScherm advertentieZoekenScherm;

    @Inject
    private AdvertentieBekijkenScherm advertentieBekijkenScherm;

    @Inject
    AdvertentieBiedenScherm advertentieBiedenScherm;

    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuze = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Alle Advertenties ---" + "\033[0m");
            System.out.println("Je bent ingelogd als " + gebruikerSessie.getVoornaam());
            System.out.println("--- Dit zijn alle advertenties: ---");
            printLijstAdvertenties(advertentieDAO.findAll());
            System.out.println("---  Keuze menu: ---");
            System.out.println("1 - Advertentie bekijken");
            System.out.println("2 - Advertentie zoeken");
            System.out.println("3 - Bieden op een advertentie");
            System.out.println("4 - Terug");

            try {
                System.out.println("Geef je keuze op:");
                keuze = scanner.nextInt();
                switch (keuze) {
                    case 1:
                        this.advertentieBekijkenScherm.start(this.gebruikerSessie);
                        break;
                    case 2:
                        this.advertentieZoekenScherm.start(this.gebruikerSessie);
                        break;
                    case 3:
                        this.advertentieBiedenScherm.start(this.gebruikerSessie);
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Probeer het nog eens.");
                }
            } catch (Exception exception) {
                System.out.println("Er is een foutmelding opgetreden. Probeer het opnieuw. Foutmelding: " + exception);
            }
        } while (keuze != 4);
    }


}
