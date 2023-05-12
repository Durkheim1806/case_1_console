package marktplaats.view;

import marktplaats.model.Advertentie;
import marktplaats.model.Categorie;
import marktplaats.model.Gebruiker;

import java.util.List;

public abstract class Scherm {

    public abstract void start(Gebruiker Scherm);

    public void printAdvertentie(Advertentie advertentie) {
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-10s | %-50s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "VRAAGPRIJS", "OMSCHRIJVING");
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-10s | %-50s |%n", advertentie.getId(), advertentie.getTitel(), advertentie.getSoort(), advertentie.getCategorie() == null ? null : advertentie.getCategorie().getNaam(), advertentie.getVraagprijs(), advertentie.getOmschrijving());
    }

    public void printLijstAdvertenties(List<Advertentie> advertentieLijst) {
        System.out.printf("| %-5s | %-20s | %-10s | %-15s | %-10s | %-20s |%n", "ID", "TITEL", "SOORT", "CATEGORIE", "VRAAGPRIJS", "AANBIEDER");
        for (int i = 0; i < advertentieLijst.size(); i++) {
            System.out.printf("| %-5s | %-20s | %-10s | %-15s | %-10s | %-20s |%n", advertentieLijst.get(i).getId(), advertentieLijst.get(i).getTitel(), advertentieLijst.get(i).getSoort(), advertentieLijst.get(i).getCategorie() == null ? null : advertentieLijst.get(i).getCategorie().getNaam(), advertentieLijst.get(i).getVraagprijs(), advertentieLijst.get(i).getAanbieder().getEmail());
        }
    }

    public void printLijstCategorien(List<Categorie> categorieLijst) {

        for (int i = 0; i < categorieLijst.size(); i++) {
            if (categorieLijst.get(i).getParent() == null) {
                System.out.printf("| %-5s | %-20s |%n", categorieLijst.get(i).getId(), categorieLijst.get(i).getNaam());
            } else {
                System.out.printf("| %-5s | %-20s |", categorieLijst.get(i).getId(), categorieLijst.get(i).getNaam());
                geefParentCategorie(categorieLijst.get(i));
            }
        }
        System.out.println();
    }

    public Categorie geefParentCategorie(Categorie childCategorie) {
        if (childCategorie.getParent() != null) {
            System.out.printf(" %-20s |", childCategorie.getParent().getNaam());
            return geefParentCategorie(childCategorie.getParent());
        } else {
            System.out.println();
        }
        return null;
    }
}
