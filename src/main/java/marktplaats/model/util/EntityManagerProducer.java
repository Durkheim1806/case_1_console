package marktplaats.model.util;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@Singleton
public class EntityManagerProducer {

    @Produces
    public EntityManager em() {
        return Persistence.createEntityManagerFactory("MySQL-localhost-marktplaats").createEntityManager();
    }
}
