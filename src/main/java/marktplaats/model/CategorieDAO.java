package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Singleton
public class CategorieDAO {

    private EntityManager em;

    @Inject
    public CategorieDAO(EntityManager em) {
        this.em = em;
    }

    public List<Categorie> findAll() {

        return em.createQuery("select c from Categorie c", Categorie.class).getResultList();
    }


    @PreDestroy
    public void close() {
        this.em.close();
    }
}
