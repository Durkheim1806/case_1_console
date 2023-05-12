package marktplaats.view;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import marktplaats.model.Gebruiker;
import marktplaats.model.GebruikerDAO;

import java.util.Scanner;

@Singleton
public class LoginScherm extends Scherm {

    @Inject
    GebruikerDAO gebruikerDAO;

    @Inject
    HoofdScherm hoofdScherm;

    public void start(Gebruiker gebruikerSessie) {

        int keuze = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\033[0;33m" + "---  Marktplaats - Login Menu ---" + "\033[0m");
            System.out.println("We hebben drie dummy users:");
            System.out.println("1 - Frits");
            System.out.println("2 - Ruud");
            System.out.println("3 - Klaas");
            System.out.println("4 - Applicatie sluiten");

            try {
                System.out.println("Als welke user wil je inloggen?");
                keuze = scanner.nextInt();
                if (keuze > 0 && keuze < 4) {
                    this.hoofdScherm.start(gebruikerDAO.select(keuze));
                } else {
                    break;
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("Je hebt geen nummer opgegeven, probeer het opnieuw.");
            } catch (Exception exception) {
                System.out.println("Er is een foutmelding opgetreden. Probeer het opnieuw");
            }


        } while (keuze != 4);
    }
}
