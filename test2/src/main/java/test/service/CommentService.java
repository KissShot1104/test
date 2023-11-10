package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.controller.article.CommentDTO;
import test.domain.Article;
import test.domain.Comment;
import test.repository.ArticleRepository;
import test.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    //여기서는 ArticleService를 부르는게 좋은가? 아니면 Repository를 부르는게 좋은가?
    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    public List<CommentDTO> printComment(Long articleId) {

        List<Comment> comments = commentRepository.findByArticleId(articleId);
        List<CommentDTO> commentDTOList = new ArrayList<>();

        comments.stream().forEach(c -> {
            CommentDTO commentDTO = CommentDTO.builder().content(c.getContent()).build();
            commentDTOList.add(commentDTO);
        });

        return commentDTOList;
    }

    @Transactional(readOnly = false)
    public void saveComment(CommentDTO commentDTO, Long articleId) {
        Article article = articleService.findOne(articleId);
        Comment comment = Comment.builder()
                .article(article)
                .content(commentDTO.getContent())
                .build();
        commentRepository.save(comment);
    }

}
