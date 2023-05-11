package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Scanner;

@Singleton
public class HoofdScherm {
    @Inject
    AdvertentiesScherm advertentiesScherm;

    public void start() {

        int keuze = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---  Marktplaats Hoofd Menu ---");
            System.out.println("1 - Alle advertenties");
            System.out.println("2 - Mijn advertenties");
            System.out.println("3 - Profiel");
            System.out.println("4 - Sluiten");


            try {
                System.out.println("Geef je keuze op:");
                keuze = scanner.nextInt();
                switch (keuze) {
                    case 1:
                        this.advertentiesScherm.start();
                        break;
                    case 2:
                        MijnAdvertentiesScherm mijnAdvertentiesScherm = new MijnAdvertentiesScherm();
                        mijnAdvertentiesScherm.start();
                        break;
                    case 3:
                        ProfielScherm profielScherm = new ProfielScherm();
                        profielScherm.start();
                        break;
                    case 4:
                        System.out.println("Doei");
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
