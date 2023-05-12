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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de velden op voor het aanmaken van de advertentie:");

        try {
            System.out.println("Titel:");
            titel = scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("Soort (product/dienst):");
            while (!scanner.hasNext("(?i)(dienst|product)")) {
                System.out.println("Je hebt niet product of dienst ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            soort = scanner.next();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("Dit zijn de categorien voor advertenties:");
            System.out.println("----------------------------");
            System.out.println();
            printLijstCategorien(categorieDAO.findAll());
            System.out.println("----------------------------");
            System.out.println("Vul een catgorie in:");
            categorie = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("Bezorgwijze (versturen/afhalen/rembours/magazijn/verzekerde verzending):");
            while (!scanner.hasNext("(?i)(versturen|afhalen|rembours|magazijn|verzekerde verzending)")) {
                System.out.println("Je hebt niet de juiste bezorgwijze ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            bezorgwijze = scanner.next();
            System.out.println("Je hebt gekozen voor: " + bezorgwijze);
        } catch (Exception e) {
            System.out.println(e);
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
            System.out.println("Alle waardes zijn ingevuld, we slaan de advertentie op.");
        } catch (Exception e) {
            System.out.println(e);
        }

        Advertentie advertentie = new Advertentie(titel, Soort.fromShortName(soort.toLowerCase()), categorieDAO.findAll().get(categorie), Bezorgwijze.fromShortName(bezorgwijze.toLowerCase()), null, beschrijving, vraagprijs, this.gebruikerSessie);
//        System.out.println("Je hebt deze gegevens ingevuld:");
//        printAdvertentie(advertentie);

        advertentieDAO.insert(advertentie);

/*        try {
            System.out.println("Vul opslaan in als je tevreden bent, en terug als je toch niet wilt opslaan:");
            while (!scanner.hasNext("(?i)(opslaan|terug)")) {
                System.out.println("Je hebt niet opslaan of terug ingetypt. Probeer het opnieuw:");
                opslaanKeuze = scanner.next();
            }
            opslaanKeuze = scanner.next();
            System.out.println("Je hebt gekozen voor: " + opslaanKeuze);
            if (opslaanKeuze.equalsIgnoreCase("opslaan")) {
                advertentieDAO.insert(advertentie);
            } else return;
        } catch (Exception e) {
            System.out.println(e);
        }*/


    }
}
