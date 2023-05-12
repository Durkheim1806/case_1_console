package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.Scanner;

@Slf4j
@Singleton
public class AdvertentieBekijkenScherm extends Scherm {

    @Inject
    private AdvertentieDAO advertentieDAO;

    public void start(Gebruiker gebruikerSessie) {

        int keuzeBekijken = 1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Alle Advertenties - Advertentie Bekijken ---" + "\033[0m");

            try {
                System.out.println("Geef het nummer op van de advertentie die je wilt bekijken. Wil je terug kies dan voor 0.");
                keuzeBekijken = scanner.nextInt();
                if (keuzeBekijken == 0) {
                    break;
                }
                System.out.println("Dit is de opgevraagde advertentie:");
                printAdvertentie(advertentieDAO.select(keuzeBekijken));
            } catch (Exception e) {
                System.out.println("Er is een foutmelding opgetreden. Probeer het opnieuw");
            }


        } while (keuzeBekijken != 0);
    }


}
