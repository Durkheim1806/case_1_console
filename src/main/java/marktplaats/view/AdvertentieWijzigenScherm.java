package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.List;
import java.util.Scanner;

@Singleton
public class AdvertentieWijzigenScherm extends Scherm {

    @Inject
    AdvertentieDAO advertentieDAO;

    private Gebruiker gebruikerSessie;

    @Override
    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuzeAdvertentieWijzigen;
        Advertentie advertentieWijzigen;
        String keuzeVeldWijzigen;
        String wijziging;
        String opslaanKeuze;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Mijn Advertenties - Advertentie Wijzigen ---" + "\033[0m");
        System.out.println("Dit zijn je advertenties:");
        List<Advertentie> lijstAdvertenties = advertentieDAO.vindAdvertentiesPerGebruiker(this.gebruikerSessie.getId());
        printLijstAdvertenties(lijstAdvertenties);
        System.out.println("Welke wil je wijzigen?");
        keuzeAdvertentieWijzigen = scanner.nextInt();
        advertentieWijzigen = advertentieDAO.select(keuzeAdvertentieWijzigen);

        try {
            System.out.println("Welk veld wil je wijzigen (titel/soort/bezorgwijze/omschrijving/vraagprijs)?");
            while (!scanner.hasNext("(?i)(titel|soort|bezorgwijze|omschrijving|vraagprijs)")) {
                System.out.println("Je hebt niet de juiste bezorgwijze ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            keuzeVeldWijzigen = scanner.next();
            switch (keuzeVeldWijzigen) {
                case "titel":
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Nu is de titel: " + advertentieWijzigen.getTitel());
                    System.out.println("Wat wil je ervan maken?");
                    wijziging = scanner2.nextLine();
                    advertentieWijzigen.setTitel(wijziging);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Je hebt deze gegevens ingevuld:");
        printAdvertentie(advertentieWijzigen);

        try {
            System.out.println("Vul opslaan in als je tevreden bent, en terug als je toch niet wilt opslaan:");
            while (!scanner.hasNext("(?i)(opslaan|terug)")) {
                System.out.println("Je hebt niet opslaan of terug ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            opslaanKeuze = scanner.next();
            if (opslaanKeuze.equalsIgnoreCase("opslaan")) {
                System.out.println("De advertentie wordt opgeslagen.");
                advertentieDAO.insert(advertentieWijzigen);
            } else {
                System.out.println("Je wordt teruggeleid.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
