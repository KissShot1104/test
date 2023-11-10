package test.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.domain.Article;
import test.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public String articles(@ModelAttribute ArticleDTO articleDTO, @RequestParam(defaultValue="") String redirect, Model model) {

//        List<Article> articles = articleService.findAll();

        //이렇게 리퀘스트 파람으로 하면 장점이 뭐임?
        if (redirect.equals("Write")) {
            return "/article/writeArticleForm";
        }

        /*ArrayList<Article> articles = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            Article article = new Article();
            article.setTitle("hello" + i);
            article.setId(i);
            article.setViewCount(i * 2);
            articles.add(article);
        }*/

        //model.addAttribute("articles", articles);

        return "/article/articles";
    }

    @PostMapping("/article")
    public String writeArticle(@Validated @ModelAttribute ArticleDTO articleDTO,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/article?redirect=write";
        }

        //articleService.saveArticle(article);

        return "redirect:/article";
    }
}
