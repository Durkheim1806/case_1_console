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
            em.merge(advertentie);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }
    }

    public Advertentie select(int id) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Advertentie advertentie = em.find(Advertentie.class, id);
        transaction.commit();

        return advertentie;
    }

    public List<Advertentie> findAll() {

        return em.createQuery("select a from Advertentie a", Advertentie.class).getResultList();
    }

    public List<Advertentie> vindAdvertentiesPerGebruiker(long aanbieder_id) {

        return em.createQuery("""   
                        select a 
                        from Advertentie a
                        where a.aanbieder.id = :aanbieder_id
                        """, Advertentie.class)
                .setParameter("aanbieder_id", aanbieder_id)
                .getResultList();
    }

    @PreDestroy
    public void close() {
        this.em.close();
    }

}
