package marktplaats.model;

import jakarta.inject.Inject;
import marktplaats.model.util.EntityManagerProducerAlt;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddBeanClasses({EntityManagerProducerAlt.class, AdvertentieDAO.class})
@EnableAlternatives(EntityManagerProducerAlt.class)
class AdvertentieDAOTest {

    @Inject
    private AdvertentieDAO target;

    @AfterEach
    public void teardown() {
        if (target.getEm().getTransaction().isActive()) {
            target.getEm().getTransaction().rollback();
        }
    }


    @Test
    void whenInsertValidPersonThenItIsInsertedInTheDatabase() {
        // given
        Advertentie advertentieFiets = Advertentie.builder().titel("Fiets").build();

        // when
        target.insert(advertentieFiets);

        // then
        Advertentie select = target.select(1);
        assertNotNull(select);
    }


}