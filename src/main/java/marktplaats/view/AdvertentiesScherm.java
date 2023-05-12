package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.List;
import java.util.Scanner;


@Slf4j
@Singleton
public class AdvertentiesScherm {

    private Gebruiker gebruikerSessie;

    @Inject
    private AdvertentieDAO advertentieDAO;

    @Inject
    private AdvertentieBekijkenScherm advertentieBekijkenScherm;

    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuze = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats Advertenties ---" + "\033[0m");
            System.out.println("Je bent ingelogd als " + gebruikerSessie.getVoornaam());
            System.out.println("--- Dit zijn alle advertenties: ---");
            printLijstAdvertenties(advertentieDAO.findAll());
            System.out.println("---  Keuze menu: ---");
            System.out.println("1 - Advertentie bekijken");
            System.out.println("2 - Terug");

            try {
                System.out.println("Geef je keuze op:");
                keuze = scanner.nextInt();
                switch (keuze) {
                    case 1:
                        this.advertentieBekijkenScherm.start();
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Probeer het nog eens.");
                }
            } catch (Exception exception) {
                System.out.println("Er is een foutmelding opgetreden. Probeer het opnieuw");
            }
        } while (keuze != 2);
    }

    public void printLijstAdvertenties(List<Advertentie> advertentieLijst) {
        System.out.printf("| %-5s | %-20s | %-10s | %-15s | %-15s | %-10s | %-20s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "SUBCATEGORIE", "VRAAGPRIJS", "AANBIEDER");
        for (int i = 0; i < advertentieLijst.size(); i++) {
            System.out.printf("| %-5s | %-20s | %-10s | %-15s | %-15s | %-10s | %-20s |%n", advertentieLijst.get(i).getId(), advertentieLijst.get(i).getTitel(), advertentieLijst.get(i).getSoort(), advertentieLijst.get(i).getCategorie() == null ? null : advertentieLijst.get(i).getCategorie().getNaam(), advertentieLijst.get(i).getSubCategorie() == null ? null : advertentieLijst.get(i).getSubCategorie().getNaam(), advertentieLijst.get(i).getVraagprijs(), advertentieLijst.get(i).getAanbieder().getEmail());
        }
    }


}
