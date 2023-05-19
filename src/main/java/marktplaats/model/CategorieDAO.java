package marktplaats.model;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Singleton
public class CategorieDAO extends DAO {

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
                 WITH RECURSIVE CategoryBranch AS (
                 	SELECT p.id, p.naam, p.parent_id
                 	FROM Categorie p
                 	WHERE p.id = :parent_id
                 	UNION ALL
                 	SELECT c.id, c.naam, c.parent_id
                 	FROM Categorie c
                 	JOIN CategoryBranch ON c.parent_id = CategoryBranch.id
                 )
                 SELECT *
                 FROM CategoryBranch;
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
