package test.repository;

import org.springframework.stereotype.Repository;
import test.domain.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Article article) {
        em.persist(article);
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class)
                .getResultList();
    }

    public Article findOne(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findByTitle(String title) {
        return em.createQuery("select a from Article a where a.title like :title", Article.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

}
