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
import java.util.Optional;

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
            CommentDTO commentDTO = CommentDTO.builder()
                    .id(c.getId())
                    .content(c.getContent())
                    .build();
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

    @Transactional(readOnly = false)
    public void removeComment(Long commentId) {
        commentRepository.remove(commentId);
    }

    //쿼리문으로 해결해야하는가?
    //아니면 반복문으로 돌려가며 삭제하는것이 좋은가?
    @Transactional(readOnly = false)
    public void removeByArticleId(Long articleId) {
        commentRepository.removeByArticleId(articleId);
    }

    //추가, 읽기, 수정, 삭제등등과 같은 것을 할 때
    //쿼리문을 써야하는 상황과 이렇게 컬렉션으로 써야하는 상황을
    //어떻게 생각해야 하는가를 물어보자(셋터를 열어놔야 하나 말아야하나? 언제열어놔야하나? 언제 닫아야 하나?)
    @Transactional(readOnly = false)
    public void modifyComment(CommentDTO commentDTO) {
        commentRepository.modifyComment(commentDTO.getId(), commentDTO.getContent());
    }

}
