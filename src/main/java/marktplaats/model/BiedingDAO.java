package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Singleton
public class BiedingDAO {

    private EntityManager em;

    @Inject
    public BiedingDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(Bieding bieding) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(bieding);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }
    }

    public List<Bieding> vindBiedingenPerAdvertentie(int advertentie_id) {
        return em.createQuery("""   
                        select b
                        from Advertentie a, Bieding b
                        where a.id = b.advertentie.id
                        and a.id = :advertentie_id
                        """, Bieding.class)
                .setParameter("advertentie_id", advertentie_id)
                .getResultList();
    }


    @PreDestroy
    public void close() {
        this.em.close();
    }
}
