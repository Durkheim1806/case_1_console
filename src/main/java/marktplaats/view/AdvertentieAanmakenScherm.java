package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.CategorieDAO;
import marktplaats.model.Gebruiker;

import java.util.Scanner;

@Slf4j
@Singleton
public class AdvertentieAanmakenScherm extends Scherm {

    @Inject
    CategorieDAO categorieDAO;

    private Gebruiker gebruikerSessie;

    @Override
    public void start(Gebruiker gebruikerSessie) {

        this.gebruikerSessie = gebruikerSessie;

        int keuze = -1;
        do {
            System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Mijn Advertenties - Advertentie Aanmaken ---" + "\033[0m");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Geef de velden op voor het aanmaken van de advertentie:");
            System.out.print("Titel:");
            String titel = scanner.next();
            System.out.print("Soort (product/dienst):");
            String soort = scanner.next();
            System.out.println("Kies een van deze categorien:");
            printLijstCategorien(categorieDAO.findAll());

        } while (keuze != 0);


    }
}
