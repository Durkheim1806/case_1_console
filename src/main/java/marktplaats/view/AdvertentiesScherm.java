package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;

import java.util.List;
import java.util.Scanner;


@Slf4j
@Singleton
public class AdvertentiesScherm {

    @Inject
    private AdvertentieDAO advertentieDAO;

    public void start() {
        int keuze;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---  Marktplaats Advertenties ---");
            printLijstAdvertenties(advertentieDAO.findAll());
            System.out.println("1 - Advertentie bekijken");
            System.out.println("2 - Mijn advertenties");
            System.out.println("3 - Profiel");
            System.out.println("4 - Terug");
            System.out.println("Geef je keuze op:");
            keuze = scanner.nextInt();
            switch (keuze) {
                case 1:
                    int keuzeBekijken;
                    System.out.println("Welke wil je bekijken?");
                    keuzeBekijken = scanner.nextInt();
                    printAdvertentie(advertentieDAO.select(keuzeBekijken));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Doei");
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer het nog eens.");
            }
        } while (keuze != 4);
    }

    public void printLijstAdvertenties(List<Advertentie> advertentieLijst) {
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "SUBCATEGORIE", "VRAAGPRIJS");
        for (int i = 0; i < advertentieLijst.size(); i++) {
            System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s |%n", advertentieLijst.get(i).getId(), advertentieLijst.get(i).getTitel(), advertentieLijst.get(i).getSoort(), advertentieLijst.get(i).getCategorie() == null ? null : advertentieLijst.get(i).getCategorie().getNaam(), advertentieLijst.get(i).getSubCategorie() == null ? null : advertentieLijst.get(i).getSubCategorie().getNaam(), advertentieLijst.get(i).getVraagprijs());
        }
    }

    public void printAdvertentie(Advertentie advertentie) {
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s | %-50s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "SUBCATEGORIE", "VRAAGPRIJS", "OMSCHRIJVING");
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-10s | %-50s |%n", advertentie.getId(), advertentie.getTitel(), advertentie.getSoort(), advertentie.getCategorie() == null ? null : advertentie.getCategorie().getNaam(), advertentie.getSubCategorie() == null ? null : advertentie.getSubCategorie().getNaam(), advertentie.getVraagprijs(), advertentie.getOmschrijving());
    }
}
