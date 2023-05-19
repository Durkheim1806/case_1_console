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
@AddBeanClasses({EntityManagerProducerAlt.class, GebruikerDAO.class, BiedingDAO.class, BiedingController.class, AdvertentieDAO.class})
@EnableAlternatives(EntityManagerProducerAlt.class)
class BiedingITTest {

    @Inject
    private BiedingController biedingController;

    @Inject
    private AdvertentieDAO advertentieDAO;

    @Inject
    private GebruikerDAO gebruikerDAO;

    @AfterEach
    public void teardown() {
        if (advertentieDAO.getEm().getTransaction().isActive()) {
            advertentieDAO.getEm().getTransaction().rollback();
        }
    }

    @Test
    public void alsInsertBodIntegratieLagerOfGelijkAanBestaandeBiedingenDanException() {
        // given
        Gebruiker fred = Gebruiker.builder().build();
        var fredWithId = gebruikerDAO.insert(fred);
        Gebruiker sjaak = Gebruiker.builder().build();
        var sjaakWithId = gebruikerDAO.insert(sjaak);
        Advertentie advertentieFiets1 = Advertentie.builder().aanbieder(fredWithId).id(1).build();
        var aWithId = advertentieDAO.insert(advertentieFiets1);

        Bieding bodFiets20 = Bieding.builder().advertentie(aWithId).bieder(sjaakWithId).bedragBieding(BigDecimal.valueOf(20)).build();
        Bieding bodFiets10 = Bieding.builder().advertentie(aWithId).bieder(fredWithId).bedragBieding(BigDecimal.valueOf(10)).build();

        biedingController.insert(bodFiets20);

        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> biedingController.insert(bodFiets10));


    }

}