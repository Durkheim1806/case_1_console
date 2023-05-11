package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;

import java.util.Scanner;

@Slf4j
@Singleton
public class AdvertentieBekijkenScherm {

    @Inject
    private AdvertentieDAO advertentieDAO;

    public void start() {

        int keuzeBekijken = 1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---  Advertentie Bekijken Menu ---");
            System.out.println("Geef het nummer op van de advertentie die je wilt bekijken. Wil je terug kies dan voor 0.");
            keuzeBekijken = scanner.nextInt();

            if (keuzeBekijken == 0) {
                break;
            }
            System.out.println("Dit is de opgevraagde advertentie:");
            printAdvertentie(advertentieDAO.select(keuzeBekijken));

        } while (keuzeBekijken != 0);
    }

    public void printAdvertentie(Advertentie advertentie) {
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s | %-50s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "SUBCATEGORIE", "VRAAGPRIJS", "OMSCHRIJVING");
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s | %-50s |%n", advertentie.getId(), advertentie.getTitel(), advertentie.getSoort(), advertentie.getCategorie() == null ? null : advertentie.getCategorie().getNaam(), advertentie.getSubCategorie() == null ? null : advertentie.getSubCategorie().getNaam(), advertentie.getVraagprijs(), advertentie.getOmschrijving());
    }

}
