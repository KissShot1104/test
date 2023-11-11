package test.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import test.domain.LikeArticle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class likeArticleRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(LikeArticle likeArticle) {
        em.persist(likeArticle);
    }

    public Long countByArticleId(Long articleId) {
        return em.createQuery("select count(la) from LikeArticle la where la.article.id = :articleId", Long.class)
                .setParameter("articleId", articleId)
                .getSingleResult();
    }

    public List<LikeArticle> findByArticleId(Long articleId) {
        return em.createQuery("select la from LikeArticle la where la.article = :articleId", LikeArticle.class)
                .setParameter("articleId", articleId)
                .getResultList();

    }

    public Long checkDuplicateLikeArticle(Long memberId, Long articleId) {
        return em.createQuery("select count(la) " +
                        "from LikeArticle la " +
                        "where la.member.id = :memberId AND la.article.id = :articleId",
                        Long.class)
                .setParameter("memberId", memberId)
                .setParameter("articleId", articleId)
                .getSingleResult();
    }

    public void removeById(Long id) {
        em.createQuery("delete from LikeArticle la where la.id = : id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
