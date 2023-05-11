package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class GebruikerDAO {

    private EntityManager em;

    @Inject
    public GebruikerDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(Gebruiker gebruiker) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(gebruiker);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }
    }

    @PreDestroy
    public void close() {
        this.em.close();
    }
}
