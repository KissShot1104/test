package test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public List<Comment> findByArticleId(Long articleId) {
        return em.createQuery("select c from Comment c where c.article.id = :articleId", Comment.class)
                .setParameter("articleId", articleId)
                .getResultList();
    }

}
