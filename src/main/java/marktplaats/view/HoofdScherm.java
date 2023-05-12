package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.Gebruiker;

import java.util.Scanner;

@Singleton
public class HoofdScherm {

    private Gebruiker gebruikerSessie;
    @Inject
    AdvertentiesScherm advertentiesScherm;

    @Inject
    MijnAdvertentiesScherm mijnAdvertentiesScherm;

    public void start(Gebruiker gebruikerSessie) {

        this.gebruikerSessie = gebruikerSessie;

        int keuze = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats Hoofd Menu ---" + "\033[0m");
            System.out.println("Je bent ingelogd als " + gebruikerSessie.getVoornaam());
            System.out.println("1 - Alle advertenties");
            System.out.println("2 - Mijn advertenties");
            System.out.println("3 - Profiel");
            System.out.println("4 - Terug");

            try {
                System.out.println("Geef je keuze op:");
                keuze = scanner.nextInt();
                switch (keuze) {
                    case 1:
                        this.advertentiesScherm.start(this.gebruikerSessie);
                        break;
                    case 2:
                        this.mijnAdvertentiesScherm.start(this.gebruikerSessie);
                        break;
                    case 3:
                        ProfielScherm profielScherm = new ProfielScherm();
                        profielScherm.start();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Probeer het nog eens.");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("Je hebt geen nummer opgegeven, probeer het opnieuw.");
            } catch (Exception exception) {
                System.out.println("Er is een foutmelding opgetreden. Probeer het opnieuw");
            }


        } while (keuze != 4);
    }
}
