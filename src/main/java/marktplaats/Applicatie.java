package marktplaats;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import marktplaats.model.Advertentie;
import marktplaats.model.AdvertentieDAO;
import marktplaats.model.Categorie;
import marktplaats.view.HoofdScherm;
import org.jboss.weld.environment.se.Weld;

import static marktplaats.model.Bezorgwijze.VERSTUREN;
import static marktplaats.model.Soort.PRODUCT;

@Slf4j
@Singleton
public class Applicatie {
    @Inject
    HoofdScherm hoofdScherm;

    @Inject
    private AdvertentieDAO advertentieDao;


    public static void main(String[] args) {

        try (SeContainer container = Weld.newInstance().initialize()) {
            Applicatie applicatie = container.select(Applicatie.class).get();
            applicatie.run();
        }

    }

    private void run() {
        Categorie advertentieCategorieKiteSurf = new Categorie("kite surf");
        Advertentie advertentieKiteBoard = new Advertentie("kite board", PRODUCT, advertentieCategorieKiteSurf, new Categorie("boards", advertentieCategorieKiteSurf), VERSTUREN, "f1 kite board zo goed als nieuw", "150");
        advertentieDao.insert((advertentieKiteBoard));
        Advertentie advertentieKiteNorth1 = new Advertentie("kite North 9m", PRODUCT, advertentieCategorieKiteSurf, new Categorie("kites", advertentieCategorieKiteSurf), VERSTUREN, "north kite nooit gebruikt", "150");
        advertentieDao.insert((advertentieKiteNorth1));
        Advertentie advertentieKiteNorth2 = new Advertentie("kite North 12m", PRODUCT, advertentieCategorieKiteSurf, null, VERSTUREN, "north kite 12m geel", null);
        advertentieDao.insert((advertentieKiteNorth2));
        Advertentie advertentieKiteNorth3 = new Advertentie(null, null, null, null, null);
        advertentieDao.insert((advertentieKiteNorth3));

        this.hoofdScherm.start();

    }
}