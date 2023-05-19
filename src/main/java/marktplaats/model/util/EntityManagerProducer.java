package marktplaats.model.util;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Singleton
public class EntityManagerProducer {


    @Produces
    public static EntityManagerFactory emf() {
        return Persistence.createEntityManagerFactory("MySQL-localhost-marktplaats");
    }

}
