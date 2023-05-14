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

    public void delete(Advertentie advertentie) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(advertentie);
        transaction.commit();
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

    public List<Advertentie> vindAdvertentiesPerSoort(Soort soort) {

        return em.createQuery("""   
                        select a 
                        from Advertentie a
                        where a.soort = :soort
                        """, Advertentie.class)
                .setParameter("soort", soort)
                .getResultList();
    }

    public List<Advertentie> vindAdvertentiesPerCategorie(long categorie_id) {

        return em.createQuery("""   
                        select a 
                        from Advertentie a
                        where a.categorie.id = :categorie_id
                        """, Advertentie.class)
                .setParameter("categorie_id", categorie_id)
                .getResultList();
    }

    public List<Advertentie> vindAdvertentieZoekterm(String zoekterm) {
        return em.createQuery("""   
                        select a 
                        from Advertentie a
                        where a.titel like :zoekterm
                        or a.omschrijving like :zoekterm
                        """, Advertentie.class)
                .setParameter("zoekterm", "%" + zoekterm + "%")
                .getResultList();
    }


    @PreDestroy
    public void close() {
        this.em.close();
    }

}
