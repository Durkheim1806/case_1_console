package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;

import static marktplaats.model.util.EntityManagerProducer.emf;

public abstract class DAO {

    protected final EntityManager em = emf().createEntityManager();

    @PreDestroy
    public void close() {
        // log.info("------------------------ PreDestroying " + this);
        em.close();
    }

}
