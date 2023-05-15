package marktplaats.model.util;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@Singleton
@Alternative
public class EntityManagerProducerAlt {

    @Produces
    public EntityManager em() {
        return Persistence
                .createEntityManagerFactory("H2-embedded-database").createEntityManager();
    }
}
