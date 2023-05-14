package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
public class AdvertentieZoekenScherm extends Scherm {

    @Inject
    AdvertentieDAO advertentieDAO;
    @Inject
    CategorieDAO categorieDAO;

    @Override
    public void start(Gebruiker gebruiker) {

        String keuzeZoeken;
        String keuzeSoort;
        int keuzeCategorie;
        String zoekterm;

        System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Alle Advertenties - Advertentie Zoeken ---" + "\033[0m");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Welk veld wil je op zoeken (soort/categorie/zoekveld/bezorgwijze)?");
            while (!scanner.hasNext("(?i)(soort|categorie|zoekveld|bezorgwijze)")) {
                System.out.println("Je hebt niet de juiste bezorgwijze ingetypt. Probeer het opnieuw:");
                scanner.next();
            }
            keuzeZoeken = scanner.next();
            switch (keuzeZoeken) {
                case "soort":
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Wil je filteren op product of dienst?");
                    while (!scanner2.hasNext("(?i)(product|dienst)")) {
                        System.out.println("Je hebt niet de juiste bezorgwijze ingetypt. Probeer het opnieuw:");
                        scanner2.next();
                    }
                    keuzeSoort = scanner2.nextLine();
                    printLijstAdvertenties(advertentieDAO.vindAdvertentiesPerSoort(Soort.fromShortName(keuzeSoort.toLowerCase())));
                    break;
                case "categorie":
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("Dit zijn de categorien:");
                    System.out.println("----------------------------");
                    printLijstCategorien2(categorieDAO.findAll());
                    System.out.println("----------------------------");
                    System.out.println("Vul een categorie in:");
                    keuzeCategorie = scanner3.nextInt();
                    List<Categorie> categorieListChildren = categorieDAO.vindCategorieChildren(keuzeCategorie);
                    List<Advertentie> advertentieLijst = new ArrayList<>();
                    for (int i = 0; i < categorieListChildren.size(); i++) {
                        advertentieLijst.addAll(advertentieDAO.vindAdvertentiesPerCategorie(categorieListChildren.get(i).getId()));
                    }
                    printLijstAdvertenties(advertentieLijst);
                case "zoekveld":
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.println("Geef je zoekterm op:");
                    zoekterm = scanner4.next();
                    printLijstAdvertenties(advertentieDAO.vindAdvertentieZoekterm(zoekterm));


            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
