package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.controller.article.ArticleDTO;
import test.controller.article.LikeArticleDTO;
import test.domain.Article;
import test.domain.LikeArticle;
import test.domain.Member;
import test.repository.likeArticleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeArticleService {

    private final likeArticleRepository likeArticleRepository;

    @Transactional(readOnly = false)
    public void clickLike(LikeArticleDTO likeArticleDTO) {

        Long memberId = likeArticleDTO.getMember().getId();
        Long articleId = likeArticleDTO.getArticle().getId();

        if (checkDuplecateLikeArticle(memberId, articleId)) {
            addLike(likeArticleDTO);
            return;
        }
            removeLike(likeArticleDTO.getId());
    }

    @Transactional(readOnly = false)
    public void addLike(LikeArticleDTO likeArticleDTO) {
        LikeArticle likeArticle = LikeArticle.builder()
                .member(likeArticleDTO.getMember())
                .article(likeArticleDTO.getArticle())
                .build();

        likeArticleRepository.save(likeArticle);
    }
    @Transactional(readOnly = false)
    public void removeLike(Long id) {
        likeArticleRepository.removeById(id);
    }

    public Long countLike(Long articleId) {
        return countByArticleId(articleId);
    }

    public boolean checkDuplecateLikeArticle(Long memberId, Long articleId) {
        return likeArticleRepository.checkDuplicateLikeArticle(memberId, articleId) != 0L;
    }


    public Long countByArticleId(Long articleId) {
        return likeArticleRepository.countByArticleId(articleId);
    }
}
