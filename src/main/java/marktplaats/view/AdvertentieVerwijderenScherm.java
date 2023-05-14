package marktplaats.view;

import jakarta.inject.Inject;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.List;
import java.util.Scanner;

public class AdvertentieVerwijderenScherm extends Scherm {

    @Inject
    AdvertentieDAO advertentieDAO;

    private Gebruiker gebruikerSessie;

    @Override
    public void start(Gebruiker gebruiker) {
        this.gebruikerSessie = gebruiker;

        int keuzeAdvertentieVerwijderen;
        Advertentie advertentieVerwijderen;
        String opslaanKeuze;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Mijn Advertenties - Advertentie Verwijderen ---" + "\033[0m");
        System.out.println("Dit zijn je advertenties:");
        List<Advertentie> lijstAdvertenties = advertentieDAO.vindAdvertentiesPerGebruiker(this.gebruikerSessie.getId());
        printLijstAdvertenties(lijstAdvertenties);
        System.out.println("Welke wil je verwijderen?");
        keuzeAdvertentieVerwijderen = scanner.nextInt();
        advertentieVerwijderen = advertentieDAO.select(keuzeAdvertentieVerwijderen);

        System.out.println("Je hebt gekozen voor deze advertentie:");
        printAdvertentie(advertentieVerwijderen);


        try {
            System.out.println("Vul verwijderen in als je zeker weet dat je wilt verwijderen, vul anders terug in:");
            while (!scanner.hasNext("(?i)(verwijderen|terug)")) {
                System.out.println("Je hebt niet verwijderen of terug ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            opslaanKeuze = scanner.next();
            if (opslaanKeuze.equalsIgnoreCase("verwijderen")) {
                System.out.println("De advertentie wordt verwijderd.");
                advertentieDAO.delete(advertentieVerwijderen);
            } else {
                System.out.println("Je wordt teruggeleid.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
