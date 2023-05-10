package marktplaats.model;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class AdvertentieDAO {

    private EntityManager em;

    @Inject
    public AdvertentieDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(Advertentie advertentie) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(advertentie);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }

    }

}
