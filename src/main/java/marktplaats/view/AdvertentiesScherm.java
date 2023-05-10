package marktplaats.view;

import jakarta.inject.Inject;
import marktplaats.model.AdvertentieDAO;


public class AdvertentiesScherm {

    @Inject
    private AdvertentieDAO dao;

    public void start() {
        System.out.println("Overzicht van advertenties komt hier");

    }
}
