package marktplaats.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static marktplaats.model.Bezorgwijze.VERSTUREN;
import static marktplaats.model.Soort.PRODUCT;

class AdvertentieTest {

    @Test
    public void alsAanmakenAdvertentieDanNietNull() {

        Advertentie advertentieKiteBoard = new Advertentie("kite board", PRODUCT, null, VERSTUREN, "link", "kite board nooit gebruikt", "150", null);
        System.out.println(advertentieKiteBoard.toString());
        Assertions.assertTrue(advertentieKiteBoard != null);
    }

}