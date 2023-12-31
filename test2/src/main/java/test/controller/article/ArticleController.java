package test.controller.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.domain.Article;
import test.domain.Member;
import test.service.ArticleService;
import test.service.CommentService;
import test.service.LikeArticleService;
import test.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final LikeArticleService likeArticleService;

    //게시글목록
    @GetMapping("/article")
    public String articles(@ModelAttribute(name = "article") ArticleDTO articleDTO, @RequestParam(defaultValue="") String redirect, Model model) {

        List<Article> articles = articleService.findAll();

        //이렇게 파라미터로 하면 장점이 뭐임?
        if (redirect.equals("write")) {
            return "/article/writeArticleForm";
        }

        model.addAttribute("articles", articles);

        return "/article/articles";
    }

    //게시글 작성
    @PostMapping("/article")
    public String writeArticle(@Validated @ModelAttribute(name = "article") ArticleDTO articleDTO,
                               BindingResult bindingResult,
                               HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/article/writeArticleForm";
        }

        //이렇게 해야 해?
        //이거 말고 member의 id를 찾는 방법 없어?
        Member member = (Member)request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();

        articleService.saveArticle(articleDTO, memberId);

        return "redirect:/article";
    }

    //게시글 내용보기(파라미터의 순서는 어떻게 하는게 좋은가?
    @GetMapping("/article/{articleId}")
    public String article(@PathVariable Long articleId,
                          @RequestParam(defaultValue = "") String redirect,
                          @ModelAttribute(name = "comment") CommentDTO commentDTO,
                          Model model) {
        //articleService의 findOne을 Article그대로 반환해도 되는가?
        //아니면 DTO로 바꾸어서 반환해야 하는가?
        Article article = articleService.findOne(articleId);
        model.addAttribute("article", article);
        if (redirect.equals("modify")) {
            return "/article/modifyArticle";
        }
        if (redirect.equals("delete")) {
            commentService.removeByArticleId(articleId);
            articleService.deleteArticle(articleId);
            return "redirect:/article";
        }

        //댓글 출력 원래 이런식으로 출력하는건가?
        List<CommentDTO> comments = commentService.printComment(articleId);
        model.addAttribute("comments", comments);

        Long countLike = likeArticleService.countByArticleId(articleId);
        model.addAttribute("countLike", countLike);

        return "/article/article";
    }


    @PostMapping("/article/{articleId}")
    public String modifyArticle(@Validated @ModelAttribute(name = "article") ArticleDTO articleDTO,
                                @PathVariable Long articleId,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/article/modifyArticle";
        }

        articleService.updateArticle(articleDTO, articleId);

        return "redirect:/article/{articleId}";
    }

    //댓글쓰는곳에 아무것도 안쓰면 검증 안하고 바로 에러남
    @PostMapping("/article/{articleId}/comment")
    public String writeComment(@Validated @ModelAttribute(name = "comment") CommentDTO comment,
                               @PathVariable Long articleId,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/article/{articleId}";
        }
        commentService.saveComment(comment, articleId);

        return "redirect:/article/{articleId}";
    }

    /*@PatchMapping("/article/{articleId}/like")
    public String likeArticle(@PathVariable Long articleId) {

        likeArticleService.clickLike(likeArticleDTO);

        return "redirect:/article/{articleId}";
    }*/

    //patch를 생각해보자
    @PutMapping("/article/{articleId}/comment/{commentId}")
    public String modifyComment(@ModelAttribute(name = "comment") CommentDTO commentDTO,
                                @PathVariable Long articleId,
                                @PathVariable Long commentId) {

        commentService.modifyComment(commentDTO);

        return "redirect:/article/{articleId}";
    }


    //deleteMapping 에서는 redirect를 못한다고 한다 하는 방법을 찾아내자
    @DeleteMapping("/article/{articleId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long articleId,
                                @PathVariable Long commentId) {
        commentService.removeComment(commentId);

        return "redirect:/article/{articleId}";
    }





}
