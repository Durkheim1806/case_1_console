package marktplaats.model;

import jakarta.inject.Inject;
import marktplaats.controller.BiedingController;
import marktplaats.model.util.EntityManagerProducerAlt;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@EnableAutoWeld
@AddBeanClasses({EntityManagerProducerAlt.class, BiedingDAO.class, BiedingController.class, AdvertentieDAO.class})
@EnableAlternatives(EntityManagerProducerAlt.class)
class BiedingITTest {

    @Inject
    private BiedingController biedingController;

    @Inject
    private AdvertentieDAO advertentieDAO;

    @AfterEach
    public void teardown() {
        if (advertentieDAO.getEm().getTransaction().isActive()) {
            advertentieDAO.getEm().getTransaction().rollback();
        }
    }

    @Test
    public void alsInsertBodLagerOfGelijkAanBestaandeBiedingenDanException() {
        // given
        Advertentie advertentieFiets1 = Advertentie.builder().build();
        var aWithId = advertentieDAO.insert(advertentieFiets1);

        Bieding bodFiets20 = Bieding.builder().advertentie(aWithId).bedragBieding(BigDecimal.valueOf(20)).build();
        Bieding bodFiets10 = Bieding.builder().advertentie(aWithId).bedragBieding(BigDecimal.valueOf(10)).build();

        biedingController.insert(bodFiets20);

        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> biedingController.insert(bodFiets10));


    }

}