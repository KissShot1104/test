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

    public void update(String title, String content, String category, Long id) {

        //setter를 포기하고 이렇게 쿼리문을 만들어야 하는가???
        em.createQuery("UPDATE Article a SET a.title = :title, a.content = :content, a.category = :category WHERE a.id = :id")
                .setParameter("title", title)
                .setParameter("content", content)
                .setParameter("category", category)
                .setParameter("id", id)
                .executeUpdate();

        //merge를 사용해야 하나?
    }

    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
