package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.*;

import java.util.Scanner;

@Slf4j
@Singleton
public class AdvertentieAanmakenScherm extends Scherm {

    @Inject
    CategorieDAO categorieDAO;

    @Inject
    AdvertentieDAO advertentieDAO;

    private Gebruiker gebruikerSessie;

    @Override
    public void start(Gebruiker gebruikerSessie) {

        this.gebruikerSessie = gebruikerSessie;

        String titel = "";
        String soort = "";
        int categorie = 0;
        String bezorgwijze = "";
        String beschrijving = "";
        String vraagprijs = "";
        String opslaanKeuze = "";

        System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Mijn Advertenties - Advertentie Aanmaken ---" + "\033[0m");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Geef de velden op voor het aanmaken van de advertentie:");

        try {
            System.out.println("Titel:");
            titel = scanner1.nextLine();
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }

        try {
            System.out.println("Soort (product/dienst):");
            while (!scanner1.hasNext("(?i)(dienst|product)")) {
                System.out.println("Je hebt niet product of dienst ingetypt. Probeer het opnieuw:");
                scanner1.next();
            }
            soort = scanner1.next();
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }

        try {

            System.out.println("Dit zijn de categorien voor advertenties:");
            System.out.println("----------------------------");
            printLijstCategorien2(categorieDAO.findAll());
            System.out.println("----------------------------");
            System.out.println("Vul een categorie in:");
            categorie = scanner1.nextInt() - 1;
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }


        try {
            System.out.println("Bezorgwijze (versturen/afhalen/rembours/magazijn/verzekerde verzending):");
            while (!scanner1.hasNext("(?i)(versturen|afhalen|rembours|magazijn|verzekerde verzending)")) {
                System.out.println("Je hebt niet de juiste bezorgwijze ingetypt. Probeer het opnieuw:");
                scanner1.next();
            }
            bezorgwijze = scanner1.next();
            System.out.println("Je hebt gekozen voor: " + bezorgwijze);
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }

        Scanner scanner2 = new Scanner(System.in); // nog ff navragen waarom dit ding nodig is, op de een of andere manier heeft

        try {
            System.out.println("Beschrijving:");
            beschrijving = scanner2.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("Vraagprijs:");
            vraagprijs = scanner2.nextLine();
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }

        Advertentie advertentie = new Advertentie(titel, Soort.fromShortName(soort.toLowerCase()), categorieDAO.findAll().get(categorie), Bezorgwijze.fromShortName(bezorgwijze.toLowerCase()), null, beschrijving, vraagprijs, this.gebruikerSessie);
        System.out.println("Je hebt deze gegevens ingevuld:");
        printAdvertentie(advertentie);

        Scanner scanner5 = new Scanner(System.in);

        try {
            System.out.println("Vul opslaan in als je tevreden bent, en terug als je toch niet wilt opslaan:");
            while (!scanner5.hasNext("(?i)(opslaan|terug)")) {
                System.out.println("Je hebt niet opslaan of terug ingetypt. Probeer het opnieuw:");
                scanner5.next();
            }
            opslaanKeuze = scanner5.next();
            if (opslaanKeuze.equalsIgnoreCase("opslaan")) {
                System.out.println("De advertentie wordt opgeslagen.");
                advertentieDAO.insert(advertentie);
            } else {
                System.out.println("Je wordt teruggeleid.");
            }
        } catch (Exception exception) {
            System.out.println("Er is een foutmelding opgetreden:" + exception);
        }


    }


}
