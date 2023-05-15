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
public class CategorieDAO {

    private EntityManager em;

    @Inject
    public CategorieDAO(EntityManager em) {
        this.em = em;
    }

    public List<Categorie> findAll() {

        return em.createQuery("select c from Categorie c", Categorie.class).getResultList();
    }

    public Categorie select(int id) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Categorie categorie = em.find(Categorie.class, id);
        transaction.commit();

        return categorie;
    }

    public List<Categorie> vindCategorieChildren(long parent_id) {
        String sql = """
                 WITH RECURSIVE cte AS (
                 	SELECT id, naam, parent_id
                 	FROM Categorie
                 	WHERE id = :parent_id
                 	UNION ALL
                 	SELECT c.id, c.naam, c.parent_id
                 	FROM Categorie c
                 	JOIN cte ON C.parent_id = cte.id
                 )
                 SELECT *
                 FROM cte;
                """;
        return em.createNativeQuery(sql, Categorie.class)
                .setParameter("parent_id", parent_id)
                .getResultList();
    }


    @PreDestroy
    public void close() {
        this.em.close();
    }
}
