package marktplaats.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static marktplaats.model.Bezorgwijze.VERSTUREN;
import static marktplaats.model.Soort.PRODUCT;

class AdvertentieTest {

    @Test
    public void alsAanmakenAdvertentieDanNietNull() {

        Advertentie advertentieKiteBoard = new Advertentie("kite board", PRODUCT, VERSTUREN, "f1 kite board zo goed als nieuw", "150");
        System.out.println(advertentieKiteBoard.toString());
        Assertions.assertTrue(advertentieKiteBoard != null);
    }

}