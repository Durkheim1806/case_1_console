package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Gebruiker;

import java.util.Scanner;


@Singleton
public class MijnAdvertentiesScherm extends Scherm {

    @Inject
    AdvertentieAanmakenScherm advertentieAanmakenScherm;
    @Inject
    MijnAdvertentiesBekijkenScherm mijnAdvertentiesBekijkenScherm;
    @Inject
    AdvertentieWijzigenScherm advertentieWijzigenScherm;
    @Inject
    AdvertentieVerwijderenScherm advertentieVerwijderenScherm;
    @Inject
    private AdvertentieDAO advertentieDAO;

    private Gebruiker gebruikerSessie;


    public void start(Gebruiker gebruiker) {

        this.gebruikerSessie = gebruiker;

        int keuze = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats - Hoofd Menu - Mijn Advertenties ---" + "\033[0m");
            System.out.println("Je bent ingelogd als " + gebruikerSessie.getVoornaam());
            /*System.out.println("Dit zijn je advertenties:");
            List<Advertentie> lijstAdvertenties = advertentieDAO.vindAdvertentiesPerGebruiker(this.gebruikerSessie.getId());
            printLijstAdvertenties(lijstAdvertenties);*/
            System.out.println("---  Keuze menu: ---");
            System.out.println("1 - Advertentie aanmaken");
            System.out.println("2 - Advertenties bekijken");
            System.out.println("3 - Advertentie wijzigen");
            System.out.println("4 - Advertentie verwijderen");
            System.out.println("5 - Terug");

            try {
                System.out.println("Geef je keuze op:");
                keuze = scanner.nextInt();
                switch (keuze) {
                    case 1:
                        this.advertentieAanmakenScherm.start(this.gebruikerSessie);
                        break;
                    case 2:
                        this.mijnAdvertentiesBekijkenScherm.start(this.gebruikerSessie);
                        break;
                    case 3:
                        this.advertentieWijzigenScherm.start(this.gebruikerSessie);
                        break;
                    case 4:
                        this.advertentieVerwijderenScherm.start(this.gebruikerSessie);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Probeer het nog eens.");
                }
            } catch (Exception exception) {
                System.out.println("Er is een foutmelding opgetreden:" + exception);
            }
        } while (keuze != 5);

    }


}
