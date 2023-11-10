package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.controller.article.ArticleDTO;
import test.domain.Article;
import test.domain.Member;
import test.repository.ArticleRepository;
import test.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public void saveArticle(ArticleDTO articleDTO, Long memberId) {

        Member member = memberRepository.findOne(memberId);

        Article article = Article.builder()
                .member(member)
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .category(articleDTO.getCategory())
                .timeStamps(null).build();

        articleRepository.save(article);
    }


    @Transactional(readOnly = false)
    public void updateArticle(ArticleDTO articleDTO, Long articleId) {
        articleRepository.update(
                articleDTO.getTitle(),
                articleDTO.getContent(),
                articleDTO.getCategory(),
                articleId
        );

    }

    @Transactional(readOnly = false)
    public void deleteArticle(Long id) {
        articleRepository.delete(id);
    }


    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findOne(Long id) {
        return articleRepository.findOne(id);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

}
