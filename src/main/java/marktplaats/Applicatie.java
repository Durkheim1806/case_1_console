package marktplaats;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.inject.Inject;
import marktplaats.model.*;
import org.jboss.weld.environment.se.Weld;

public class Applicatie {

    @Inject
    private AdvertentieDAO advertentieDao;


    public static void main(String[] args) {

        try (SeContainer container = Weld.newInstance().initialize()) {
            Applicatie applicatie = container.select(Applicatie.class).get();
            applicatie.run();
        }

//        HoofdScherm hoofdScherm = new HoofdScherm();
//        hoofdScherm.start();

    }

    private void run() {
        Advertentie advertentieKiteBoard = new Advertentie("kite board", AdvertentieSoort.PRODUCT, Bezorgwijze.VERSTUREN, "f1 kite board zo goed als nieuw", "150");
        advertentieDao.insert((advertentieKiteBoard));
        AdvertentieCategorie advertentieCategorieKiteSurf = new AdvertentieCategorie("kite surf");
        Advertentie advertentieKiteNorth1 = new Advertentie("kite North", AdvertentieSoort.PRODUCT, advertentieCategorieKiteSurf, new AdvertentieSubCategorie("kites", advertentieCategorieKiteSurf), Bezorgwijze.VERSTUREN, "north kite nooit gebruikt", "150");
        advertentieDao.insert((advertentieKiteNorth1));

    }
}