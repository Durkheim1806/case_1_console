package marktplaats.scherm;

import java.util.Scanner;

public class HoofdScherm {

    public void start(){

        int keuze;
        do {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---  Marktplaats Hoofd Menu ---");
        System.out.println("1 - Alle advertenties");
        System.out.println("2 - Mijn advertenties");
        System.out.println("3 - Profiel");
        System.out.println("4 - Sluiten");
        System.out.println("Geef je keuze op:");
        keuze = scanner.nextInt();

        switch(keuze){
            case 1:
                AdvertentiesScherm advertentiesScherm = new AdvertentiesScherm();
                advertentiesScherm.start();
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

        } while (keuze != 4);
    }
}
