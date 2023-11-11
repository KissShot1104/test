package test.controller.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import test.domain.Article;
import test.domain.Member;

@AllArgsConstructor
@Builder
@Data
@Setter
public class LikeArticleDTO {

    private Long id;

    private Member member;

    private Article article;
}
