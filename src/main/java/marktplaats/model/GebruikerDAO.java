package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class GebruikerDAO extends DAO {
    
    public Gebruiker insert(Gebruiker gebruiker) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            var g = em.merge(gebruiker);
            transaction.commit();
            return g;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
            return null;
        }
    }

    public Gebruiker select(int id) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Gebruiker gebruiker = em.find(Gebruiker.class, id);
        transaction.commit();

        return gebruiker;
    }

    @PreDestroy
    public void close() {
        this.em.close();
    }
}
