package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.controller.article.ArticleDTO;
import test.domain.Article;
import test.repository.ArticleRepository;
import test.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    /*@Transactional(readOnly = false)
    public void saveArticle(ArticleDTO articleDTO) {

        //Article article = Article.builder();

        articleRepository.save(article);
    }*/

    public void saveArticle(Article article) {

        //Article article = Article.builder();

        articleRepository.save(article);
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
